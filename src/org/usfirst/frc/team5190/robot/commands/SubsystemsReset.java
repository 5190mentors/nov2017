/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team5190.robot.Robot;

public class SubsystemsReset extends TimedCommand {
	public SubsystemsReset() {
		super("ResetSubsystems", 1);
		requires(Robot.drivetrain);
		requires(Robot.teeterTotter);
		requires(Robot.claw);
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - ResetSubsystems");		
		Robot.teeterTotter.reset();
		Robot.elevator.reset();
		Robot.claw.reset();
		Robot.drivetrain.reset();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - ResetSubsystems");
	}
}
