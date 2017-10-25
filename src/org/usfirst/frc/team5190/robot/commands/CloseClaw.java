/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5190.robot.Robot;

/**
 * Closes the claw for one second. Real robots should use sensors, stalling
 * motors is BAD!
 */
public class CloseClaw extends Command {
	public CloseClaw() {
		super("CloseClaw");
		requires(Robot.claw);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - CloseClaw");
		Robot.claw.close();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.claw.isGrabbing();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// NOTE: Doesn't stop in simulation due to lower friction causing the
		// can to fall out
		// + there is no need to worry about stalling the motor or crushing the
		// can.
		if (!Robot.isSimulation())
			Robot.claw.stop();
		System.out.println("Leaving command - CloseClaw");
	}
}
