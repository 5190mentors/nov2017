package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTeeterTotter extends CommandGroup {
	public AutoTeeterTotter() {
		addSequential(new AutoDriveStraight());		
		addSequential(new AutoDriveBalance());
	}
}
