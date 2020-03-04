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

public class IntakePistonSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Piston intakePiston;

  public IntakePistonSubsystem() {
    intakePiston = new Piston(new Solenoid(RobotMap.INTAKE_PISTON_DEPLOY), new Solenoid(RobotMap.INTAKE_PISTON_RETRACT), "Intake");
  }
  public boolean getIntakeExtended() {
    return intakePiston.extended;
  }
  public void extendIntake() {
    intakePiston.extend();
  }
  public void retractIntake() {
    intakePiston.retract();
  }
  public void toggleIntake() {
      intakePiston.toggle();
  }
  public void disableIntake() {
    intakePiston.disable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
