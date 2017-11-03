package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Pickup a soda can (if one is between the open claws) and get it in a safe
 * state to drive around.
 */
public class Pickup extends CommandGroup {
	public Pickup() {
		addSequential(new CloseClaw());
		addSequential(new SetElevatorSetpoint(Elevator.Height.MIDDLE));
	}
}
