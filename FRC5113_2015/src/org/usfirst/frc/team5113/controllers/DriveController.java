package org.usfirst.frc.team5113.controllers;

import org.usfirst.frc.team5113.drive.CANManager;
/**
 * @author Lemons
 * Base class for drive controllers- xbox and joysticks, mainly.
 */
public abstract class DriveController
{
	public abstract void init();

	public abstract void update(CANManager dr);
}
