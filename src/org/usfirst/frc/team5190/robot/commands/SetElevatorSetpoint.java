/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5190.robot.Robot;

public class SetElevatorSetpoint extends Command {
	private double setpoint;

	public SetElevatorSetpoint(double setpoint) {
		super("Elevator");
		requires(Robot.elevator);
		this.setpoint = setpoint;
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - SetElevatorSetpoint");
		Robot.endPIDloops();
		Robot.elevator.initialize(setpoint);
	}

	@Override
	protected void execute() {
		// Nothing to execute. PID on straight drive takes care of execution
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.onTarget();
	}
	
	@Override
	protected void end() {
		// we won't disable the PID loop of balance drive so that it maintains its balance
		// next in line should do this.
		// Robot.elevator.end();
		System.out.println("Leaving command - SetElevatorSetpoint");
	}	
}
