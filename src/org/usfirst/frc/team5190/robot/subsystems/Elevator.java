package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Elevator extends PIDSubsystem {
	public Elevator() {
		super("Elevator", RobotMap.kPel, RobotMap.kIel, RobotMap.kDel);

		LiveWindow.addActuator("Elevator", "Motor", (Victor) RobotMap.elevatorMotor);
		LiveWindow.addSensor("Elevator", "Pot", (AnalogPotentiometer) RobotMap.elevatorSensor);
		LiveWindow.addActuator("Elevator", "PID", getPIDController());
	}

    public void initialize(double setpoint)
    {
    	getPIDController().setSetpoint(setpoint);
    	setAbsoluteTolerance(0.05);
    	setOutputRange(0, 1);
    	
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
		return RobotMap.elevatorSensor.get();
	}

	@Override
	protected void usePIDOutput(double d) {
		RobotMap.elevatorMotor.set(d);
	}
}
