package org.usfirst.frc.team5113.controllers;

import org.usfirst.frc.team5113.drive.MotorManager;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Lemons
 * Controls robot motor systems using joysticks.
 */
public class JoystickController extends DriveController
{
	Joystick rightStick;
	Joystick leftStick;
	Joystick xboxController;

	public void init()
	{
		rightStick = new Joystick(1);
		leftStick = new Joystick(2);
		xboxController = new Joystick(0);
	}
	
	boolean toggle = false;
	boolean lastHeld = false;

	public void update(MotorManager dr)
	{
		double rightAngle = rightStick.getDirectionDegrees();
		double leftXAxis = -leftStick.getX();
		
		double rotationalSensitivity = 0.5f;
		
		
		//button is is top-right back button (NOT the trigger)
		if(xboxController.getRawButton(6) && !lastHeld)
		{
			toggle = !toggle;
			lastHeld = true;
		}
		
		if(!xboxController.getRawButton(6))
		{
			lastHeld = false;
		}
		
		if(toggle)
		{
			float mag = (float) Math.abs((Math.sin(xboxController.getDirectionRadians()) + xboxController.getMagnitude()) / 2f);
			if(mag < 0.1f)
			{
				mag = 0;
			}
			
			
			dr.mecanumDrive(
					mag, xboxController.getDirectionDegrees(),
					(float) (-xboxController.getRawAxis(4) * rotationalSensitivity));
		}
		else
		{

			float mag = (float) Math.abs((Math.sin(rightStick.getDirectionRadians()) + rightStick.getMagnitude()) / 2f);
			if(mag < 0.1f)
			{
				mag = 0;
			}
			
			dr.mecanumDrive(mag, rightAngle, leftXAxis / 3f);
		}
		
		float left = (float) xboxController.getRawAxis(2);
		float right = (float) xboxController.getRawAxis(3);
		
		
		dr.elevatorMovement(left - right);
		
		
	}
}