/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int LEFT_MOTOR_FRONT = 3;
  public static final int LEFT_MOTOR_REAR = 5;
  public static final int RIGHT_MOTOR_FRONT = 4;
  public static final int RIGHT_MOTOR_REAR = 8;

  public static final int INTAKE_SPARK = 7; 
  public static final int CONVEYOR_SPARK = 9;
  public static final int SHOOTER_VICTOR = 2;

  public static final int CONTROLLER_XBOX_PORT_DRIVER = 1;
  public static final int CONTROLLER_XBOX_PORT_SUBSYSTEM = 0;

  //PISTONS
  public static final int SHOOTER_PISTON_DEPLOY = 2;
  public static final int SHOOTER_PISTON_RETRACT = 3;
  public static final int INTAKE_PISTON_DEPLOY = 7;
  public static final int INTAKE_PISTON_RETRACT = 6;
  public static final int CLIMBER_PISON_DEPLOY = 1; //tbd
  public static final int CLIMBER_PISON_RETRACT = 0;
  //RING LIGHT
  public static final int RING_LIGHT = 5;//tbd

  //Subsystem control buttons
  public static final int CLOSE_SHOOT_BUTTON = 2;
  public static final int FAR_SHOOT_BUTTON = 3;
  public static final int TOGGLE_INTAKE_BUTTON = 1;
  public static final int TOGGLE_CLIMBER_BUTTON = 4;
  public static final int REVERSE_BALLS_BUTTON = 8;
  public static final int CONVEYOR_OVERRIDE_BUTTON = 7;



}
