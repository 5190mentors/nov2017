package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StraightDrive extends PIDSubsystem {

	protected double m_pidOut;
	protected double m_setPoint = RobotMap.kMaxPitch - 1;
	
    public StraightDrive() {
    	super("Straight Drive", RobotMap.kPsd, RobotMap.kIsd, RobotMap.kDsd, RobotMap.kFsd);
    	
    	LiveWindow.addActuator("Straight Drive", "PID Controller", getPIDController());
    }
    
    public void initialize()
    {
    	if (RobotMap.enableNavX) {
    		RobotMap.navx.reset();
    	}

    	getPIDController().setSetpoint(m_setPoint);
    	setAbsoluteTolerance(0.5);
    	setOutputRange(-0.7, 0.7);
    	
    	// start the PID loop
    	enable();
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
        
        // trip dips on the way forward as no dips. no need to back up
        if (pitch < 0)
        	return 0;
        
        return pitch;
    }

    @Override
    protected void usePIDOutput(double output) {
    	m_pidOut = output;
		System.out.println(returnPIDInput() +  " --- " + output);
    	RobotMap.drive.drive(output, 0);
    }

    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("StraightDrive.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("StraightDrive.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("StraightDrive.PIDOutput", m_pidOut);
    }
    
    @Override
    public boolean onTarget()
    {
    	return (Math.abs(m_setPoint - RobotMap.navx.getPitch()) < 0.5);
    }
}
