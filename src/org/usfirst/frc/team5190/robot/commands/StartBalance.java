/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StartBalance extends Command {

	public StartBalance() {
		super("StartBalance");
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - StartBalance");
		// Reset the gyro on the drive train
		Robot.drivetrain.reset();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.autoBalanceOnTeeterTotter();
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.isBalanced();
	}

	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
		System.out.println("Leaving command - StartBalance");
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.drive(0, 0);
		System.out.println("Cancelling command - StartBalance");
	}
}
