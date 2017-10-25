/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopBalance extends Command {

	public StopBalance() {
		super("StopBalance");
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - StopBalance");
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - StopBalance");
	}
}
