package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.comms.IRISComms;

public class NearToteGoal extends ActionGoal
{
	public boolean flagCompleated;
	IRISComms comm = new IRISComms();
	float[] totData;
	float distance;
	
	public void update()
	{		
		totData = comm.getToteDataFromString();//x,y,w,h,d,a
		distance = totData[4];
		
		if(distance > 0.2f)//.2m minDistance from tote to pickup, 0.1m optimal, but also max
			flagCompleated = false;
		else
			flagCompleated = true;
	}
	
	public boolean compleated()
	{
		if(flagCompleated)
		{
			flagCompleated = false;
			return true;
		}
		else
			return false;
	}


}
