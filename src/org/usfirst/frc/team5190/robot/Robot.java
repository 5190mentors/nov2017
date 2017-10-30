package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.subsystems.BalanceDrive;
import org.usfirst.frc.team5190.robot.subsystems.Claw;
import org.usfirst.frc.team5190.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.Elevator;
import org.usfirst.frc.team5190.robot.subsystems.StraightDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
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
	public static StraightDrive straightDrive;
	public static BalanceDrive balanceDrive;
	
	public static Elevator elevator;
	public static Claw claw;
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Initialize drive train
		RobotMap.frontLeftMotor = new Jaguar(RobotMap.spFrontLeft);
		RobotMap.rearLeftMotor = new Jaguar(RobotMap.spRearLeft);
		RobotMap.frontRightMotor = new Jaguar(RobotMap.spFrontRight);
		RobotMap.rearRightMotor = new Jaguar(RobotMap.spRearRight);

		RobotMap.leftEncoder = new Encoder(RobotMap.dtLeftEncPortA, RobotMap.dtLeftEncPortB);
		RobotMap.rightEncoder = new Encoder(RobotMap.dtRightEncPortA, RobotMap.dtRightEncPortA);
		RobotMap.leftEncoder.setDistancePerPulse(0.01745 * RobotMap.kRadiusInInches);
		RobotMap.rightEncoder.setDistancePerPulse(0.01745 * RobotMap.kRadiusInInches);

		RobotMap.navx = new AHRS(SPI.Port.kMXP, RobotMap.kNavUpdateHz);
		RobotMap.drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);

		if (RobotMap.enableClaw)
		{
			RobotMap.grip = new Solenoid(RobotMap.spGrip);
		}
		
		if (RobotMap.enableElevator)
		{
			RobotMap.elevatorMotor = new Victor(RobotMap.spElevatorMotor);
			RobotMap.elevatorSensor = new AnalogPotentiometer(RobotMap.spElevatorPot, 1.0 / 5.0);
		}
		
		drivetrain = new DriveTrain();
		straightDrive = new StraightDrive();
		balanceDrive = new BalanceDrive();
		elevator = new Elevator();
		claw = new Claw();
		
		oi = new OI();

		// instantiate the command used for the autonomous period
		// autonomousCommand = new Autonomous();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(straightDrive);
		SmartDashboard.putData(balanceDrive);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(claw);		
	}

	@Override
	public void autonomousInit() {
		endPIDloops();
		// autonomousCommand.start(); // schedule the autonomous command (example)
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		// autonomousCommand.cancel();
		endPIDloops();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void testInit() {
		endPIDloops();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void updateSmartDashboard() {
		drivetrain.updateSmartDashboard();
		straightDrive.updateSmartDashboard();
		balanceDrive.updateSmartDashboard();
		elevator.updateSmartDashboard();
		claw.updateSmartDashboard();
	}

	public static void endPIDloops() {
		if (straightDrive.getPIDController().isEnabled())
			straightDrive.end();
		
		if (balanceDrive.getPIDController().isEnabled())
			balanceDrive.end();
		
		if (elevator.getPIDController().isEnabled())
			elevator.end();
	}
}
