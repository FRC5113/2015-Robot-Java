package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.comms.IRISComms;

public class JakeTestAutonGoals extends ActionGoal
{
	
	public enum State 
	{
		BROKENDONTUSE, ROTATEFROMWITHINAUTOZONE, DOWNTOGRABTOTE, TOTEUP, ROTATE, MOVEAWAYFROMTOTE, QUIT, OVERSTAIRS, DROPTOTEINTOAUTOZONE
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
		case BROKENDONTUSE:
			
			//System.out.println("FORWARD");
			
			if(center[0] < centerImage[0] - 5)
			{
				controller.left(0.3f);
			}
			else if(center[0] > centerImage[0] + 5)
			{
				controller.right(0.3f);
			}
			else
			{
				//if(ytotedat[4] > 0.4f)
				if(false)
					{
					controller.forward(0.3f);
				}
				else
				{
					state = State.DOWNTOGRABTOTE;
				}
			}
			
			break;			
			
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
				if(System.currentTimeMillis() - timer > 1150)
				{
					pause = false;
					state = State.OVERSTAIRS;
				}
			}
			controller.rotCW(0.3f);
			break;
			
		case OVERSTAIRS:
			
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
					state = State.DROPTOTEINTOAUTOZONE;
					controller.stop();
				}
				else
				controller.forward(.9f);
			}
			
			break;
			
		case ROTATEFROMWITHINAUTOZONE:
			if(!pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}
			else
			{
				if(System.currentTimeMillis() - timer > 1350)
				{
					pause = false;
					state = State.MOVEAWAYFROMTOTE;
				}
			}
			controller.rotCCW(0.2f);
			break;
			
		case DROPTOTEINTOAUTOZONE:
			
			//System.out.println("TOTE");
			
			if(controller.elevToPoint(65) && !pause)
			{
				timer = System.currentTimeMillis();
				pause = true;
			}			
			else if(pause && (System.currentTimeMillis() - timer > 250))
			{
				pause = false;
				state = State.ROTATEFROMWITHINAUTOZONE;				
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
					state = State.QUIT;
				}
				controller.back(0.3f);
			}
			
			break;

		
		case QUIT:
			controller.stop();
			break;
			
		}
		
	}
}
