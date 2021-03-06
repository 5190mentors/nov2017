package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class ClawClose extends TimedCommand {
	public ClawClose() {
		super("CloseClaw", 1);
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - CloseClaw");
		Robot.claw.close();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - CloseClaw");
	}
}
