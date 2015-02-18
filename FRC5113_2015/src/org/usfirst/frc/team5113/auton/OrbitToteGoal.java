package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.drive.MotorManager;

public class OrbitToteGoal
{
	float shortLength, distance, currLength;
	final float TOL = 15;
	
	public void update(MotorManager dr)
	{
		if(distance > 2)
			shortLength = (float)(300 * Math.pow(distance, -1.09));
		else
			shortLength = (float)(310 * Math.pow(distance, -1.2));
		
		while((currLength - shortLength) > TOL)
			dr.mecanumDrive(0.8f, 90, 0.2f);
	}
}
