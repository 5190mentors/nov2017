package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.commands.TeeterTotterStart;
import org.usfirst.frc.team5190.robot.subsystems.Claw;
import org.usfirst.frc.team5190.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.Elevator;
import org.usfirst.frc.team5190.robot.subsystems.TeeterTotter;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;

	public static DriveTrain drivetrain;
	public static TeeterTotter teeterTotter;	
	public static Elevator elevator;
	public static Claw claw;
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// Initialize all actuators and sensors
		RobotMap.frontLeftMotor = new Jaguar(RobotMap.spFrontLeft);
		RobotMap.rearLeftMotor = new Jaguar(RobotMap.spRearLeft);
		RobotMap.frontRightMotor = new Jaguar(RobotMap.spFrontRight);
		RobotMap.rearRightMotor = new Jaguar(RobotMap.spRearRight);
		RobotMap.drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);

		if (RobotMap.enableEncoders) {
			RobotMap.leftEncoder = new Encoder(RobotMap.dtLeftEncPortA, RobotMap.dtLeftEncPortB);
			RobotMap.rightEncoder = new Encoder(RobotMap.dtRightEncPortA, RobotMap.dtRightEncPortA);
			RobotMap.leftEncoder.setDistancePerPulse(0.01745 * RobotMap.kRadiusInInches);
			RobotMap.rightEncoder.setDistancePerPulse(0.01745 * RobotMap.kRadiusInInches);
		}

		if (RobotMap.enableNavX)
			RobotMap.navx = new AHRS(SPI.Port.kMXP, RobotMap.kNavUpdateHz);

		if (RobotMap.enableClaw)
			RobotMap.grip = new Solenoid(RobotMap.dvPcm, RobotMap.spGrip);
		
		if (RobotMap.enableElevator) {
			RobotMap.elevatorMotor = new Victor(RobotMap.spElevatorMotor);
		}

		// Initialize all subsystems
		drivetrain = new DriveTrain();
		teeterTotter = new TeeterTotter();
		elevator = new Elevator();		
		claw = new Claw();
		oi = new OI();
		
		// instantiate the command used for the autonomous period
		autonomousCommand = new TeeterTotterStart();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(teeterTotter);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(claw);		
	}

	@Override
	public void autonomousInit() {
		// there is no timing in this now
		autonomousCommand.start(); // schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	@Override
	public void disabledInit() {
		teeterTotter.reset();
		elevator.reset();
		claw.reset();		
		drivetrain.reset();
	}
}
