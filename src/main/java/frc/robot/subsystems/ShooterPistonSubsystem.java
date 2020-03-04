/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.Piston;

public class ShooterPistonSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Piston shooterPiston;

  public ShooterPistonSubsystem() {
    shooterPiston = new Piston(new Solenoid(RobotMap.SHOOTER_PISTON_DEPLOY), new Solenoid(RobotMap.SHOOTER_PISTON_RETRACT), "Shooter");
  }
  public boolean getShooterExtended() {
    return shooterPiston.extended;
  }
  public void extendShooter() {
    shooterPiston.extend();
  }
  public void retractShooter() {
    shooterPiston.retract();
  }
  public void toggleShooter() {
      shooterPiston.toggle();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
