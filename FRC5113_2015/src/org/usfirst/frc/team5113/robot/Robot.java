package org.usfirst.frc.team5113.robot;

import org.usfirst.frc.team5113.comms.IRISComms;
import org.usfirst.frc.team5113.controllers.DriveController;
import org.usfirst.frc.team5113.controllers.JoystickController;
import org.usfirst.frc.team5113.drive.MotorManager;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

	IRISComms comms;
	private MotorManager motorManagers;// this gives us access to the Drive class
	private DriveController controller;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		controller = new JoystickController();
		controller.init();
		comms = new IRISComms();
		motorManagers = new MotorManager();
		motorManagers.init();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		while (isOperatorControl() && isEnabled())
		{
			controller.update(motorManagers);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{

	}

}
