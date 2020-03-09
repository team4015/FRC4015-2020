package frc.robot;

public class NumberConstants {
    //Drivetrain Constants
    public static final double SLOWING_CONSTANT = 2;
    public static final double TURNING_CONSTANT = 1.3;
    //Auto Speed Constants

    // public static final double AUTO_SPEED = 0.25;
    // public static final double AUTO_TURNING_SPEED = 0.5;
    // public static final double AUTO_TURN_TIME= 1.3; //for 90 degree turn
    //Subsystem constants
    public static final double INTAKE_SPEED = 0.8;  
    public static final double CONVEYOR_SPEED = 1.0;
    public static final double CONVEYOR_REVERSE_SPEED = 0.8;
    public static final double CONVEYOR_SHOOT_SPEED = 1.0;
    public static final double SHOOTER_SPEED_FAR = -1.0*0.95;
    public static final double SHOOTER_SPEED_CLOSE = -1.0*0.82;

    public static final double AUTOCONVEY_TIME = 0.3; //override limit switch safety timer
    public static final double SHOOTER_DELAY_BETWEEN_BALLS = 0.35;
    public static final double REVUP_TIME = 2;
    public static final double DRIVEBACK_TIME = 0.3;


    //Encoder constants
    
    //Vision Constants
    public static final int IMG_WIDTH = 160;
	public static final int IMG_HEIGHT = 120;
}