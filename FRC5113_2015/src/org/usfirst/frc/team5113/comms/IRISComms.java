package org.usfirst.frc.team5113.comms;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * @author Jacob Laurendeau
 * The "Host"- robot side code for creating and receiving data from network tables sent by IRIS' vision recognition systems.
 */
public class IRISComms
{

	private String tableName = "IRISTable";
	private NetworkTable table;
	
	private static IRISComms commsInst;
	
	public String getYellowToteData()
	{
		return table.getString("YToteData");
	}
	
	
	public float[] getToteDataFromString()
	{		
		String strIn = getYellowToteData();
		float[] box = new float[6];
		String[] strs = strIn.split(",");
		for(int i = 0; i < 6; i++)
		{
			box[i] = Float.parseFloat(strs[i]);
		}
		return box;
	}
	
	public IRISComms()
	{
		table = NetworkTable.getTable(tableName);
		table.putBoolean("HighCamera", true);
	}
	
	public void SetCamera(boolean high)
	{
		table.putBoolean("HighCamera", high);
	}
	
	//True = high camera, false = low camera
	public boolean GetCamera()
	{
		return table.getBoolean("HighCamera");
	}

	public static void init()
	{
		commsInst = new IRISComms();
			
		// CameraServer.getInstance().startAutomaticCapture(new
		// USBCamera("cam0"));
		// CameraServer.getInstance().startAutomaticCapture(new
		// USBCamera("cam1"));

		// camera.setQuality(50);
		// camera.startAutomaticCapture("cam0");
		// //CameraServer.getInstance().startAutomaticCapture(new
		// USBCamera("cam3"));

	}
	
	public static IRISComms GetInstance()
	{
		return commsInst;
	}
}
