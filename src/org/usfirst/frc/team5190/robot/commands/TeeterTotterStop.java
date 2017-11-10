/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeeterTotterStop extends Command {

	public TeeterTotterStop() {
		super("TeeterTotterStop");
		requires(Robot.drivetrain);
		requires(Robot.teeterTotter);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - TeeterTotterStop");
		Robot.teeterTotter.stop();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - TeeterTotterStop");
	}
}
