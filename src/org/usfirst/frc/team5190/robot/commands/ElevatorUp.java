/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorUp extends Command {
	
	public ElevatorUp() {
		super("UpElevator");
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - UpElevator");
	}

	@Override
	protected void execute() {
		Robot.elevator.up();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		System.out.println("Leaving command - UpElevator");
	}	
}
