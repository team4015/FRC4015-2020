/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.NumberConstants;
import frc.robot.RobotMap;

public class ShooterSubsystem extends Subsystem {
    private Victor shooterMotor;

    public ShooterSubsystem() {
        shooterMotor = new Victor(RobotMap.SHOOTER_VICTOR);
    }
    
    public void shootFar(){
      shooterMotor.set(NumberConstants.SHOOTER_SPEED_FAR);
    }

    public void shootClose(){
      shooterMotor.set(NumberConstants.SHOOTER_SPEED_CLOSE);
    }

    public void antiShoot(){
      shooterMotor.set(-NumberConstants.SHOOTER_SPEED_FAR);
    }
    public void idle(){
        shooterMotor.stopMotor();
    }
    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
  }