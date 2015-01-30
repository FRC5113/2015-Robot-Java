package org.usfirst.frc.team5113.drive;


import org.usfirst.frc.team5113.robot.TempDriverJoysticks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.*;

//First attempt at mecanum drive, wish us luck! 
//Very similar to what we did last year, for now
public class DriveTrain 
{

	int frontLeft = 0;  //motor numbers
    int frontRight = 3;
    int backLeft = 1;
    int backRight = 6;
    
    Victor fl;//Names of the Motor controllers
    Victor fr;
    Victor bl;
    Victor br;
    	
    int encoderFL1 = 0;//Digital IO for encoders
    int encoderFL2 = 1;
    int encoderBL1 = 2;
    int encoderBL2 = 3;
    int encoderBR1 = 4;
    int encoderBR2 = 5;
    int encoderFR1 = 6;
    int encoderFR2 = 7;
    
    Encoder frontRightEncoder = new Encoder(encoderFR1, encoderFR2);//Assigns the digital encoders to their real life counterparts
    Encoder backRightEncoder = new Encoder(encoderBR1, encoderBR2);
    Encoder backLeftEncoder = new Encoder(encoderBL1, encoderBL2);
    Encoder frontLeftEncoder = new Encoder(encoderFL1, encoderFL2);
    
	/*TempDriverJoysticks driverJoystick;
	Joystick stick2;
	Joystick stick1;*/
	
	public void init()
	{
		 /*driverJoystick = new TempDriverJoysticks(1,2);//calling TempDriverJoysticks, will get a better name for the final class
		 stick1 = driverJoystick.getLeftJoystick();
		 stick2 = driverJoystick.getRightJoystick();*/
		 
		 fl = new Victor(frontLeft);//Assigns the digital motor controllers to their real-life counterparts
		 fr = new Victor(frontRight);
		 bl = new Victor(backLeft);
		 br = new Victor(backRight);
	
	}
	
	/*public void driveUpdate()
	{
		//joystickDrive(stick1,stick2);//tells the robot which joysticks to look at for driving instructions
		//joystickDrive2(stick1);
		//joystickDrive4(stick1,stick2);
		//xboxDrive(stick1);
		//customMecanumDrive(stick1.getMagnitude(), stick1.getDirectionDegrees(),(float) (stick2.getTwist() / 2f));
		customMecanumDrive(stick1.getMagnitude(), stick1.getDirectionDegrees(),(float) (stick1.getRawAxis(4) / 2f));
	}*/
	
	//We made our own drive code! we feel proud
	public void customMecanumDrive(double d, double e, float rotation)
	{
		d = Math.abs(d);
		
		if(d >= 1)
			d = (float)0.99;
		
		float newDirection = (float) (e + 45);//MATH!
		newDirection = (float)(newDirection * Math.PI) / 180f;
		float cosine, sine;
		cosine = (float)Math.cos(newDirection);
		sine = (float)Math.sin(newDirection);
		
		float frontLeftPower = (float) -(sine*d + rotation);//MORE MATH!
		float frontRightPower = (float)(cosine*d - rotation);
		float backLeftPower = (float) -(cosine*d + rotation);
		float backRightPower = (float)(sine*d - rotation);
		
		bl.set(backLeftPower);
		br.set(backRightPower);
		fl.set(frontLeftPower);
		fr.set(frontRightPower);
	}
	
	
	//This is how we UPDATE all the ENCODER stuff
	public void encoderUpdate()
	{
		boolean fRStop = frontRightEncoder.getStopped();//This gets whether or not the wheels are in 
		boolean fLStop = frontLeftEncoder.getStopped();//motion exciting, no?
		boolean bRStop = backRightEncoder.getStopped();
		boolean bLStop = backLeftEncoder.getStopped();
		
		System.err.println("Is the Front Left Wheel Stopped: " + fLStop);
		System.err.println("Is the Front Right Wheel Stopped: " + fRStop);
		System.err.println("Is the Back Left Wheel Stopped: " + bLStop);
		System.err.println("Is the Back Right Wheel Stopped: " + bRStop);
	}
	
}
