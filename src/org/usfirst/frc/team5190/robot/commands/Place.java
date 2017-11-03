package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Place a held soda can onto the platform.
 */
public class Place extends CommandGroup {
	public Place() {
		addSequential(new SetElevatorSetpoint(Elevator.Height.MIDDLE));
		addSequential(new OpenClaw());
	}
}
