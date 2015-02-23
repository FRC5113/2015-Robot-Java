package org.usfirst.frc.team5113.auton;

public class Rotation extends ActionGoal
{
	public AutonController contr;
	public boolean flagCompleated = false;
	public boolean flagCompleated2 = false;
	public boolean flagCompleated3 = false;
	public float timeNow = 0;
	public float timeStart = 0;
	public float timeNow2 = 0;
	public float timeStart2 = 0;
	public float timeNow3 = 0;
	public float timeStart3 = 0;
	
	@Override
	public void update()
	{
		if(timeStart == 0)
			timeStart = System.currentTimeMillis();
		else
			timeNow = System.currentTimeMillis();
		
		if((timeNow - timeStart) < 500)//TODO - check time or implement encoders
			contr.rotCCW(0.2f);
		else
		{
			contr.stop();
			flagCompleated = true;
		}
	}
	
	public void updateCW()
	{
		if(timeStart2 == 0)
			timeStart2 = System.currentTimeMillis();
		else
			timeNow2 = System.currentTimeMillis();
		
		if((timeNow2 - timeStart2) < 500)//TODO - check time or implement encoders
			contr.rotCCW(0.2f);
		else
		{
			contr.stop();
			flagCompleated2 = true;
		}
	}
	
	public void update180()
	{
		if(timeStart3 == 0)
			timeStart3 = System.currentTimeMillis();
		else
			timeNow3 = System.currentTimeMillis();
		
		if((timeNow3 - timeStart3) < 1000)//TODO - check time or implement encoders
			contr.rotCCW(0.2f);
		else
		{
			contr.stop();
			flagCompleated3 = true;
		}
	}

	@Override
	public boolean compleated()
	{
		return flagCompleated;
	}
	
	public boolean compleatedCW()
	{
		return flagCompleated2;
	}
	
	public boolean compleated180()
	{
		return flagCompleated3;
	}

}
