package frc.robot.robotModes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.auto.AutoDrive;
import frc.robot.commands.auto.AutoIntake;
import frc.robot.commands.auto.AutoIntakePiston;
import frc.robot.commands.auto.AutoShooter;

import frc.robot.NumberConstants;
import frc.robot.Robot;

public class Auto extends CommandGroup
{
	public Auto()
	{//FACING TARGET ORIENTATION LEFT AND RIGHT
		addSequential(new AutoShooter(true,5));

		//LEFT SIDE AUTO, angled slightly clockwise, just turn a bit more clockwise and drive into center balls
		//ends up at middle of field
		// addSequential(new AutoDrive(0.3, 0.0,1)); 
		// addSequential(new AutoDrive(0.0, 0.5,0.3));
		// addSequential(new AutoDrive(0.3, 0.0,2));//end up on other team rendezvous point
		// addSequential(new AutoDrive(0.0, -0.5,0.5)); 

		//MIDDLE AUTO, Going LEFT to center of field
		// addSequential(new AutoDrive(0.3, 0.0,1.5)); 
		// addSequential(new AutoDrive(0.0, 0.5,0.45));
		// addSequential(new AutoDrive(0.3, 0.0,1.5));
		// addSequential(new AutoDrive(0.0, -0.5,0.45));

		//MIDDLE AUTO, Going RIGHT to start of trench (coming in at an angle)
		// addSequential(new AutoDrive(0.3, 0.0,2)); 
		// addSequential(new AutoDrive(0.0, -0.5,0.45));
		// addSequential(new AutoDrive(0.3, 0.0,1)); 

		
		//RIGHT SIDE AUTO, angled slightly counterclockwise , just drive straight back and turn a little 
		//ends up in front edge of control panel zone
		addSequential(new AutoDrive(0.3, 0.0,1)); 
		addSequential(new AutoDrive(0.0, 0.5,0.2));
		addSequential(new AutoDrive(0.3, 0.0,1)); 


		//AUTO INTAKE
		// addSequential(new AutoIntakePiston(true));
		// addSequential(new AutoIntake(0.2,true,10)); //drive back to baseline
		
    }
	
}