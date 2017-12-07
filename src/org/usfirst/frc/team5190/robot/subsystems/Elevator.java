package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Elevator extends Subsystem {
	
	public Elevator() {
		super("Elevator");
		
		if (RobotMap.enableElevator)
			LiveWindow.addActuator("Elevator", "Motor", (CANTalon) RobotMap.elevatorMotor);

		if (RobotMap.reverseElevator) {
			((Jaguar) RobotMap.elevatorMotor).setInverted(true);
		}
		
		reset();
	}
	
    public void reset() {
    	if (RobotMap.enableElevator)
    		RobotMap.elevatorMotor.stopMotor();
    	System.out.println("Elevator: Reset completed");
    }
    
    public void initDefaultCommand() {	
    }    

    public void up() {
    	if (RobotMap.enableElevator)
    		RobotMap.elevatorMotor.set(RobotMap.kElevSpeed);
    }
    
    public void down() {
    	if (RobotMap.enableElevator)
    		RobotMap.elevatorMotor.set(-RobotMap.kElevSpeed);
    }

    public void stop() {
    	if (RobotMap.enableElevator)
    		RobotMap.elevatorMotor.set(0);
    }
}
