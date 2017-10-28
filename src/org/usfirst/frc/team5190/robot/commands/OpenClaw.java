/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team5190.robot.Robot;

public class OpenClaw extends TimedCommand {
	public OpenClaw() {
		super("OpenClaw", 1);
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - OpenClaw");
		Robot.endPIDloops();
		Robot.claw.open();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - OpenClaw");
	}
}
