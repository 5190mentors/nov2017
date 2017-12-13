package org.usfirst.frc.team5190.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class RobotMap {
	
	// DRIVE TRAIN
	public static final int spFrontLeft = 0;				// port configuration for drive motors
	public static final int spRearLeft = 2;
	public static final int spFrontRight = 1;
	public static final int spRearRight = 3;
	
	public static SpeedController frontLeftMotor;
	public static SpeedController rearLeftMotor;
	public static SpeedController frontRightMotor;
	public static SpeedController rearRightMotor;
	public static RobotDrive drive;
	public static boolean reverseDrive = false;				// turn this on to reverse all driving
	
	public static final boolean enableNavX = true;			// turn this off for simulation
	public static final byte kNavUpdateHz = 20;
	public static AHRS navx;

	public static final boolean enableEncoders = false;		// turn this off if there are no encoders on drivetrain	
	public static final int dtLeftEncPortA = 0;
	public static final int dtLeftEncPortB = 1;
	public static final int dtRightEncPortA = 2;
	public static final int dtRightEncPortB = 3;
	public static final int kRadiusInInches = 2;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;

	// TEETER TOTTER
	public static final double kPsd = 0.2; // 0.3;			// .08 X (0 - 8) = -0.64, .08 X (4 - 8) = -0.32
	public static final double kIsd = 0;
	public static final double kDsd = 0; //0.3;
	public static final double kFsd = 0; //0.001;
	public static final double kMinPitch = 5;				// set this to be less than the angle of inclination of teeter totter
	public static final double kPbd = 0.03;					// .03 X (8 - 0) = 0.24, .03 X (4 - 0) = .12
	public static final double kIbd = 0;
	public static final double kDbd = 0.1;
	public static final double kFbd = 0;

	// CLAW
	public static final boolean enableClaw = true;			// turn this off if the robot does not have a claw
	public static final int dvPcm = 41;						// device id of PCM
	public static final int spGrip = 0;						// port on PCM that the solenoid is connected to
	public static Solenoid grip;
	
	// ELEVATOR
	public static final boolean enableElevator = true;		// turn this off if the robot does not have an elevator
	public static final int spElevatorMotor = 1;
	public static final boolean reverseElevator = false;	// set this to change the direction of elevator
	public static final double kElevSpeed = 0.5;
	public static SpeedController elevatorMotor;

	// ELEVATOR with POT
	public static final boolean enableElevWithPot = false;	// turn this off if the robot does not have an elevator with Pot
	public static final int spElevatorPot = 4;
	public static final double kLow = 1;
	public static final double kMiddle = 2.5;
	public static final double kHigh = 4.5;
	public static final double kPel = 1; // 18;
	public static final double kIel = 0; //0.07;
	public static final double kDel = 0; //0.2;
	public static Potentiometer elevatorPot;
}