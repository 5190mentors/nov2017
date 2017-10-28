package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Make sure the robot is in a state to pickup soda cans.
 */
public class PrepareToPickup extends CommandGroup {
	public PrepareToPickup() {
		addSequential(new OpenClaw());
		addSequential(new SetElevatorSetpoint(0));
	}
}
