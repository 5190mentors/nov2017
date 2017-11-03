package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
	
	public enum Height {LOW, MIDDLE, HIGH};
	public Height m_setPoint;

	public Elevator() {
		super("Elevator");
	}
	
    public void initialize(Height setpoint)
    {
    	m_setPoint = setpoint;
    }
    
    public void initDefaultCommand() {	
    }
    
    public void end() {
    }
    
	public boolean onTarget() {
		return true;
	}
}
