package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.AutoDriveBalance;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class BalanceDrive extends PIDSubsystem {

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
		setDefaultCommand(new AutoDriveBalance());
    }

    @Override
    protected double returnPIDInput() 
    {
    	return  RobotMap.navx.getPitch();
    }

    @Override
    protected void usePIDOutput(double output) 
    {
    	RobotMap.drive.arcadeDrive(output, 0);
    }
}
