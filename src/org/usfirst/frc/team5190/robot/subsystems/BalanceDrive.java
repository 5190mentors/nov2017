package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.AutoDriveBalance;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BalanceDrive extends PIDSubsystem {

	protected double m_pidOut;
	
    public BalanceDrive() {
    	super("Balance Drive", RobotMap.kPbd, RobotMap.kIbd, RobotMap.kDbd, RobotMap.kFbd);

    	LiveWindow.addActuator("Balance Drive", "PID Controller", getPIDController());
    }
    
    public void initialize()
    {
    	// do not reset navx angle since we might start off at 5 degrees
    	getPIDController().setContinuous(false);
    	getPIDController().setSetpoint(0);    	
    	setAbsoluteTolerance(0.05);
    	setInputRange(-45, 45);
    	setOutputRange(-1, 1);
    	
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
    		return 0;
    }

    @Override
    protected void usePIDOutput(double output) 
    {
    	m_pidOut = output;
    	RobotMap.drive.arcadeDrive(output, 0);
    }

    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("BalanceDrive.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("BalanceDrive.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("BalanceDrive.PIDOutput", m_pidOut);
    }
}
