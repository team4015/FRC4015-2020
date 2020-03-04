/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class AutoConveyor extends Command {
    Timer delay;
  boolean convey;
  public AutoConveyor(boolean convey) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.conveyorSubsystem);
    this.convey = convey;
    delay = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      delay.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if (delay.get()>2) {
        Robot.conveyorSubsystem.shootConveyor();
      } else {
          Robot.conveyorSubsystem.idle();
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
    Robot.conveyorSubsystem.idle();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
