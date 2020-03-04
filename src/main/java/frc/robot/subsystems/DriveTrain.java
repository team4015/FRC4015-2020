/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.NumberConstants;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Talon leftTalonFront;
  public Talon leftTalonRear;
  public Talon rightTalonFront;
  public Talon rightTalonRear; 
  

  private SpeedControllerGroup leftSide,rightSide;
  private DifferentialDrive robotDrive;
  
  public DriveTrain() {
    leftTalonFront = new Talon(RobotMap.LEFT_MOTOR_FRONT);
    leftTalonRear = new Talon(RobotMap.LEFT_MOTOR_REAR);
    rightTalonFront = new Talon(RobotMap.RIGHT_MOTOR_FRONT);
    rightTalonRear = new Talon(RobotMap.RIGHT_MOTOR_REAR);
    leftSide = new SpeedControllerGroup(leftTalonFront, leftTalonRear);
    rightSide = new SpeedControllerGroup(rightTalonFront, rightTalonRear);
    robotDrive = new DifferentialDrive(leftSide, rightSide);
  }

  public void stop() {
    drive(0,0,true);    
  }
  
  public void drive(double speed, double turn, boolean quickTurn) {
    robotDrive.curvatureDrive(speed, turn/NumberConstants.TURNING_CONSTANT,quickTurn);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}