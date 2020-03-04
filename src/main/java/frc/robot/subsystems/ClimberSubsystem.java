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

public class ClimberSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Piston climberPiston;

  public ClimberSubsystem() {
    climberPiston = new Piston(new Solenoid(RobotMap.CLIMBER_PISON_DEPLOY), new Solenoid(RobotMap.CLIMBER_PISON_RETRACT), "Climber");
  }
  public boolean getClimberExtended() {
    return climberPiston.extended;
  }
  public void extendClimber() {
    climberPiston.extend();
  }
  public void retractClimber() {
    climberPiston.retract();
  }
  public void toggleClimber() {
      climberPiston.toggle();
  }
  public void disableClimber() {
    climberPiston.disable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
