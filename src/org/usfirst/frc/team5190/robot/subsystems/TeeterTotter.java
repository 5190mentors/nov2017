package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeeterTotter extends PIDSubsystem {

	protected double m_pidOut;
	protected double m_setPoint = 0;
	protected double m_horizontalPitch = 0;
	protected double m_tolerance = 0.1;
	public enum Stage {STRAIGHT_DRIVE, BALANCE_DRIVE};
	protected Stage m_currentStage = Stage.STRAIGHT_DRIVE;
	
    public TeeterTotter() {
    	super("Teeter Totter", RobotMap.kPsd, RobotMap.kIsd, RobotMap.kDsd, RobotMap.kFsd);
    	
    	LiveWindow.addActuator("Teeter Totter", "PID Controller", getPIDController());
    }

    public void initialize(Stage stage)
    {
    	if (stage == Stage.STRAIGHT_DRIVE) {
        	if (RobotMap.enableNavX) {
        		RobotMap.navx.reset();
        		m_horizontalPitch = RobotMap.navx.getPitch();
        	}

        	m_currentStage = Stage.STRAIGHT_DRIVE;
        	m_setPoint = RobotMap.kMaxPitch - 1;
        	m_tolerance = 0.5;

        	getPIDController().setPID(RobotMap.kPsd, RobotMap.kIsd, RobotMap.kDsd, RobotMap.kFsd);
        	getPIDController().setSetpoint(m_setPoint);
        	setAbsoluteTolerance(m_tolerance);
        	setOutputRange(-0.7, 0.7);
        	
        	// start the PID loop
        	enable();
    	}
    	else {
        	m_currentStage = Stage.BALANCE_DRIVE;
        	m_setPoint = m_horizontalPitch;
        	m_tolerance = 0.1;
        	
        	// reset the PID loop
        	getPIDController().reset();

        	getPIDController().setPID(RobotMap.kPbd, RobotMap.kIbd, RobotMap.kDbd, RobotMap.kFbd);
        	getPIDController().setSetpoint(m_setPoint);
        	setAbsoluteTolerance(m_tolerance);
        	setOutputRange(-RobotMap.kMaxSpeed, RobotMap.kMaxSpeed);
        	
        	// restart the PID loop
        	enable();
    	}    	  
    }
    
    public void end()
    {
    	// stop the PID loop
    	disable();
    }

    @Override
    public void initDefaultCommand() 
    {
    }

    @Override
    protected double returnPIDInput() {
    	if (!RobotMap.enableNavX)
    		return m_setPoint;
    	
        double pitch = RobotMap.navx.getPitch();
        
        // ignore dips during straight drive and continue driving. no need to back up
        if (m_currentStage == Stage.STRAIGHT_DRIVE && pitch < 0)
        	return 0;
        
        return pitch;
    }

    @Override
    protected void usePIDOutput(double output) {
    	
    	if (m_currentStage == Stage.STRAIGHT_DRIVE)
    		m_pidOut = output;
    	else
    		m_pidOut = -output;
    	
		System.out.println(returnPIDInput() +  " --- " + m_pidOut);
    	RobotMap.drive.drive(m_pidOut, 0);

    	if (m_currentStage == Stage.STRAIGHT_DRIVE) {
        	if (Math.abs(m_setPoint - RobotMap.navx.getPitch()) < m_tolerance);
    			// switch to balance drive
    			initialize(Stage.BALANCE_DRIVE);
    	}	
    }

    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("StraightDrive.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("StraightDrive.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("StraightDrive.PIDOutput", m_pidOut);
    }
    
    @Override
    public boolean onTarget()
    {
    	// the only way to come out of teeter totter is through an interrupt command
    	return false;
    }
}
