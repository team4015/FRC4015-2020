/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakePistonSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Rangefinder;
import frc.robot.subsystems.RingLightSubsystem;
import frc.robot.subsystems.ShooterPistonSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.robotModes.*;
import org.opencv.core.MatOfPoint;
import java.util.ArrayList;
import edu.wpi.first.vision.VisionThread;
import frc.robot.*;


/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static CvSource outputStream;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  public static UsbCamera cameraFront;
  public static UsbCamera cameraShooter;
  public static IntakeSubsystem intakeSubsystem;
  public static IntakePistonSubsystem intakePistonSubsystem;
  public static ShooterPistonSubsystem shooterPistonSubsystem;
  public static ClimberSubsystem climberSubsystem;
  public static ConveyorSubsystem conveyorSubsystem;
  public static ShooterSubsystem shooterSubsystem;
  public static DriveTrain driveTrain;
  public static RingLightSubsystem ringLightSubsystem;
  public static Teleop teleop;
  public static Auto auto;
  public static Rangefinder rangeFinder;

  VisionThread visionThread;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    
    cameraFront = CameraServer.getInstance().startAutomaticCapture(0);
    cameraShooter = CameraServer.getInstance().startAutomaticCapture(1);
    cameraFront.setResolution(NumberConstants.IMG_WIDTH,NumberConstants.IMG_HEIGHT);
    // cameraA.setBrightness(15);
    // cameraA.setExposureManual(20);
    cameraShooter.setResolution(NumberConstants.IMG_WIDTH,NumberConstants.IMG_HEIGHT);
    
    intakeSubsystem = new IntakeSubsystem();
    intakePistonSubsystem = new IntakePistonSubsystem();
    shooterPistonSubsystem = new ShooterPistonSubsystem();
    climberSubsystem = new ClimberSubsystem();
    conveyorSubsystem = new ConveyorSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    driveTrain = new DriveTrain();
    ringLightSubsystem = new RingLightSubsystem();
    teleop = new Teleop();
    auto = new Auto();
    rangeFinder = new Rangefinder();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro           
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_chooser.getSelected();
    auto.start();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    teleop.start();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testInit() {
    
  }

  @Override
  public void testPeriodic() {
  
  }
}
