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
	private float elevToPoint;
	private int mode = 1;
	
	
	public ToteAlgorithm algorithm;
	public ToteAlgorithm algorithm2;
	public ToteAlgorithm algorithm3;
	public IntoZone zone;
	public Rotation spin;
	public Rotation spin2;
	public OrbitBin binSpin;
	public OrbitBin binSpin2;
	
	public JakeTestAutonGoals goalllllllllll = new JakeTestAutonGoals();
	

	@Override
	public void init()
	{
		mag = dir = rot = elev = elevToPoint = 0;
		algorithm = new ToteAlgorithm();
		algorithm2 = new ToteAlgorithm();
		algorithm3 = new ToteAlgorithm();
		zone = new IntoZone();
		spin = new Rotation();
		binSpin = new OrbitBin();
	}

	@Override
	public void update(MotorManager dr)
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
  		
  		goalllllllllll.update();
  		
//  		
//		if(mode == 1)
//			autonOne();
//		else if(mode == 2)
//				autonTwo();
//		else if(mode == 3)
//				autonThree();
//		else
//			stop();
	}
	
	public void autonOne()
	{
		if(zone.compleated())
			zone.update();
		else
			stop();
	}
	
	public void autonTwo()
	{
		if(algorithm.compleated())
			algorithm.update();
		else if(spin.compleatedCW())
			 	spin.updateCW();
		else if(zone.compleated())
				zone.update();
		else
			stop();
	}
	
	public void autonThree()
	{
		if(algorithm.compleated())
			algorithm.update();
		else if(binSpin.compleated())
				binSpin.update();
		else if(spin.compleated180())
			 	spin.update180();
		else if(algorithm2.compleated())
				algorithm2.update();
		else if(binSpin2.compleated())
				binSpin2.update();
		else if(spin2.compleated180())
				spin2.update180();
		else if(algorithm3.compleated())
				algorithm3.update();
		else if(spin.compleated())
				spin.update();
		else if(zone.compleated())
				zone.update();
		else
			stop();
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
	
	public void elevToPoint(float point)
	{
		elevToPoint = point;
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