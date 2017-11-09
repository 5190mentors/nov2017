package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class TeeterTotter extends PIDSubsystem {

	private double m_setPoint = 0;
	private double m_horizontalPitch = 0;
	private double m_tolerance = 0.5;
	private enum Stage {STRAIGHT_DRIVE, BALANCE_DRIVE};
	private Stage m_currentStage = Stage.STRAIGHT_DRIVE;
	
    public TeeterTotter() {
    	super("Teeter Totter", RobotMap.kPsd, RobotMap.kIsd, RobotMap.kDsd, RobotMap.kFsd);
    	
    	LiveWindow.addActuator("Teeter Totter", "PID Controller", getPIDController());
    	
    	reset();
    }

    // call this only when the robot is horizontal
    public void reset()
    {
    	if (RobotMap.enableNavX) {
    		RobotMap.navx.reset();
    		m_horizontalPitch = RobotMap.navx.getPitch();
    	}
    	else {
    		m_horizontalPitch = 0;
    	}

    	m_currentStage = Stage.STRAIGHT_DRIVE;    	
		m_setPoint = RobotMap.kMinPitch + m_horizontalPitch;
		m_tolerance = 0.5;

		// stop any loops
    	if (this.getPIDController().isEnabled())
    		disable();

       	getPIDController().setPID(RobotMap.kPsd, RobotMap.kIsd, RobotMap.kDsd, RobotMap.kFsd);
    	getPIDController().setSetpoint(m_setPoint);
    	setAbsoluteTolerance(m_tolerance);
    	setOutputRange(-0.5, 0.5);
    	
    	System.out.println("Teeter Totter: Reset completed");
    }
    
    public void start() {
    	System.out.println("Teeter Totter: Started");
       	enable();
    }
    
    public void stop() {
    	if (this.getPIDController().isEnabled())
    		disable();

    	System.out.println("Teeter Totter: Stopped");
    }

    @Override
    public void initDefaultCommand() {
    }

    @Override
    protected double returnPIDInput() {
    	if (RobotMap.enableNavX)
    		return RobotMap.navx.getPitch();
    	else
    		return m_setPoint;    		
    }

    @Override
    protected void usePIDOutput(double output) {
    	double pidOut;
    	double pidIn = returnPIDInput();
    	
    	if (m_currentStage == Stage.STRAIGHT_DRIVE)
    		pidOut = -output;
    	else
    		pidOut = output;
    	
		System.out.println("Teeter Totter: " + m_currentStage + " | " + (pidIn - m_setPoint) +  " | " + pidOut);
    	RobotMap.drive.drive(pidOut, 0);
    	
    	if (m_currentStage == Stage.STRAIGHT_DRIVE) {
        	if (Math.abs(pidIn - m_setPoint) < m_tolerance) {
    			// switch to balance drive
    			switchToBalanceDrive();
        	}
    	}
    }

    @Override
    public boolean onTarget()
    {
    	// the only way to come out of teeter totter is through an interrupt command
    	return false;
    }

    private void switchToBalanceDrive()
    {
    	m_currentStage = Stage.BALANCE_DRIVE;
    	m_setPoint = m_horizontalPitch;
    	m_tolerance = 0.1;
    	
		// reset the PID loop
    	getPIDController().reset();

    	getPIDController().setPID(RobotMap.kPbd, RobotMap.kIbd, RobotMap.kDbd, RobotMap.kFbd);
    	getPIDController().setSetpoint(m_setPoint);
    	setAbsoluteTolerance(m_tolerance);
    	setOutputRange(-0.03 * RobotMap.kMinPitch, 0.03 * RobotMap.kMinPitch);
    	
    	// restart the PID loop
    	System.out.println("Teeter Totter: Switched to balance drive");
    	enable();    	
    }    
}

