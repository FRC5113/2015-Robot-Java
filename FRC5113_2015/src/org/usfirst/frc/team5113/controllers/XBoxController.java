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
		dr.mecanumDrive(xbox.getMagnitude(), xbox.getMagnitude(),
				(float) (xbox.getRawAxis(4) * rotationalSensitivity));
		
		dr.elevatorMovement(xbox.getRawAxis(5) / 2f);
	}
}