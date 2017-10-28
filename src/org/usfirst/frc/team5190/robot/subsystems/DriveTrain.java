package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.TeleDriveWithJoystick;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends Subsystem {

	public DriveTrain() {
		super("Drive Train");

		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Jaguar) RobotMap.frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Rear Left Motor", (Jaguar) RobotMap.rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar) RobotMap.frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Rear Right Motor", (Jaguar) RobotMap.rearRightMotor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", RobotMap.leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", RobotMap.rightEncoder);
		LiveWindow.addSensor("Drive Train", "NavX", RobotMap.navx);
	}
	
	public void initialize()
	{
		
	}

	public void end()
	{
		RobotMap.drive.arcadeDrive(0, 0);		
	}
	
	public boolean onTarget()
	{
		return false;
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleDriveWithJoystick());
	}

	public void drive(Joystick joy) {
		RobotMap.drive.arcadeDrive(joy);
	}

//	public double getDistance() {
//		return (RobotMap.leftEncoder.getDistance() + RobotMap.rightEncoder.getDistance()) / 2;
//	}
//
//	public double getDistanceToObstacle() {
//		return rangefinder.getAverageVoltage();
//		return 0;
//	}	
}