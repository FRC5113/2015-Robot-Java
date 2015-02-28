package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.comms.IRISComms;

public class JakeTestAutonGoals extends ActionGoal
{
	
	public enum State 
	{
		forward1, tote1, bin1, forward2, tote2, bin2, forward3, tote3, bin3, autozone
	}
	
	public State state = State.forward1;

	@Override
	public void update()
	{
		
		float[] ytotedat = IRISComms.GetInstance().getToteDataFromString();
		
		float[] center = new float[] {
				ytotedat[0] + (ytotedat[2] / 2f),
				ytotedat[1] + (ytotedat[3] / 2f)};
		float[] centerImage = new float[] {320, 240};
		//SIZE_640x480
		
		System.out.println("TEST: " + center[0] + ", " + center[1] + ", ");
		
		switch(state)
		{
		case forward1:
			
			if(center[0] < centerImage[0] - 10)
			{
				controller.left(0.3f);
			}
			else if(center[0] > centerImage[0] + 10)
			{
				controller.right(0.3f);
			}
			
			break;
		}
	}

	@Override
	public boolean compleated()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
