package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class ElevatorUsingPot extends PIDSubsystem {
	public enum Height {LOW, MIDDLE, HIGH};
	
	protected double m_pidOut;
	protected double m_setPoint;
	protected double m_tolerance = 0.1;
	
	public ElevatorUsingPot() {
		super("Elevator", RobotMap.kPel, RobotMap.kIel, RobotMap.kDel);

		LiveWindow.addActuator("Elevator", "Motor", (Victor) RobotMap.elevatorMotor);			
		LiveWindow.addSensor("Elevator", "Pot", (AnalogPotentiometer) RobotMap.elevatorPot);
		LiveWindow.addActuator("Elevator", "PID", getPIDController());
		
		if (RobotMap.reverseElevator) {
			((Jaguar) RobotMap.elevatorMotor).setInverted(true);
		}
		
		reset();
	}
	
    public void reset() {
    	RobotMap.elevatorMotor.stopMotor();
    	
    	setAbsoluteTolerance(m_tolerance);
    	setOutputRange(-RobotMap.kElevSpeed, RobotMap.kElevSpeed);
    	
    	if (getPIDController().isEnabled())
    		disable();
    	
    	System.out.println("ElevatorPot: Reset completed");
    }
    
    public void goTo(Height setpoint)
    {
    	if (setpoint == Height.LOW)
    		m_setPoint = RobotMap.kLow;
    	else if (setpoint == Height.MIDDLE)
    		m_setPoint = RobotMap.kMiddle;
    	else
    		m_setPoint = RobotMap.kHigh;
    	
    	if (getPIDController().isEnabled())
    		disable();

    	getPIDController().setSetpoint(m_setPoint);
    	
    	// start the PID loop
    	enable();
    }

    public void stop()
    {
    	if (getPIDController().isEnabled())
    		disable();
    }

    @Override
	public void initDefaultCommand() {
	}

	@Override
	protected double returnPIDInput() {
		return RobotMap.elevatorPot.get();
	}

	@Override
	protected void usePIDOutput(double d) {
		double pidIn = returnPIDInput();
		m_pidOut = d;
		
		System.out.println("ElevatorPot: " + (pidIn - m_setPoint) + " | " + d);
		RobotMap.elevatorMotor.set(d);
	}

	@Override
	public boolean onTarget()
	{
		return (Math.abs(returnPIDInput() - m_setPoint) < m_tolerance);
	}
}
