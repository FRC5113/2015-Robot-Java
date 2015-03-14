package org.usfirst.frc.team5113.auton;

public abstract class ActionGoal
{	
	public abstract void update();
	
	public AutonController controller;
	
	public boolean flagCompleted;
}
