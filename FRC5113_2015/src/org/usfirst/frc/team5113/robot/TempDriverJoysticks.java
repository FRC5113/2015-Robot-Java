package org.usfirst.frc.team5113.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.buttons.*;

//this is how we get the joysticks, pretty much the same as last year for now
public class TempDriverJoysticks 
{
	public Joystick rightStick;
	public Joystick leftStick;
	
	private Button rotateLeft;
	private int rotateLeftPos = 3;
	
	private Button rotateRight;
	private int rotateRightPos = 4;
	
	public TempDriverJoysticks (int right, int left)
	{
		rightStick = new Joystick(right);
		leftStick = new Joystick(left);
		
		rotateLeft = new JoystickButton(rightStick, rotateLeftPos);
		rotateRight = new JoystickButton(rightStick, rotateRightPos);
	}
	
	public Joystick getRightJoystick() 
	{
        return rightStick;
    }

    public Joystick getLeftJoystick() 
    {
        return leftStick;
    }
    
}
