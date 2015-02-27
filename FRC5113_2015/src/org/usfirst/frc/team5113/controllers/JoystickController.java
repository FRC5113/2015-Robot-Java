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
	Joystick thirdStick;

	public void init()
	{
		rightStick = new Joystick(1);
		leftStick = new Joystick(2);
		thirdStick = new Joystick(0);
	}

	public void update(MotorManager dr)
	{
		double rightMag = rightStick.getMagnitude();
		double rightAngle = rightStick.getDirectionDegrees();
		double leftXAxis = -leftStick.getX();
		
				
		dr.mecanumDrive(rightMag / 3f, rightAngle, leftXAxis / 3f);
		
		//dr.elevatorMovement(speed);
		
		//dr.mecanumDrive(0.8f, 90, 0.2f);
		//dr.elevatorMovement(thirdStick.getY());
		
		float left = (float) thirdStick.getRawAxis(2);
		float right = (float) thirdStick.getRawAxis(3);
		
		/*
		if(Math.abs(left) <= 0.12)
		{
			left = 0;
		}
		if(Math.abs(right) <= 0.12)
		{
			right = 0;
		}
		*/
		
		
		dr.elevatorMovement(left - right);
		
		System.out.println(thirdStick.getRawAxis(2) + ", " + thirdStick.getRawAxis(3));
		
	}
}