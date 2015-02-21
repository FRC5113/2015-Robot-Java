package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.controllers.DriveController;
import org.usfirst.frc.team5113.drive.MotorManager;

public class AutonController extends DriveController
{
	
	public static ActionGoal goal;
	
	private float mag;
	private float dir;
	private float rot;
	private float elev;
	private int mode = 1;
	private float timeStart, timeNow;
	public OrbitToteGoal orbit;
	public NearToteGoal nearGoal;
	public PickupToteGoal pickup;
	public boolean rotFlag = false;
	public boolean autonOneFlag = false;
	public boolean toteAlgorithmFlag = false;
	public boolean orbitFlag = false;
	public int count = 0;
	public boolean rotFlag2 = false;
	public boolean rotFlag3 = false;
	public boolean orbitFlag2 = false;
	

	@Override
	public void init()
	{
		mag = dir = rot = elev = 0;
		timeStart = 0;
		orbit = new OrbitToteGoal();
		nearGoal = new NearToteGoal();
		pickup = new PickupToteGoal();
	}

	@Override
	public void update(MotorManager dr)
	{
  		dr.mecanumDrive(mag, dir, rot);
  		dr.elevatorMovement(elev);
  		
		if(mode == 1)
			autonOne();
		else if(mode == 2)
				autonTwo();
		else
			autonThree();
	}
	
	public void autonOne()
	{
		if(count != 0)
		{
			timeNow = System.currentTimeMillis();
			count++;
		}
		else
			timeStart = System.currentTimeMillis();
		
		if((timeNow - timeStart) < 2000)//TODO - check time or implement encoders
			forward(.4f);
		else
		{
			stop();
			autonOneFlag = true;
			count = 0;
		}
	}
	
	public void autonTwo()
	{
		if(!orbit.compleated())
			orbit();
		else if(!nearGoal.compleated())
				forward(.2f);
		else if(!pickup.compleated())
				elevDown(0.2f);
		else if(!pickup.compleated2())
		     {
				pickup.pickUp();
				elevDown(0.2f);
		     }
		else if(rotFlag)
			 {
				if(count != 0)
				{
					timeNow = System.currentTimeMillis();
					count++;
				}
				else
					timeStart = System.currentTimeMillis();
			
				if((timeNow - timeStart) < 500)//TODO - check time or implement gyro
					rotCCW(.3f);
				else
				{
					rotFlag = true;
					stop();
					count = 0;
				}
			 }
		else if(autonOneFlag)
				autonOne();
		else
			stop();
	}
	
	public void autonThree()
	{
		if(!toteAlgorithmFlag)
			toteAlgorithm();
		else if(orbitFlag)
		     {
				if(count != 0)
				{
					timeNow = System.currentTimeMillis();
					count++;
				}
				else
					timeStart = System.currentTimeMillis();
			
				if((timeNow - timeStart) < 2000)//TODO - check time or implement encoders
					orbit();
				else
				{
					count = 0;
					orbitFlag = true;
				}
			 }
		else if(rotFlag)
			 {
				if(count != 0)
				{
					timeNow = System.currentTimeMillis();
					count++;
				}
				else
					timeStart = System.currentTimeMillis();
		
				if((timeNow - timeStart) < 500)//TODO - check time or implement gyro
					rotCCW(.3f);
				else
				{
					rotFlag = true;
					stop();
					count = 0;
				}
			 }
		else if(!toteAlgorithmFlag)
				toteAlgorithm();
		else if(orbitFlag2)
	    {
			if(count != 0)
			{
				timeNow = System.currentTimeMillis();
				count++;
			}
			else
				timeStart = System.currentTimeMillis();
		
			if((timeNow - timeStart) < 2000)//TODO - check time or implement encoders
				orbit();
			else
			{
				count = 0;
				orbitFlag2 = true;
			}
		}
		else if(rotFlag2)
		{
			if(count != 0)
			{
				timeNow = System.currentTimeMillis();
				count++;
			}
			else
				timeStart = System.currentTimeMillis();
	
			if((timeNow - timeStart) < 500)//TODO - check time or implement gyro
				rotCCW(.3f);
			else
			{
				rotFlag2 = true;
				stop();
				count = 0;
			}
		}
		else if(!toteAlgorithmFlag)
				toteAlgorithm();
		if(rotFlag3)
		 {
			if(count != 0)
			{
				timeNow = System.currentTimeMillis();
				count++;
			}
			else
				timeStart = System.currentTimeMillis();
		
			if((timeNow - timeStart) < 500)//TODO - check time or implement gyro
				rotCCW(.3f);
			else
			{
				rotFlag3 = true;
				stop();
				count = 0;
			}
		 }
		else if(autonOneFlag)
				autonOne();
		else
			stop();
	}
	
	public void toteAlgorithm()
	{
		if(!orbit.compleated())
			orbit();
		else if(!nearGoal.compleated())
				forward(.2f);
		else if(!pickup.compleated())
				elevDown(0.2f);
		else if(!pickup.compleated2())
		     {
				pickup.pickUp();
				elevDown(0.2f);
		     }
		else
			toteAlgorithmFlag = true;
	}
	
	public void mecan(float mag, float dir, float rot)
	{
		this.mag = mag;
		this.dir = dir;
		this.rot = rot;
	}

	public void forward(float speed)
	{
		mag = speed;
		dir = 0;
		rot = 0;
	}
	
	public void left(float speed)
	{
		mag = speed;
		dir = 270;
		rot = 0;
	}
	
	public void right(float speed)
	{
		mag = speed;
		dir = 90;
		rot = 0;
	}
	
	public void back(float speed)
	{
		mag = speed;
		dir = 180;
		rot = 0;
	}
	
	public void rotCCW(float speed)
	{
		mag = 0;
		dir = 0;
		rot = speed;
	}
	
	public void rotCW(float speed)
	{
		mag = 0;
		dir = 0;
		rot = -speed;
	}
	
	public void elevUp(float speed)
	{
		elev = speed;
	}
	
	public void elevDown(float speed)
	{
		elev = speed;
	}
	
	public void stop()
	{
		mag = dir = rot = 0;
	}
	
	public void orbit()
	{
		mag = 0.8f;
		dir = 90;
		rot = 0.2f;
	}
	
}