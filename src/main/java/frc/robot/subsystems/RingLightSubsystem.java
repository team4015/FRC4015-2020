/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class RingLightSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Solenoid ringLight;
  private Timer blinkTimer;

  public RingLightSubsystem() {
    ringLight = new Solenoid(RobotMap.RING_LIGHT);
    blinkTimer = new Timer();
  }
  public void on() {
      ringLight.set(true);
  }

  public void off() {
    ringLight.set(false);
  }
  public void blink(double time) {
    ringLight.set(false);
    blinkTimer.start();
    if (blinkTimer.get()>=time) {
        blinkTimer.stop();
        blinkTimer.reset();
        ringLight.set(true);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
