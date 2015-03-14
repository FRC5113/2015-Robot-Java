package org.usfirst.frc.team5113.auton;

import org.usfirst.frc.team5113.controllers.AutonController;

public abstract class ActionGoal
{	
	public abstract void update();
	
	public AutonController controller;
	
	public boolean flagCompleted;
}
