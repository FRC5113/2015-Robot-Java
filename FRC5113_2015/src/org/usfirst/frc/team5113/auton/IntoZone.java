package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.drive.MotorManager;

public class IntoZone extends ActionGoal
{
	public AutonController contr;
	public MotorManager motors = new MotorManager();
	public boolean flagCompleated = false;
	public float timeNow = 0;
	public float timeStart = 0;
	public double distance = 0;
	
	
	@Override
	public void update()
	{
		distance = motors.getDistance();
		
//		if(timeStart == 0)
//			timeStart = System.currentTimeMillis();
//		else
//			timeNow = System.currentTimeMillis();
		
		if(distance < 10)//TODO - Find Distance from Totes to Zone
			contr.forward(.4f);
		else
		{
			contr.stop();
			flagCompleated = true;
		}
	}

	@Override
	public boolean compleated()
	{
		return flagCompleated;
	}

}
