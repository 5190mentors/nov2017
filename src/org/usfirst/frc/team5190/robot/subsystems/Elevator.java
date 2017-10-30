package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {
	
	protected double m_pidOut;
	protected double m_setPoint;
	
	public Elevator() {
		super("Elevator", RobotMap.kPel, RobotMap.kIel, RobotMap.kDel);

		if (RobotMap.enableElevator)
		{
			LiveWindow.addActuator("Elevator", "Motor", (Victor) RobotMap.elevatorMotor);
			LiveWindow.addSensor("Elevator", "Pot", (AnalogPotentiometer) RobotMap.elevatorSensor);
		}
		
		LiveWindow.addActuator("Elevator", "PID", getPIDController());
	}

    public void initialize(double setpoint)
    {
    	m_setPoint = setpoint;
    	
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
		if (RobotMap.enableElevator)
			return RobotMap.elevatorSensor.get();
		else
			return m_setPoint;
	}

	@Override
	protected void usePIDOutput(double d) {
		m_pidOut = d;
		if (RobotMap.enableElevator)
			RobotMap.elevatorMotor.set(d);
	}

    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("Elevator.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("Elevator.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("Elevator.PIDOutput", m_pidOut);
    }
}
