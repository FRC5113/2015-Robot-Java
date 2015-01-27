package org.usfirst.frc.team5113.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

public class CameraBase {
	
	int xOut = 5;
	int yOut = 4;
	
	float x = 0.5f;
	float y = 0.5f;
	float add = 0.05f;
	float deadzone = 0.08f;
	
	float time = 0;
	
	Joystick xbox = new Joystick(1);
	
	Servo servoX = new Servo(xOut);
	Servo servoY = new Servo(yOut);
		
	public void update() {
		
		//x += xbox.getRawAxis(4) / 700f;
		//y -= xbox.getRawAxis(5) / 700f;
		
		
		if(x > 1)
			x = 1;
		if(x < 0)
			x = 0;
		if(y > 1)
			y = 1;
		if(y < 0)
			y = 0;
		
		servoX.set(x);
		servoY.set(y);
		
	}
}
