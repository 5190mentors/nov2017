/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorStop extends Command {
	
	public ElevatorStop() {
		super("ElevatorStop");
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - ElevatorStop");
		Robot.elevator.stop();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
		System.out.println("Leaving command - ElevatorStop");
	}	
}
