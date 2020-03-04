/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class IntakePistonManager extends Command {
  private Timer delay;
  public IntakePistonManager() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakePistonSubsystem);
    delay = new Timer();
    // Robot.intakePistonSubsystem.initForStart();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.conveyorSubsystem.balls>4) {
      Robot.intakePistonSubsystem.retractIntake();
    }
    if (OI.XBoxControllerSubsystem.getRawButtonPressed(RobotMap.TOGGLE_INTAKE_BUTTON)) {
      if (Robot.intakePistonSubsystem.getIntakeExtended()) {
        Robot.intakePistonSubsystem.retractIntake();
      } else {
        Robot.intakePistonSubsystem.extendIntake();
        delay.start();
      }
    }
    if (delay.get()>0.5) {
      Robot.intakePistonSubsystem.disableIntake();
      delay.stop();
      delay.reset();
    } 
    // else if (OI.XBoxControllerSubsystem.getRawButtonPressed(2)) {
    //   Robot.intakePistonSubsystem.retractIntake();
    // }
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
