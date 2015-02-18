package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.controllers.DriveController;
import org.usfirst.frc.team5113.drive.MotorManager;

public class AutonController extends DriveController
{
	OrbitToteGoal orbit;
	public static ActionGoal goal;

	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		orbit = new OrbitToteGoal();
	}

	@Override
	public void update(MotorManager dr)
	{
		// TODO Auto-generated method stub
		orbit.update(dr);
	}
	
	//public void 
	
	public void forward()
	{
		
	}
	
	public void left()
	{
		
	}
	
	public void right()
	{
	
	}
	
	public void back()
	{
		
	}
	
	public void rotCCW()
	{
		
	}
	
	public void rotCW()
	{
		
	}
}
