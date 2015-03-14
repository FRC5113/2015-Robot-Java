package org.usfirst.frc.team5113.drive;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class AngleManager
{
	// Accelerometer
	ADXL345_I2C accel;	//Ari confirmed that we do infact use I2C
	ADXL345_I2C.AllAxes accelerations;

	// Gyro
	Gyro gyro;

	private static AngleManager instance;

	public static AngleManager getInstance()
	{
		return instance;
	}

	public static void setup()
	{
		instance = new AngleManager();
		instance.init();
	}

	public void init()
	{
		accel = new ADXL345_I2C(Port.kMXP, Range.k4G);
		gyro = new Gyro(0);
	}

	public void update()
	{
		gyro.updateTable();
		accel.updateTable();
	}

	/***
	 * @return double[] {x, y, z}
	 */
	public double[] accelVals()
	{
		double[] vals = new double[2];
		vals[0] = accel.getAcceleration(ADXL345_I2C.Axes.kX);
		vals[1] = accel.getAcceleration(ADXL345_I2C.Axes.kY);
		vals[2] = accel.getAcceleration(ADXL345_I2C.Axes.kZ);

		return vals;
	}

	public double currAngle()
	{
		return gyro.getAngle();
	}
}