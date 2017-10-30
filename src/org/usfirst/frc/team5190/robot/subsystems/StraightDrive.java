package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.AutoDriveStraight;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StraightDrive extends PIDSubsystem {

	protected double m_pidOut;
	
    public StraightDrive() {
    	super("Straight Drive", RobotMap.kPsd, RobotMap.kIsd, RobotMap.kDsd, RobotMap.kFsd);
    	
    	LiveWindow.addActuator("Straight Drive", "PID Controller", getPIDController());
    }
    
    public void initialize()
    {
    	RobotMap.navx.reset();
    	getPIDController().setContinuous(false);
    	getPIDController().setSetpoint(5);
    	setPercentTolerance(10);
    	setInputRange(0, RobotMap.kMaxPitch);
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
		setDefaultCommand(new AutoDriveStraight());
    }

    @Override
    protected double returnPIDInput() {
        double pitch = RobotMap.navx.getPitch();
        
        if (pitch < 0)
        	return 0;
        
        return pitch;
    }

    @Override
    protected void usePIDOutput(double output) {
    	m_pidOut = output;
    	RobotMap.drive.arcadeDrive(output, 0);
    }

    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("StraightDrive.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("StraightDrive.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("StraightDrive.PIDOutput", m_pidOut);
    }
}
