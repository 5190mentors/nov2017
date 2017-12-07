package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainJoystickDrive extends Command {

	public DriveTrainJoystickDrive() {
		super("DriveTrainJoystickDrive");
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - DriveTrainJoyStickDrive");
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.drive(Robot.oi.getJoystick());
	}

	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - DriveTrainJoyStickDrive");
	}
	
	@Override
	protected void interrupted() {
		System.out.println("Cancelling command - DriveTrainJoyStickDrive");
	}
}
