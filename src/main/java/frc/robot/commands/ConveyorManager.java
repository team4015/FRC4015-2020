/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.NumberConstants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class ConveyorManager extends Command {
  boolean autoRun;
  Timer shooterDelay;
  boolean button2State;
  boolean override;
  Timer overrideTimer;
  public ConveyorManager() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.conveyorSubsystem);
    autoRun = true;
    shooterDelay = new Timer();
    button2State = false;
    override = false;
    overrideTimer = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // if (override) {
    //   if (overrideTimer.get()>NumberConstants.AUTOCONVEY_TIME) {
    //   Robot.conveyorSubsystem.idle();
    //   overrideTimer.stop();
    //   overrideTimer.reset();
    //   override = false;
    //   } else {
    //     Robot.conveyorSubsystem.forward();
    //   }
    // }
     
    //so the .getRawButtonPressed(2) method for logitech controller button 2 does not work, write your own
    SmartDashboard.putNumber("BALLS", Robot.conveyorSubsystem.balls);
    //shooting ball sequence
    if ((button2State==false&&OI.XBoxControllerSubsystem.getRawButton(RobotMap.CLOSE_SHOOT_BUTTON))||OI.XBoxControllerSubsystem.getRawButtonPressed(RobotMap.FAR_SHOOT_BUTTON)) {
      shooterDelay.start();
      autoRun = false;
      button2State = true;
    }
    // if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.CLOSE_SHOOT_BUTTON)||OI.XBoxControllerSubsystem.getRawButton(RobotMap.FAR_SHOOT_BUTTON)) {
      if (shooterDelay.get()>NumberConstants.REVUP_TIME) {
        Robot.conveyorSubsystem.shootConveyor();
        Robot.conveyorSubsystem.balls = 0;
      }
      
    // }
    if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.CLOSE_SHOOT_BUTTON)==false&&OI.XBoxControllerSubsystem.getRawButton(RobotMap.FAR_SHOOT_BUTTON)==false) {
      Robot.conveyorSubsystem.idle();
      shooterDelay.stop();
      shooterDelay.reset();
      autoRun = true;
      button2State = false;
    }
    if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.CONVEYOR_OVERRIDE_BUTTON)) {
      Robot.conveyorSubsystem.forward();
      autoRun = false;
      // override = true;
      // if (Robot.conveyorSubsystem.balls>=5) {
      //   Robot.conveyorSubsystem.balls = 0;
      // }
      // Robot.conveyorSubsystem.balls++;
      // overrideTimer.start();
    }
    //reversing conveyor and resetting ball count
    else if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.REVERSE_BALLS_BUTTON)) {
      Robot.conveyorSubsystem.reverse();
      autoRun = false;
    } else if (OI.XBoxControllerSubsystem.getRawButtonReleased(RobotMap.REVERSE_BALLS_BUTTON)) {
      Robot.conveyorSubsystem.idle();
      autoRun = true;
    }
    //autoconvey sequence
    if (autoRun) {
      Robot.conveyorSubsystem.autoConvey();
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
