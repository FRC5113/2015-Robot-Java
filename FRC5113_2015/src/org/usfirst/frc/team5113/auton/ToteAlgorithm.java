package org.usfirst.frc.team5113.auton;

public class ToteAlgorithm extends ActionGoal
{
	public boolean flagCompleated;
	public AutonController contr;
	public OrbitToteGoal orbit;
	public NearToteGoal nearGoal;
	public PickupToteGoal lift;
	
	@Override
	public void update()
	{
		if(orbit.compleated())
			contr.orbit();
		else if(nearGoal.compleated())
				contr.forward(.2f);
		else if(lift.compleated())
				contr.elevDown(0.2f);
		else if(lift.compleated2())
		     {
				lift.pickUp();
				contr.elevUp(0.2f);
		     }
		else
			flagCompleated = true;
	}

	@Override
	public boolean compleated()
	{
		return flagCompleated;
	}

}
