/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.NumberConstants;
import frc.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class AutoShooter extends Command {
    boolean far;
    Timer delay;
    boolean finished;
    double time;
    public AutoShooter(boolean far, double t) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterSubsystem);
        requires(Robot.shooterPistonSubsystem);
        requires(Robot.conveyorSubsystem);
        this.far = far;
        delay = new Timer();
        finished = false;
        time = t;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        delay.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        if (far) {
            Robot.shooterSubsystem.shootFar();
            Robot.shooterPistonSubsystem.extendShooter();
        } else {
            Robot.shooterSubsystem.shootClose();
            Robot.shooterPistonSubsystem.retractShooter();
        }
        if (delay.get()>NumberConstants.REVUP_TIME) {
            Robot.conveyorSubsystem.shootConveyor();
          } 
        if (delay.get()>time) {
            Robot.conveyorSubsystem.idle();
            Robot.shooterSubsystem.idle();
            Robot.conveyorSubsystem.balls = 0;
            finished = true;
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.shooterSubsystem.idle();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}