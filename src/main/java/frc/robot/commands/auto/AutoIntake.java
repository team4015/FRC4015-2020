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
public class AutoIntake extends Command {
    double speed;
    Timer timer;
    boolean intake;
    double time;
    boolean finished;
    public AutoIntake(double driveSpeed, boolean intake, double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeSubsystem);
        requires(Robot.conveyorSubsystem);
        requires(Robot.driveTrain);
        this.speed = driveSpeed;
        finished = false;
        this.intake = intake;
        this.time = time;
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.drive(speed, 0.0, true);
        if (intake) { //intake balls
            Robot.intakeSubsystem.intake();
            Robot.conveyorSubsystem.autoConvey();
        } else { //dump balls
            Robot.intakeSubsystem.outtake();
            Robot.conveyorSubsystem.reverse();
        }
        if (timer.get()>time) {
            Robot.intakeSubsystem.idle();
            Robot.conveyorSubsystem.idle();
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
        Robot.intakeSubsystem.idle();
        Robot.conveyorSubsystem.idle();
        Robot.driveTrain.stop();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}