package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.drive.MotorManager;

public class IntoZone extends ActionGoal
{
	public AutonController contr;
	public MotorManager motors = new MotorManager();
	public boolean flagCompleated = false;
	public float timeNow = 0;
	public float timeStart = 0;
	
	
	@Override
	public void update()
	{
		
		if(timeStart == 0)
			timeStart = System.currentTimeMillis();
		else
			timeNow = System.currentTimeMillis();
		
		if((timeStart- timeNow) < 1000)//TODO - Find Distance from Totes to Zone
			contr.forward(.2f);
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
