package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team5190.robot.Robot;

public class ClawOpen extends TimedCommand {
	public ClawOpen() {
		super("ClawOpen", 1);
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - ClawOpen");
		Robot.claw.open();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - ClawOpen");
	}
}
