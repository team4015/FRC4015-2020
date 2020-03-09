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
public class AutoDrive extends Command {
    double speed, turn, time;
    Timer timer;
    boolean finished;
    public AutoDrive(double speed, double turn, double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.speed = speed;
        this.turn = turn;
        this.time = time;
        timer = new Timer();
        finished = false;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.driveTrain.drive(speed, turn, true);
        if (timer.get()>time) {
            Robot.driveTrain.stop();
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
        Robot.driveTrain.stop();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}