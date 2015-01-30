package org.usfirst.frc.team5113.drive;

import org.usfirst.frc.team5113.elevator.Elevator;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickController extends DriveController
{
	Joystick rightStick;
	Joystick leftStick;
	
	
	public void init()
	{
		rightStick = new Joystick(1);
		leftStick = new Joystick(2);
	}
	
	public void update(DriveTrain dr, Elevator el)
	{
		double rightMag = rightStick.getMagnitude();
		double rightAngle = rightStick.getDirectionDegrees();
		float leftXAxis = (float)leftStick.getX();
		dr.customMecanumDrive(rightMag, rightAngle, leftXAxis);
	}
}
