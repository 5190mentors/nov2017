/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopTeeterTotter extends Command {

	public StopTeeterTotter() {
		super("StopTeeterTotter");
		requires(Robot.drivetrain);
		requires(Robot.teeterTotter);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - StopTeeterTotter");
		// is the following needed? Wouldn't the interruption to autoteetertotter command do this anyway?
		Robot.teeterTotter.end();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - StopTeeterTotter");
	}
}
