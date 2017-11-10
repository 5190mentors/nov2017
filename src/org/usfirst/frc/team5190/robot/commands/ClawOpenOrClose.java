package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team5190.robot.Robot;

public class ClawOpenOrClose extends TimedCommand {
	public ClawOpenOrClose() {
		super("ClawOpenOrClose", 1);
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - ClawOpenOrClose");
		if (Robot.claw.isGrabbing())
			Robot.claw.open();
		else
			Robot.claw.close();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - ClawOpenOrClose");
	}
}
