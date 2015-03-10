package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.auton.JakeTestAutonGoals.State;

public class ReedAutonTest extends ActionGoal
{
	public enum State 
	{
		DOWNONSTAIRS, PUSHTIME, SHIFTOVER, DOWNTOGRABTOTE, TOTEUP, ROTATE, MOVEAWAYFROMTOTE, QUIT, PUTONSTAIRS, DROPTOTEINTOAUTOZONE
	}
	
	public State state = State.DOWNTOGRABTOTE;
	
	private boolean pause = false;
	
	private long timer;

	@Override
	public void update()
	{
		/*
		float[] ytotedat = IRISComms.GetInstance().getToteDataFromString();
		*/
		float[] center = new float[] {
				0,
				0};
		/*
		float[] center = new float[] {
				ytotedat[0] + (ytotedat[2] / 2f),
				ytotedat[1] + (ytotedat[3] / 2f)}
				*/
		float[] centerImage = new float[] {320, 240};
		//SIZE_640x480
		 
		 
		
		//System.out.println("TEST: " + center[0] + ", " + center[1] + ", ");
		
		switch(state)
		{
		case DOWNTOGRABTOTE:
			
			//System.out.println("TOTE");
			
			if(controller.elevToPoint(75) && !pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			
			if(pause && (System.currentTimeMillis() - timer > 500))
			{
				pause = false;
				state = State.TOTEUP;				
			}
			
			break;
			
		case TOTEUP:
			//System.out.println("TOTEUP");
			
			if(controller.elevToPoint(500) && !pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			
			if(pause && System.currentTimeMillis() - timer > 250)
			{
				pause = false;
				state = State.ROTATE;
			}
			
			 break;
			 
		case ROTATE:
			if(!pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			else
			{
				if(System.currentTimeMillis() - timer > 1000)
				{
					pause = false;
					state = State.PUTONSTAIRS;
				}
			}
			controller.rotCW(0.2f);
			break;
			
		case PUTONSTAIRS:
			
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
					state = State.DOWNONSTAIRS;
				}
				controller.forward(0.5f);
			}
			
			break;	
			
		case DOWNONSTAIRS:
			
			if(controller.elevToPoint(75) && !pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			
			if(pause && (System.currentTimeMillis() - timer > 500))
			{
				pause = false;
				state = State.MOVEAWAYFROMTOTE;				
			}
			
			break;
			
		case MOVEAWAYFROMTOTE:
			
			if(!pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			else
			{
				if(System.currentTimeMillis() - timer > 1000)
				{
					pause = false;
					state = State.SHIFTOVER;
				}
				controller.back(0.3f);
			}
			
			break;

		case SHIFTOVER:
			
			if(!pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			else
			{
				if(System.currentTimeMillis() - timer > 500)
				{
					pause = false;
					state = State.PUSHTIME;
				}
				controller.left(0.2f);
			}
			
			break;
		
		case PUSHTIME:
			
			if(!pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			else
			{
				if(System.currentTimeMillis() - timer > 1000)
				{
					pause = false;
					state = State.QUIT;
				}
				controller.forward(0.9f);
			}
			
			break;
			
		case QUIT:
			controller.stop();
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
