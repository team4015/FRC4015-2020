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
public class RingLightManager extends Command {
    Timer delay;
    int blinkNum = 0;
  public RingLightManager() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ringLightSubsystem);
    delay = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.ringLightSubsystem.off();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if (OI.XBoxControllerSubsystem.getRawButton(RobotMap.FAR_SHOOT_BUTTON)) {
        Robot.ringLightSubsystem.on();
      } else {
        Robot.ringLightSubsystem.off();
      }
      // if (OI.XBoxControllerDriver.getYButtonPressed()) {
      //     Robot.ringLightSubsystem.off();
      //     delay.start();
      // }
      // if (delay.get()>=0.15) {
      //     delay.stop();
      //     delay.reset();
      //     Robot.ringLightSubsystem.on();
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
