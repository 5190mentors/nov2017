/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command {
	public CloseClaw() {
		super("CloseClaw");
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - CloseClaw");
		Robot.endPIDloops();
		Robot.claw.close();
	}

	@Override
	protected boolean isFinished() {
		return Robot.claw.isGrabbing();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - CloseClaw");
	}
}
