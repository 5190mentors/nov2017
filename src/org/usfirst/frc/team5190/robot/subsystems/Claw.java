package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Claw extends Subsystem {

	public Claw() {
		super("Claw");

		if (RobotMap.enableClaw)
			LiveWindow.addActuator("Claw", "Solenoid", (Solenoid) RobotMap.grip);
		
		reset();
	}

	public void reset() {
		open();
    	System.out.println("Claw: Reset completed");
	}

	@Override
	public void initDefaultCommand() {
	}

	public void open() {
		if (RobotMap.enableClaw)
			RobotMap.grip.set(false);
    	System.out.println("Claw: Opened");
	}
	
	public void close() {
		if (RobotMap.enableClaw)
			RobotMap.grip.set(true);
    	System.out.println("Claw: Closed");
	}

	public boolean isGrabbing() {
		if (RobotMap.enableClaw)			
			return RobotMap.grip.get();
		else
			return false;
	}
}
