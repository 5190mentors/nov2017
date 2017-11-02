/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.subsystems.TeeterTotter.Stage;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTeeterTotter extends Command {
	public AutoTeeterTotter() {
		super("AutoTeeterTotter");
		requires(Robot.drivetrain);
		requires(Robot.teeterTotter);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - AutoTeeterTotter");
		Robot.teeterTotter.initialize(Stage.STRAIGHT_DRIVE);
	}

	@Override
	protected void execute() {
		// Nothing to execute. PID on teeter totter takes care of driving
	}

	@Override
	protected boolean isFinished() {
		// the only way to come out of this command is to run the stopteetertotter command
		return false;
	}

	@Override
	protected void end() {
		Robot.teeterTotter.end();
		System.out.println("Leaving command - AutoTeeterTotter");
	}

	@Override
	protected void interrupted() {
		Robot.teeterTotter.end();
		System.out.println("Cancelling command - AutoTeeterTotter");
	}
}
