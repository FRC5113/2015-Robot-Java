package org.usfirst.frc.team5113.comms;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * @author Jacob Laurendeau
 * The "Host"- robot side code for creating and receiving data from network tables sent by IRIS' vision recognition systems.
 */
public class IRISComms implements Runnable
{

	private String tableName = "IRISTable";
	private NetworkTable table;
	
	private static IRISComms commsInst;
	
	private Thread thread;
	
	private int sessionHigh;
    Image frame;
	private int sessionLow;
	
	public String getYellowToteData()
	{
		return table.getString("YellowToteData");
	}
	
	public String getMiscData(String key)
	{
		//TODO rewrite because this code is really ugly
		try
		{
			return table.getString(key);
		}
		catch(Exception e)
		{
			try
			{
			e.printStackTrace();
			table.putString(key, "");
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return "";
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
	
	public void update()
	{
        NIVision.IMAQdxGrab(sessionLow, frame, 1);
        CameraServer.getInstance().setImage(frame);    
	}
	
	public void initLocal()
	{
		table = NetworkTable.getTable(tableName);
		table.putBoolean("HighCamera", false);
		
		
		
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        
        
        sessionLow = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        NIVision.IMAQdxConfigureGrab(sessionLow);

        
        NIVision.IMAQdxStartAcquisition(sessionLow);
        
        
	}
	
	public IRISComms()
	{ 
	}
	
	//TODO fix
	public void SetCamera(boolean high)
	{
		/*
		table.putBoolean("HighCamera", high);    
		
		NIVision.IMAQdxStopAcquisition(sessionLow);
		
        sessionLow = NIVision.IMAQdxOpenCamera(high ? "cam0" : "cam1",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        NIVision.IMAQdxConfigureGrab(sessionLow);

        
        NIVision.IMAQdxStartAcquisition(sessionLow);
        */

	}
	
	//True = high camera, false = low camera
	public boolean GetCamera()
	{
		return table.getBoolean("HighCamera");
	}
	
	public static void init()
	{
		commsInst = new IRISComms();
		commsInst.initLocal();
		commsInst.thread = new Thread(commsInst);
		commsInst.thread.start();
	}
	
	public static IRISComms GetInstance()
	{
		return commsInst;
	}


	@Override
	public void run()
	{
		long timer = System.currentTimeMillis();

		while(true)
		{
			//Limit to max 10 ticks per second
			if(System.currentTimeMillis() - timer >= 100)
			{
				update();
			}
			else
			{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
