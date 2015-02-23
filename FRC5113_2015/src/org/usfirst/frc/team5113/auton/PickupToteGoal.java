package org.usfirst.frc.team5113.auton;

public class PickupToteGoal extends ActionGoal
{
	public double currHeight;
	public boolean flagCompleated = false;
	public boolean flagCompleated2 = false;
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
				flagCompleated2 = false;
			else
				flagCompleated2 = true;	
		}
		else
		{//lower
			if(currHeight > minHeight)
				flagCompleated = false;
			else
				flagCompleated = true;
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
		update();
		
		return flagCompleated;
	}
	
	public boolean compleated2()
	{
		update();
		
		return flagCompleated2;
	}
	
}
