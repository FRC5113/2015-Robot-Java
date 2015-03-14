package org.usfirst.frc.team5113.drive;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class AngleManager 
{
	//Accelerometer
	ADXL345_I2C accel;//Ari confirmed that we do infact use I2C
	ADXL345_I2C.AllAxes accelerations;
	
	public void init()
	{
		accel = new ADXL345_I2C(null, Range.k4G);
	}
	
	public double[] accelVals()
	{
		double[] vals = new double[2];
		vals[0] = accel.getAcceleration(ADXL345_I2C.Axes.kX);
		vals[1] = accel.getAcceleration(ADXL345_I2C.Axes.kY);
		vals[2] = accel.getAcceleration(ADXL345_I2C.Axes.kZ);
		
		return vals;//x, y, z
		
	}
}
