package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.controllers.DriveController;
import org.usfirst.frc.team5113.drive.CANManager;

public class AutonController extends DriveController
{
	
	public static ActionGoal goal;
	
	private float mag;
	private float dir;
	private float rot;
	private float elev;
	private float elevToPoint;
	private double lastElevatorHeight = 0;

	public JakeTestAutonGoals goalllllllllll = new JakeTestAutonGoals();
	

	@Override
	public void init()
	{
		mag = dir = rot = elev = elevToPoint = 0;
	}

	@Override
	public void update(CANManager dr)
	{
  		dr.mecanumDrive(mag, dir, rot);
  		if(elev == 0 && elevToPoint != 0)
  		{
  			dr.elevatorMovementLimited((int) elevToPoint);
  			elev = 0;
  		}
  		else
  		{
  			dr.elevatorMovement(elev);
  			elevToPoint = 0; 
  		}
  		
  		lastElevatorHeight = dr.elevatorHeight();
  		
  		//zone.update();
  		
  		goalllllllllll.controller = this;
  		goalllllllllll.update();
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
	
	public boolean elevToPoint(float point)
	{
		boolean bool = elevatorOnPoint();
		
		if(!bool)
			elevToPoint = point;
		else
			elevToPoint = 0;
		
		return bool;
	}
	
	private boolean elevatorOnPoint()
	{
		float elevatorGoalHeight = elevToPoint;
		if(elevatorGoalHeight > (lastElevatorHeight + 20))
		{
			return false;
		}
		else if(elevatorGoalHeight < (lastElevatorHeight - 20))
		{
			return false;
		}
		return true;
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
		mag = dir = rot = elev = elevToPoint = 0;
	}
	
	public void orbit()
	{
		mag = 0.8f;
		dir = 90;
		rot = 0.2f;
	}
	
}