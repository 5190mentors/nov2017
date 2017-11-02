package org.usfirst.frc.team5190.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class RobotMap {
	
	// DRIVE TRAIN
	public static final int spFrontLeft = 0;
	public static final int spRearLeft = 2;
	public static final int spFrontRight = 1;
	public static final int spRearRight = 3;
	public static SpeedController frontLeftMotor;
	public static SpeedController rearLeftMotor;
	public static SpeedController frontRightMotor;
	public static SpeedController rearRightMotor;
	public static RobotDrive drive;

	public static final boolean enableNavX = true;
	public static final byte kNavUpdateHz = 20;
	public static AHRS navx;

	public static final boolean enableEncoders = false;
	public static final int dtLeftEncPortA = 0;
	public static final int dtLeftEncPortB = 1;
	public static final int dtRightEncPortA = 2;
	public static final int dtRightEncPortB = 3;
	public static final int kRadiusInInches = 2;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;

	// STRAIGHT DRIVE
	public static final double kPsd = 0.07; //0.1;
	public static final double kIsd = 0;
	public static final double kDsd = 0; //0.3;
	public static final double kFsd = 0; //0.001;
	public static final double kMaxPitch = 9;
	public static final double kMaxSpeed = 0.15;
	
	// BALANCE DRIVE
	public static final double kPbd = kMaxSpeed / kMaxPitch;
	public static final double kIbd = 0;
	public static final double kDbd = 0;
	public static final double kFbd = 0;

	// CLAW
	public static final boolean enableClaw = false;
	public static final int spGrip = 0;
	public static Solenoid grip; 
	
	// ELEVATOR
	public static final boolean enableElevator = false;
	public static final int spElevatorMotor = 4;
	public static final int spElevatorPot = 4;
	public static final double kPel = 1; // 18;
	public static final double kIel = 0; //0.07;
	public static final double kDel = 0; //0.2;
	public static SpeedController elevatorMotor;
	public static Potentiometer elevatorSensor;

//	public static boolean leftEncoderDisabled = false;
//	public static boolean rightEncoderDisabled = false;
//	public static boolean useDrivePIDinAuto = false;
//	public static boolean shooterUsePot = false;
//	public static boolean teleopUseGyro = true;
//	public static int autoStartStation = 1;
//	public static double xStart = 0.0;
//	public static double yStart = 0.0;
//	public static double xAutoShootPosition = 0.0;
//	public static double yAutoShootPosition = 0.0;
//	public static double autoShootAngle = 10.0;
//	public static double autoDriveAngle = 10.0;
//	public static double autoDriveAngle2 = 80.0;
//	public static double autoDriveDistance = 5.0;

//	public static final boolean dtLeftEncReversed = true;
//	public static final boolean dtRightEncReversed = false;
//	
//	// start with this dtP.  Crank up if unresponsive, down if noise.  
//	// Then add dtD to eliminate noise.  dtI doesn't do anything
//	public static double dtP = 2.0, dtI = 0.0, dtD = 0.0, dtF = 0.02;
//	public static double distP = 1.0, distI = 0.2, distD = 0.0;
//	public static boolean teleopArcadeDrive = true;
//	
//	// MOTORS, SERVOS, ENCODERS, GYRO
//	/** Jaguars */
//	public static Jaguar dtLeft, dtRight, shootL, shootR, shootA;
//	/** Servo */
//	public static Servo shootK;
//	/** Encoders */
//	public static Encoder dtLeftEnc, dtRightEnc, shootEnc;
//	public static Potentiometer pot;
//	public static int uIn = 6;
//	public static int uOut = 7;
//
//	//##############
//	// ROBOT VISION
//	//##############
//	public static CameraServer camera;
//	
//	//############
//	// LED COLORS
//	//############
//	public static Alliance alliance;
//	public static I2C wire;
//	
//	public static final String patWait = "rainbow"; // Chroma, white chaser
//	public static final String patBlue = "blue"; // Solid blue
//	public static final String patRed = "red"; // Solid red
//	public static final String patTest = "orangeBlink"; // Orange<-->off slow fade
//	public static final String patNone = "yellow"; // Solid yeller
//	
//
//	/**
//	 * NAVX Gyro & PID
//	 * 
//	 */
//	// Might want to try some I term in here with new method.
//	public static double navP = 0.05, navI = 0.01, navD = 0.0, navF = 0.0;
//	public static double navRateP = 0.05, navRateI = 0.0, navRateD = 0.0, navRateF = 0.0;
//	public static double yawOffset = 0.0;
//
//	/**
//	 * SHOOTER VARIABLES
//	 */
//	public static final int spShootL = 2;
//	public static final int spShootR = 3;
//	public static final int spShootA = 4;
//	public static final int kickerP = 5;
//	
//	public static final int encShootA = 4;
//	public static final int encShootB = 5;
//	
//	public static final double shooterP = 0.1;
//	public static final double shooterI = 0.01;
//	public static final double shooterD = 0.004;
//	
//	public static final double spinUp = .75;
//	public static final double pitchUpVolts = .3;  // NOTE: This is reversed!
//	public static final double pitchDownVolts = -.4; // NOTE: This is reversed!
//	public static final double suckLVolts = .5;
//	public static final double suckRVolts = -.5;
	
//	
//	// Logging
//	public static final boolean loggingEnabled = false;
//	public static boolean redAlliance = false;
}