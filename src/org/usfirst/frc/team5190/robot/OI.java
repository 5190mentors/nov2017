package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.commands.AutoTeeterTotter;
import org.usfirst.frc.team5190.robot.commands.Pickup;
import org.usfirst.frc.team5190.robot.commands.Place;
import org.usfirst.frc.team5190.robot.commands.PrepareToPickup;
import org.usfirst.frc.team5190.robot.commands.StopTeeterTotter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick joy = new Joystick(0);
	
	public OI() {
		// Create some buttons
		JoystickButton b0 = new JoystickButton(joy, 5);
//		JoystickButton b1 = new JoystickButton(joy, 6);
		JoystickButton b2 = new JoystickButton(joy, 7);
//		JoystickButton b3 = new JoystickButton(joy, 8);
		JoystickButton b4 = new JoystickButton(joy, 5);
		JoystickButton b5 = new JoystickButton(joy, 6);
		JoystickButton b6 = new JoystickButton(joy, 7);
//		JoystickButton b7 = new JoystickButton(joy, 8);
//		JoystickButton b8 = new JoystickButton(joy, 9);
//		JoystickButton b9 = new JoystickButton(joy, 10);
//		JoystickButton b10 = new JoystickButton(joy, 11);
//		JoystickButton b11 = new JoystickButton(joy, 12);

		// Connect the buttons to commands
		b0.whenPressed(new PrepareToPickup());
		b2.whenPressed(new Pickup());
		b4.whenPressed(new Place());
		
		b5.whenPressed(new AutoTeeterTotter());
		b6.whenPressed(new StopTeeterTotter());		
	}

	public Joystick getJoystick() {
		return joy;
	}
}
