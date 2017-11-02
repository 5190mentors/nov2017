/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveStraight extends Command {
	public AutoDriveStraight() {
		super("AutoDriveStraight");
		requires(Robot.drivetrain);
		requires(Robot.straightDrive);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - AutoDriveStraight");
		Robot.endPIDloops();
		Robot.straightDrive.initialize();
	}

	@Override
	protected void execute() {
		// Nothing to execute. PID on straight drive takes care of driving
	}

	@Override
	protected boolean isFinished() {
		return Robot.straightDrive.onTarget();
	}

	@Override
	protected void end() {
		// we won't disable the PID loop of balance drive so that it maintains its balance
		// next in line should do this.
//		Robot.straightDrive.end();
		System.out.println("Leaving command - AutoDriveStraight");
	}

	@Override
	protected void interrupted() {
		Robot.straightDrive.end();
		System.out.println("Cancelling command - AutoDriveStraight");
	}
}
