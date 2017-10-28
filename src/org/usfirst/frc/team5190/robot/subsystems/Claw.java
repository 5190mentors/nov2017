/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.OpenClaw;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Claw extends Subsystem {

	public Claw() {
		super("Claw");
		
		LiveWindow.addActuator("Claw", "Solenoid", (Solenoid) RobotMap.grip);
	}

	public void initialize()
	{
	}

	public void end()
	{
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new OpenClaw());
	}

	public void open() {
		RobotMap.grip.set(false);
	}
	
	public void close() {
		RobotMap.grip.set(true);
	}

	public boolean isGrabbing() {
		return RobotMap.grip.get();
	}
}
