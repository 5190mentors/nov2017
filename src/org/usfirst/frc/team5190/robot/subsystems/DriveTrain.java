package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.DriveTrainJoystickDrive;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends Subsystem {

	public DriveTrain() {
		super("Drive Train");

		LiveWindow.addActuator("Drive Train", "Front Left Motor", (Jaguar) RobotMap.frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Rear Left Motor", (Jaguar) RobotMap.rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar) RobotMap.frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Rear Right Motor", (Jaguar) RobotMap.rearRightMotor);
		
		if (RobotMap.enableEncoders) {
			LiveWindow.addSensor("Drive Train", "Left Encoder", RobotMap.leftEncoder);
			LiveWindow.addSensor("Drive Train", "Right Encoder", RobotMap.rightEncoder);
		}
		
		if (RobotMap.enableNavX)
			LiveWindow.addSensor("Drive Train", "NavX", RobotMap.navx);
		
		if (RobotMap.reverseDrive) {
			RobotMap.drive.setInvertedMotor(MotorType.kFrontLeft, true);
			RobotMap.drive.setInvertedMotor(MotorType.kFrontRight, true);
			RobotMap.drive.setInvertedMotor(MotorType.kRearLeft, true);
			RobotMap.drive.setInvertedMotor(MotorType.kRearRight, true);
		}			

		reset();
	}
	
	public void reset() {		
		if (RobotMap.enableEncoders) {
			RobotMap.leftEncoder.reset();
			RobotMap.rightEncoder.reset();
		}

		if (RobotMap.enableNavX)
			RobotMap.navx.reset();
		
		// stop the motors
		RobotMap.drive.arcadeDrive(0, 0);
		
    	System.out.println("Drive Train: Reset completed");
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveTrainJoystickDrive());
	}

	public void drive(Joystick joy) {
//		RobotMap.drive.arcadeDrive(joy);
		double throttle = joy.getThrottle();
		double y = joy.getY();
		double x = joy.getX();
		System.out.println("Drive Train: " + y + " | " + x + " | " + throttle);
		RobotMap.drive.arcadeDrive(y*throttle, x*throttle);
	}
}