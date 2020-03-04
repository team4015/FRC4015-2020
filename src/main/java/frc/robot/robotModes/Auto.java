package frc.robot.robotModes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TeleDrive;
import frc.robot.commands.auto.AutoConveyor;
import frc.robot.commands.auto.AutoDrive;
import frc.robot.commands.auto.AutoHoodPiston;
import frc.robot.commands.auto.AutoIntakePiston;
import frc.robot.commands.auto.AutoShooter;
import frc.robot.commands.IntakeManager;
import frc.robot.commands.IntakePistonManager;
import frc.robot.NumberConstants;
import frc.robot.Robot;

public class Auto extends CommandGroup
{
	public Auto()
	{
		addParallel(new AutoShooter(true),5);
		addParallel(new AutoHoodPiston(true));
		addParallel(new AutoConveyor(true),5);
		addSequential(new AutoDrive(0.3, 0.0),1);
		addSequential(new AutoDrive(0.0, 0.5),2);
		addSequential(new AutoIntakePiston(true));
		
    }
	
}