package org.usfirst.frc.team5113.auton;

public class IntoZone extends ActionGoal
{
	public AutonController contr;
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
		
		if((timeNow - timeStart) < 2000)//TODO - check time or implement encoders
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
