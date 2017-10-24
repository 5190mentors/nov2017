package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem {

	private static final int FRONT_LEFT = 0;
	private static final int REAR_LEFT = 2;
	private static final int FRONT_RIGHT = 1;
	private static final int REAR_RIGHT = 3;
	
	private static final double GYRO_CORRECTION_CONSTANT = 1;
	private static final double GYRO_RESPONSE_DELAY = 0.004;
	private static final double BALANCING_MIN_TIME_CHECK = 1;
	private static final double BALANCING_MAX_TIME_CHECK = 20;
	private static final double BALANCING_TOLERANCE_LIMIT = 5;
	
	private SpeedController frontLeftMotor = new Jaguar(FRONT_LEFT);
	private SpeedController rearLeftMotor = new Jaguar(REAR_LEFT);
	private SpeedController frontRightMotor = new Jaguar(FRONT_RIGHT);
	private SpeedController rearRightMotor = new Jaguar(REAR_RIGHT);

	private RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

//	private Encoder leftEncoder = new Encoder(1, 2);
//	private Encoder rightEncoder = new Encoder(3, 4);
//	private AnalogInput rangefinder = new AnalogInput(6);
//	private AnalogGyro gyro = new AnalogGyro(1);
	private Timer balancingTimer = new Timer();

	public DriveTrain() {
		super();

		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
//		if (Robot.isReal()) {
//			leftEncoder.setDistancePerPulse(0.042);
//			rightEncoder.setDistancePerPulse(0.042);
//		} else {
//			// Circumference in ft = 4in/12(in/ft)*PI
//			leftEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
//			rightEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
//		}
		
		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Jaguar) frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Jaguar) rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar) frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Jaguar) rearRightMotor);
//		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
//		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
//		LiveWindow.addSensor("Drive Train", "Rangefinder", rangefinder);
//		LiveWindow.addSensor("Drive Train", "Gyro", gyro);
	}

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveWithJoystick());
	}

	public void log() {
		SmartDashboard.putString("Drive Train", "Hello");
//		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
//		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
//		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
//		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
//		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}

	public void drive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}

	public void drive(Joystick joy) {
		drive.arcadeDrive(joy);
	}

	public double getHeading() {
//		return gyro.getAngle();
		return 0;
	}

	public void reset() {
//		gyro.reset();
		balancingTimer.reset();
//		leftEncoder.reset();
//		rightEncoder.reset();
	}

	public double getDistance() {
//		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return 0;
	}

	public double getDistanceToObstacle() {
//		// Really meters in simulation since it's a rangefinder...
//		return rangefinder.getAverageVoltage();
		return 0;
	}
	
	public void autoBalanceOnTeeterTotter() {
//		double pitchAngleDegrees = getHeading();
//        double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
//        double yAxisRate = Math.sin(pitchAngleRadians) * GYRO_CORRECTION_CONSTANT;
//        drive(yAxisRate, 0);
//        Timer.delay(GYRO_RESPONSE_DELAY);		// wait for a motor update time    
    }
	
	public boolean isBalanced() {
		double elapsed = balancingTimer.get();
		return elapsed > BALANCING_MAX_TIME_CHECK || 
				(elapsed > BALANCING_MIN_TIME_CHECK && getHeading() < BALANCING_TOLERANCE_LIMIT);
	}
}