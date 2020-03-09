
package frc.robot.robotModes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TeleDrive;
import frc.robot.commands.auto.AutoAlign;
import frc.robot.commands.ClimberManager;
import frc.robot.commands.ConveyorManager;
import frc.robot.commands.IntakeManager;
import frc.robot.commands.IntakePistonManager;
import frc.robot.commands.RingLightManager;
import frc.robot.commands.ShooterManager;
import frc.robot.commands.ShooterPistonManager;

public class Teleop extends CommandGroup {

    public Teleop() {
        // addParallel(new TeleDrive());
        addParallel(new AutoAlign());
        addParallel(new ClimberManager());
        addParallel(new IntakeManager());
        addParallel(new IntakePistonManager());
        addParallel(new ShooterPistonManager());
        addParallel(new ShooterManager());
        addParallel(new ConveyorManager());
        addParallel(new RingLightManager());
        


    }

}