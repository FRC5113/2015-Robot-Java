package org.usfirst.frc.team5113.drive;

import org.usfirst.frc.team5113.elevator.Elevator;

public abstract class DriveController 
{
	public abstract void init();
	
	public abstract void update(DriveTrain dr, Elevator el);
}
