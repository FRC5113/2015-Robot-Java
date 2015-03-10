package org.usfirst.frc.team5113.controllers;

import org.usfirst.frc.team5113.drive.MotorManager;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Lemons
 * Controls robot motor systems using an xbox controller.
 */
public class XBoxController extends DriveController
{

	Joystick xbox;
	
	//Higher number = Higher sensitivity.
	private double rotationalSensitivity = 0.5f;

	public void init()
	{
		xbox = new Joystick(0);
	}

	public void update(MotorManager dr)
	{
		float mag = (float) (xbox.getMagnitude() * 0.6f);
		if(mag < 0.10f)
		{
			mag = 0;
		}
		
		
		dr.mecanumDrive(mag, xbox.getDirectionDegrees(),
				(float) (-xbox.getRawAxis(4) * rotationalSensitivity));
		
		float left = (float) xbox.getRawAxis(2);
		float right = (float) xbox.getRawAxis(3);		
		
		dr.elevatorMovement(left - right);
	}
}