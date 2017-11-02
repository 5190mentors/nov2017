package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BalanceDrive extends PIDSubsystem {

	protected double m_pidOut;
	protected double m_setPoint = 0;
	
    public BalanceDrive() {
    	super("Balance Drive", RobotMap.kPbd, RobotMap.kIbd, RobotMap.kDbd, RobotMap.kFbd);

    	LiveWindow.addActuator("Balance Drive", "PID Controller", getPIDController());
    }
    
    public void initialize()
    {
    	// do not reset navx angle since we might start off at an angle
    	getPIDController().setSetpoint(m_setPoint);    	
    	setAbsoluteTolerance(0.1);
    	setOutputRange(-RobotMap.kMaxSpeed, RobotMap.kMaxSpeed);
    	
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
    protected double returnPIDInput() 
    {
    	if (RobotMap.enableNavX)
    		return  RobotMap.navx.getPitch();
    	else
    		return m_setPoint;
    }

    @Override
    protected void usePIDOutput(double output) 
    {
    	m_pidOut = -output;
		System.out.println(returnPIDInput() +  " --- " + output);
    	RobotMap.drive.drive(-output, 0);
    }

    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("BalanceDrive.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("BalanceDrive.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("BalanceDrive.PIDOutput", m_pidOut);
    }

    public boolean onTarget()
    {
    	// We will need to stay on target until we are interrupted by another command
    	// return Math.abs(m_setPoint - RobotMap.navx.getPitch()) < 0.1;
    	return false;
    }
}
