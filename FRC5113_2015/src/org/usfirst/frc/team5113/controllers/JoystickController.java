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

	public void init()
	{
		rightStick = new Joystick(1);
		leftStick = new Joystick(2);
	}

	public void update(MotorManager dr)
	{
		double rightMag = rightStick.getMagnitude();
		double rightAngle = rightStick.getDirectionDegrees();
		double leftXAxis = -leftStick.getX();
				
		dr.mecanumDrive(rightMag, rightAngle, leftXAxis);
		
		//dr.mecanumDrive(0.8f, 90, 0.2f);
		
		dr.elevatorMovement(leftStick.getY());
	}
}