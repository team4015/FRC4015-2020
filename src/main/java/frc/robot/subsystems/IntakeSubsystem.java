/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.NumberConstants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  PWMSparkMax intakeSpark;
  // Spark intakeSpark;
  public IntakeSubsystem() {
    intakeSpark = new PWMSparkMax(RobotMap.INTAKE_SPARK);
  }

  public void intakeShooter(){
    intakeSpark.setSpeed(-NumberConstants.INTAKE_SPEED);
  }

  public void intake(){
    intakeSpark.setSpeed(-NumberConstants.INTAKE_SPEED);
  }

  public void outtake(){
    intakeSpark.setSpeed(NumberConstants.INTAKE_SPEED);
  }

  public void idle(){
    intakeSpark.stopMotor();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
