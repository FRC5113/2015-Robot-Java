package org.usfirst.frc.team5113.auton;

public class PickupToteGoal extends ActionGoal
{
	public double currHeight;
	public boolean flagCompleated, flagCompleated2;
	public boolean lift = false;
	public double maxHeight = 4;
	public double minHeight = .5;
	
	public void height(double h)
	{
		currHeight = h;
	}

	@Override
	public void update()
	{
		if(lift)
		{//raise
			if(currHeight < maxHeight)
				flagCompleated = false;
			else
				flagCompleated = true;	
		}
		else
		{//lower
			if(currHeight > minHeight)
				flagCompleated2 = false;
			else
				flagCompleated2 = true;
		}
	}
	
	public void pickUp()
	{
		lift = true;
	}
	
	public void putDown()
	{
		lift = false;
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
	
	public boolean compleated2()
	{
		if(flagCompleated2)
		{
			flagCompleated2 = false;
			return true;
		}
		else
			return false;
	}
	
}
