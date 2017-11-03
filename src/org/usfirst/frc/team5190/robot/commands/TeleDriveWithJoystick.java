package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleDriveWithJoystick extends Command {

	public TeleDriveWithJoystick() {
		super("ArcadeDriveWithJoyStick");
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - ArcadeDriveWithJoyStick");
		Robot.drivetrain.initialize();
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
		Robot.drivetrain.end();
		System.out.println("Leaving command - ArcadeDriveWithJoyStick");
	}
	
	@Override
	protected void interrupted() {
		Robot.drivetrain.end();
		System.out.println("Cancelling command - ArcadeDriveWithJoyStick");
	}
}
