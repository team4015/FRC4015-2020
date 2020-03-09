/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GripPipeline;
import frc.robot.NumberConstants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class AutoAlign extends Command {
  private DriveMode driveMode = DriveMode.MEDIUM;
  private VisionThread visionThread;
  private double centerX = 0.0;
  private double height = 0.0;
  private double targetHeight = 21.0;
  private double driveSpeed = 0.0;
  private double turnSpeed = 0.0;
  double angleKp = 0.85;  
  double distanceKp = 0.1;
  double angleTolerance = 0.09;
  double distanceTolerance = 1;
  private final Object imgLock = new Object();
  CvSink cvSink = new CvSink("opencv_USB Camera 0");
  CvSource outputStream = CameraServer.getInstance().putVideo("Vision Stream", NumberConstants.IMG_WIDTH, NumberConstants.IMG_HEIGHT);
  boolean run = false;

  Timer driveBack;
  boolean auto;
  double driveBackTime;
  enum DriveMode {
    FAST,
    MEDIUM,
    SLOW,
    SUPERSLOW
  }

  public AutoAlign() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
    driveBack = new Timer();
    auto = false;
    driveBackTime = NumberConstants.DRIVEBACK_TIME;
  }

  // Called just before this Command runs the first time    
  @Override
  protected void initialize() {
    cvSink.setSource(Robot.cameraShooter);
    visionThread = new VisionThread(Robot.cameraShooter, new GripPipeline(), pipeline -> {
      Mat output = new Mat();
      cvSink.grabFrame(output);
      SmartDashboard.putNumber("Targets Detected", pipeline.filterContoursOutput().size());
      if (!pipeline.filterContoursOutput().isEmpty()) {
          ArrayList<Rect> targets = new ArrayList<Rect>();
          
          for (int i = 0;i<pipeline.filterContoursOutput().size();i++) {
              Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i));
              targets.add(r);
              Imgproc.rectangle(output, new Point(r.x, r.y),new Point(r.x+r.width, r.y+r.height), new Scalar(0, 255, 0, 255), 1);
          }
          Collections.sort(targets, new Area()); //Sort by area
          // Collections.sort(targets, new LeftToRight());
          // Imgproc.rectangle(output, new Point(targets.get(0).x, targets.get(0).y),new Point(targets.get(0).x+targets.get(0).width, targets.get(0).y+targets.get(0).height), new Scalar(0, 255, 0, 255), 1);

          synchronized (imgLock) {
            Rect r1 = targets.get(0);
            centerX = r1.x+r1.width/2;
            height = r1.height;
            targets.clear();
          }
      }
      outputStream.putFrame(output);
  });
  visionThread.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(OI.XBoxControllerDriver.getAButtonPressed()) { // A Slows OI.JoyStickRight.getRawButtonPressed(3) || 
      switch(driveMode) {
      case MEDIUM:
        driveMode = DriveMode.FAST;
        break;
      case SLOW:
        driveMode = DriveMode.MEDIUM;
        break;
      case SUPERSLOW:
        driveMode = DriveMode.SLOW;
        break;
    default:
      break;
      }
    }
    if(OI.XBoxControllerDriver.getBButtonPressed()) { // B speeds OI.JoyStickRight.getRawButtonPressed(4) || 
      switch(driveMode) {
      case SLOW:
        driveMode = DriveMode.SUPERSLOW;
        break;
      case MEDIUM:
        driveMode = DriveMode.SLOW;
        break;
      case FAST:
        driveMode = DriveMode.MEDIUM;
        break;
    default:
      break;
      }
    }
    double slowMod;
      switch(driveMode) {
        case FAST:
          slowMod = 1;
          SmartDashboard.putString("Mode:", "FAST");
          break;
        case MEDIUM:
          slowMod = Math.sqrt(NumberConstants.SLOWING_CONSTANT);
          SmartDashboard.putString("Mode:", "MEDIUM");
          break;
        case SLOW:
          slowMod = NumberConstants.SLOWING_CONSTANT;
          SmartDashboard.putString("Mode:", "SLOW");
          break;
        case SUPERSLOW:
          slowMod = NumberConstants.SLOWING_CONSTANT*1.5;
          SmartDashboard.putString("Mode:", "SUPERSLOW");
          break;
        default:
          slowMod = 1;
          SmartDashboard.putString("Mode:", "FAST");
      }
  //VISION STUFF
  double centerX,height;
    synchronized (imgLock) {
        centerX = (this.centerX-NumberConstants.IMG_WIDTH/2)/(NumberConstants.IMG_WIDTH/2);
        height = this.height;
    }
    double distance = targetHeight-height;
    System.out.println("CENTER: "+centerX+" HEIGHT: "+height);
  if (OI.XBoxControllerDriver.getYButton()) {
    run = true;
  } else {
      run = false;
  }
  if (run==true) {
      //Angle P control loop
      if (Math.abs(centerX)>angleTolerance) {
          turnSpeed = centerX*angleKp;
        // if(centerX<0) {
        //     turnSpeed = -0.3;
        // } else {
        //     turnSpeed = 0.3;
        // }
      } else {
          turnSpeed = 0;
      }

      //Distance P control loop
      if (Math.abs(distance)>distanceTolerance) {
        // driveSpeed = distance*distanceKp;
          if (distance>0) {
              driveSpeed = -0.3;
          } else {
              driveSpeed = 0.3;
          }
      } else {
          driveSpeed = 0;
      }

      if (driveSpeed==0 && turnSpeed==0) {
          System.out.println("****ALIGNED****");
          SmartDashboard.putBoolean("ALIGNED", true);

      } else {
          System.out.println("****DRIVING****"+driveSpeed);
          SmartDashboard.putBoolean("ALIGNED", false);
      }
      Robot.driveTrain.drive(driveSpeed, turnSpeed, true);
  } else { //Teleop
    if (Math.abs(centerX)<=angleTolerance && Math.abs(distance)<distanceTolerance) {
        System.out.println("****ALIGNED****");
        SmartDashboard.putBoolean("ALIGNED", true);
    } else {
      SmartDashboard.putBoolean("ALIGNED", false);
    }
    if (auto==true&&driveBack.get()>driveBackTime) {
      auto = false;
      Robot.driveTrain.stop();
    }
    if (OI.XBoxControllerSubsystem.getRawButtonPressed(RobotMap.CLOSE_SHOOT_BUTTON)) {
      driveBack.start();
      auto = true;
    }
    if (auto==false) {
      double speed = (OI.XBoxControllerDriver.getTriggerAxis(Hand.kRight) - OI.XBoxControllerDriver.getTriggerAxis(Hand.kLeft)) / slowMod;
      double turn = OI.XBoxControllerDriver.getX(Hand.kLeft) / slowMod;
      Robot.driveTrain.drive(-speed, turn, true);
      SmartDashboard.putNumber("Speed %", Math.abs(speed)*100);
    } else {
      Robot.driveTrain.drive(0.6, 0.0, true);
    }
    // Robot.driveTrain.stop();
  }
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
// class LeftToRight implements Comparator<Rect> 
// { 
//     // Used for sorting in ascending order of 
//     // roll number 
//     public int compare(Rect a, Rect b) 
//     { 
//         return a.x-b.x; 
//     } 
// } 
class Area implements Comparator<Rect> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Rect a, Rect b) 
    { 
        if (a.area()-b.area()>0) {
            return 1;
        } else if (a.area()-b.area()<0) {
            return -1;
        }
        return 0; 
    } 
} 