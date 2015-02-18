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

	@Override
	public void init()
	{
		
	}

	@Override
	public void update(MotorManager dr)
	{
		dr.mecanumDrive(mag, dir, rot);
		dr.elevatorMovement(elev);
		mag = dir = rot = elev = 0;
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
	
}