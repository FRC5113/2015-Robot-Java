package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.auton.JakeTestAutonGoals.State;
import org.usfirst.frc.team5113.drive.MotorManager;

public class IntoZone extends ActionGoal
{
	public AutonController contr;
	public MotorManager motors = new MotorManager();
	public boolean flagCompleated = false;
	public float timer = 0;
	private boolean pause = false;
	
	
	@Override
	public void update()
	{
		if(flagCompleated)
		{
			if(!pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			else
			{
				if(System.currentTimeMillis() - timer > 1700)
				{
					pause = false;
				}
				controller.forward(0.5f);
			}
		}
		else
		{
			controller.stop();
		}
	}

	@Override
	public boolean compleated()
	{
		return flagCompleated;
	}

}
