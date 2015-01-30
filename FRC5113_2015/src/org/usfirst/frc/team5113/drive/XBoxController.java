package org.usfirst.frc.team5113.drive;

import org.usfirst.frc.team5113.elevator.Elevator;

import edu.wpi.first.wpilibj.Joystick;

public class XBoxController extends DriveController
{
	
	Joystick xbox;
	
	
	public void init()
	{
		xbox = new Joystick(0);
	}
	
	public void update(DriveTrain dr, Elevator el)
	{
		dr.customMecanumDrive(xbox.getMagnitude(), xbox.getMagnitude(), (float) (xbox.getRawAxis(4) / 2f));
	} 
}
