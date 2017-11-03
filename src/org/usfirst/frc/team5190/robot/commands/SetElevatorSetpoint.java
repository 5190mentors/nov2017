/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.subsystems.Elevator;
import org.usfirst.frc.team5190.robot.subsystems.ElevatorUsingPot;

import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorSetpoint extends Command {
	private Elevator.Height setpoint;

	public SetElevatorSetpoint(Elevator.Height setpoint) {
		super("Elevator");
		requires(Robot.elevator);
		this.setpoint = setpoint;
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - SetElevatorSetpoint");
		if (RobotMap.enableElevator)
			((ElevatorUsingPot) Robot.elevator).initialize(setpoint);
		else
			((Elevator) Robot.elevator).initialize(setpoint);
	}

	@Override
	protected void execute() {
		// Nothing to execute. PID on straight drive takes care of execution
	}

	@Override
	protected boolean isFinished() {
		if (RobotMap.enableElevator)
			return ((ElevatorUsingPot) Robot.elevator).onTarget();
		else
			return ((Elevator) Robot.elevator).onTarget();
	}
	
	@Override
	protected void end() {
		System.out.println("Leaving command - SetElevatorSetpoint");
		if (RobotMap.enableElevator)
			((ElevatorUsingPot) Robot.elevator).end();
		else
			((Elevator) Robot.elevator).end();
	}	
}
