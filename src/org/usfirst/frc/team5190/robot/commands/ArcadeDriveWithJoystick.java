package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveWithJoystick extends Command {

	public ArcadeDriveWithJoystick() {
		super("ArcadeDriveWithJoyStick");
		requires(Robot.drivetrain);
	}

	// Called once before execution
	@Override
	protected void initialize() {
		System.out.println("Entering command - ArcadeDriveWithJoyStick");
	}
	
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivetrain.drive(Robot.oi.getJoystick());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.drive(0, 0);
		System.out.println("Leaving command - ArcadeDriveWithJoyStick");
	}
	
	// Called once when this command is cancelled
	@Override
	protected void interrupted() {
		Robot.drivetrain.drive(0, 0);
		System.out.println("Cancelling command - ArcadeDriveWithJoyStick");
	}
}
