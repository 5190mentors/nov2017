package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Elevator extends Subsystem {
	
	public Elevator() {
		super("Elevator");
		
		LiveWindow.addActuator("Elevator", "Motor", (Jaguar) RobotMap.elevatorMotor);

		if (RobotMap.reverseElevator) {
			((Jaguar) RobotMap.elevatorMotor).setInverted(true);
		}
		
		reset();
	}
	
    public void reset() {
    	RobotMap.elevatorMotor.stopMotor();
    	System.out.println("Elevator: Reset completed");
    }
    
    public void initDefaultCommand() {	
    }    

    public void up() {
    	RobotMap.elevatorMotor.set(RobotMap.kElevSpeed);
    }
    
    public void down() {
    	RobotMap.elevatorMotor.set(-RobotMap.kElevSpeed);
    }
}
