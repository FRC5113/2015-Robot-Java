package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.comms.IRISComms;
import org.usfirst.frc.team5113.drive.MotorManager;

public class OrbitToteGoal extends ActionGoal
{
	float shortLength, distance, currLength;
	final float TOL = 15;
	IRISComms comm = new IRISComms();
	float[] totData;
	
	public void update(MotorManager dr)
	{
		totData = comm.getToteDataFromString();//x,y,w,h,d,a
		distance = totData[4];
		currLength = totData[2];
		
		for(int i = 0; i < totData.length; i++)
			System.out.println(i + ": " + totData[i]);
		
		if(distance > 2)
			shortLength = (float)(300 * Math.pow(distance, -1.09));
		else
			shortLength = (float)(310 * Math.pow(distance, -1.2));
		
		if((currLength - shortLength) > TOL)
			dr.mecanumDrive(0.8f, 90, 0.2f);
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
