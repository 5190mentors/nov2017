package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.TeleDriveWithJoystick;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	public DriveTrain() {
		super("Drive Train");

		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Jaguar) RobotMap.frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Rear Left Motor", (Jaguar) RobotMap.rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar) RobotMap.frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Rear Right Motor", (Jaguar) RobotMap.rearRightMotor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", RobotMap.leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", RobotMap.rightEncoder);
		
		if (RobotMap.enableNavX)
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

	public void updateSmartDashboard() {
		SmartDashboard.putNumber(	"DriveTrain.LeftEncoder", 		RobotMap.leftEncoder.getDistance());
		SmartDashboard.putNumber(	"DriveTrain.RightEncoder", 		RobotMap.rightEncoder.getDistance());

		if (RobotMap.enableNavX)
		{
			SmartDashboard.putBoolean(  "DriveTrain.NavX.Connected",	RobotMap.navx.isConnected());
	        SmartDashboard.putBoolean(  "DriveTrain.NavX.IsCalibrating",RobotMap.navx.isCalibrating());
	        SmartDashboard.putNumber(   "DriveTrain.NavX.Yaw",			RobotMap.navx.getYaw());
	        SmartDashboard.putNumber(   "DriveTrain.NavX.Pitch",		RobotMap.navx.getPitch());
	        SmartDashboard.putNumber(   "DriveTrain.NavX.Roll",			RobotMap.navx.getRoll());
	        
	        /* Display tilt-corrected, Magnetometer-based heading (requires             */
	        /* magnetometer calibration to be useful)                                   */
	        
	        SmartDashboard.putNumber(   "DriveTrain.NavX.CompassHeading",   RobotMap.navx.getCompassHeading());
	        
	        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
	        SmartDashboard.putNumber(   "DriveTrain.NavX.FusedHeading",     RobotMap.navx.getFusedHeading());
	
	        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
	        /* path for upgrading from the Kit-of-Parts gyro to the NavX-MXP            */
	        
	        SmartDashboard.putNumber(   "DriveTrain.NavX.TotalYaw",         RobotMap.navx.getAngle());
	        SmartDashboard.putNumber(   "DriveTrain.NavX.YawRateDPS",       RobotMap.navx.getRate());
	        SmartDashboard.putBoolean(  "DriveTrain.NavX.IsMoving",         RobotMap.navx.isMoving());
	        SmartDashboard.putBoolean(  "DriveTrain.NavX.IsRotating",       RobotMap.navx.isRotating());
		}
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