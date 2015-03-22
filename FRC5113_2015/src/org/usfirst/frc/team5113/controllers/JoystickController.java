package org.usfirst.frc.team5113.controllers;

import org.usfirst.frc.team5113.drive.CANManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * @author Lemons Handles driver input and passes to CANMANAGER for actual robot
 *         movements
 */
public class JoystickController extends DriveController
{
	Joystick rightStick;
	Joystick leftStick;
	Joystick xboxController;
	

	double rotationalSensitivity = 0.5f;

	public void init()
	{
		rightStick = new Joystick(1);
		leftStick = new Joystick(2);
		xboxController = new Joystick(0);
		
	}

	boolean toggle = false;
	boolean lastHeld = false;

	private void handlePneumatics(CANManager dr)
	{
		// Pneumatics control
		if (xboxController.getRawButton(5))
		{
			dr.setPneumatics(false);
		} 
		else if (xboxController.getRawButton(6))
		{
			dr.setPneumatics(true);
		}// Not sure if this works or not, hard to test too.
		else if (xboxController.getRawButton(3) && xboxController.getRawButton(7))
		{
			dr.pneumaticsClearStickyFaults();
		}
		
		
		if(xboxController.getRawButton(1))
			dr.pneumaticWheelsIn();
		else if(xboxController.getRawButton(2))
				dr.pneumaticWheelsOut();
		else
			dr.pneumaticWheelsOff();
			
		dr.runPneumatics();

		// Signal pneumatics errors with rumble
		if (dr.pneumaticsHasErrors())
		{
			xboxController.setRumble(RumbleType.kLeftRumble, 0.5f);
			xboxController.setRumble(RumbleType.kRightRumble, 0.5f);
		}
	}

	private void handleElevator(CANManager dr)
	{
		// Elevator movement
		float left = (float) xboxController.getRawAxis(2);
		float right = (float) xboxController.getRawAxis(3);
		dr.elevatorMovement(left - right);
	}

	//Buttons
	// 1 = A button
	// 2 = B button
	// 3 = X button
	// 4 = Y button
	// 5 = left shoulder button
	// 6 = right shoulder button
	
	//Axes
	// 0 = Left Stick X-axis
	// 1 = Left Stick Y-axis
	// 2 = Left Trigger
	// 3 = Right Trigger
	// 4 = Right Stick X-axis
	// 5 = Right Stick Y-axis
	private void handleXboxDrive(CANManager dr)
	{
		//The magnitude uses sin so that it will drive at about 50% speed forward,
		//And 100% speed sideways. Because mecanum requires more power sideways.
		float mag = (float) Math.abs((Math.sin(xboxController
				.getDirectionRadians()) + xboxController.getMagnitude()) / 2f);
		if (mag < 0.1f)
		{
			mag = 0;
		}

		dr.mecanumDrive(mag, xboxController.getDirectionDegrees(),
				(float) (-xboxController.getRawAxis(4) * rotationalSensitivity));
	}

	private void handleJoystickDrive(CANManager dr)
	{

		double rightAngle = 90 - rightStick.getDirectionDegrees();
		
		double leftXAxis = -leftStick.getX();

		// Joystick drive control
		//The magnitude uses sin so that it will drive at about 50% speed forward,
		//And 100% speed sideways. Because mecanum requires more power sideways.
		float mag = (float) rightStick.getMagnitude() / 2f;
				
				/*(float) Math
				.abs((Math.sin(rightStick.getDirectionRadians()) + rightStick
						.getMagnitude()) / 2f);*/
		if (mag < 0.1f)
		{
			mag = 0;
		}
		
//		if((rightAngle > 170) && (rightAngle < 190))
//			mag = (mag * 2) - 0.05f;
//		else if(((rightAngle > 350) && (rightAngle <= 360)) || ((rightAngle >= 0) && (rightAngle < 10)))
//				mag = (mag * 2) - 0.05f;

		
		dr.mecanumDrive2(mag, rightAngle, leftXAxis / 3f);
	}

	public void update(CANManager dr)
	{

		handlePneumatics(dr);
		handleElevator(dr);

		// Switching
		// button is "Back" button
		if (xboxController.getRawButton(7) && !lastHeld)
		{
			toggle = !toggle;
			lastHeld = true;
		}
		if (!xboxController.getRawButton(7))
		{
			lastHeld = false;
		} 
		else
		{
			// Signal switch with rumble
			xboxController.setRumble(toggle ? RumbleType.kLeftRumble
					: RumbleType.kRightRumble, 1);
		}

		// Xbox drive control
		if (toggle)
		{
			handleXboxDrive(dr);
		} else
		{
			handleJoystickDrive(dr);
		}
	}
}