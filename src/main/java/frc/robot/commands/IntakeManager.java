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
import frc.robot.NumberConstants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class IntakeManager extends Command {
  private Timer overrideTimer;
  private boolean override;
  public IntakeManager() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakeSubsystem);
    overrideTimer = new Timer();
    override = false;
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
    //   Robot.intakeSubsystem.idle();
    //   overrideTimer.stop();
    //   overrideTimer.reset();
    //   override = false;
    //   } else {
    //     Robot.intakeSubsystem.intake();
    //   }
    // }
    if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.CONVEYOR_OVERRIDE_BUTTON)) {
      Robot.intakeSubsystem.intake();
      // overrideTimer.start();
      // override = true; 
    } else {
    //stop intake when you have 5 balls
    // if (Robot.conveyorSubsystem.balls>=5) {
    //   Robot.intakeSubsystem.idle();
    // }
    
    //otherwise, control it with controller
    if (OI.XBoxControllerSubsystem.getBumper(Hand.kLeft)) { //&&Robot.conveyorSubsystem.balls<5
      Robot.intakeSubsystem.intake();
    }
    else if (OI.XBoxControllerSubsystem.getBumper(Hand.kRight)) {
      Robot.intakeSubsystem.outtake();
    }
      //button 7 is left, button 8 is right
    else if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.REVERSE_BALLS_BUTTON)) {
      Robot.intakeSubsystem.outtake();
    } 
    // else if (OI.XBoxControllerSubsystem.getRawButton(8)) {
    //   Robot.intakeSubsystem.outtake();
    // }
    else {
      Robot.intakeSubsystem.idle();
    }
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
