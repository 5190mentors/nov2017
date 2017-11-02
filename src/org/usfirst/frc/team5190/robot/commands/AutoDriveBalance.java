/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveBalance extends Command {
	public AutoDriveBalance() {
		super("AutoDriveBalance");
		requires(Robot.drivetrain);
		requires(Robot.balanceDrive);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - AutoDriveBalance");
		Robot.endPIDloops();
		Robot.balanceDrive.initialize();
	}

	@Override
	protected void execute() {
		// Nothing to execute. PID on balance drive takes care of driving
	}

	@Override
	protected boolean isFinished() {
		return Robot.balanceDrive.onTarget();
	}

	@Override
	protected void end() {
		// we won't disable the PID loop of balance drive so that it maintains its balance
		// next in line should do this.
		// Robot.balanceDrive.end();
		System.out.println("Leaving command - AutoDriveBalance");
	}

	@Override
	protected void interrupted() {
		Robot.balanceDrive.end();
		System.out.println("Cancelling command - AutoDriveBalance");
	}
}
