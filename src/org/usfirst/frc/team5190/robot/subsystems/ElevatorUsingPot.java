package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.subsystems.Elevator.Height;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorUsingPot extends PIDSubsystem {
	
	protected double m_pidOut;
	protected double m_setPoint;
	protected double m_tolerance = 0.1;
	
	public ElevatorUsingPot() {
		super("Elevator", RobotMap.kPel, RobotMap.kIel, RobotMap.kDel);

		LiveWindow.addActuator("Elevator", "Motor", (Victor) RobotMap.elevatorMotor);			
		LiveWindow.addSensor("Elevator", "Pot", (AnalogPotentiometer) RobotMap.elevatorPot);
		LiveWindow.addActuator("Elevator", "PID", getPIDController());
	}

    public void initialize(Elevator.Height setpoint)
    {
    	if (setpoint == Height.LOW)
    		m_setPoint = RobotMap.kLow;
    	else if (setpoint == Height.MIDDLE)
    		m_setPoint = RobotMap.kMiddle;
    	else
    		m_setPoint = RobotMap.kHigh;
    	
    	getPIDController().setSetpoint(m_setPoint);
    	setAbsoluteTolerance(m_tolerance);
    	setOutputRange(-0.5, 0.5);
    	
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
		return RobotMap.elevatorPot.get();
	}

	@Override
	protected void usePIDOutput(double d) {
		m_pidOut = d;
		RobotMap.elevatorMotor.set(d);
	}

	@Override
	public boolean onTarget()
	{
		return (Math.abs(returnPIDInput() - m_setPoint) < m_tolerance);
	}
	
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("Elevator.DistanceDriveSetpoint", this.getSetpoint());
    	SmartDashboard.putNumber("Elevator.PIDInput", this.returnPIDInput());
    	SmartDashboard.putNumber("Elevator.PIDOutput", m_pidOut);
    }
}
