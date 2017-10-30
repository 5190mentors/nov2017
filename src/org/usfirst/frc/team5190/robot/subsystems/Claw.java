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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem {

	public boolean m_lastStatus;
	
	public Claw() {
		super("Claw");

		if (RobotMap.enableClaw)
		{
			LiveWindow.addActuator("Claw", "Solenoid", (Solenoid) RobotMap.grip);
		}
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
		if (RobotMap.enableClaw)
			RobotMap.grip.set(false);
		m_lastStatus = false;
	}
	
	public void close() {
		if (RobotMap.enableClaw)
			RobotMap.grip.set(true);
		m_lastStatus = true;
	}

	public boolean isGrabbing() {
		if (RobotMap.enableClaw)
			return RobotMap.grip.get();
		else
			return m_lastStatus;
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putBoolean("Claw.Status", this.isGrabbing());
	}
}
