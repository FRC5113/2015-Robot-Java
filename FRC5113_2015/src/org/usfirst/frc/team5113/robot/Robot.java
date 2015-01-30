
package org.usfirst.frc.team5113.robot;

import org.usfirst.frc.team5113.comms.IRISComms;
import org.usfirst.frc.team5113.drive.DriveTrain;

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
	public static DriveTrain mecanumWheels;//this gives us access to the Drive class


	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
    	comms = new IRISComms();
    	mecanumWheels = new DriveTrain();
    	mecanumWheels.init();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
    	while(isOperatorControl() && isEnabled())
    	{
            mecanumWheels.driveUpdate(); //makes the robot move.... maybe
    		mecanumWheels.encoderUpdate();
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
