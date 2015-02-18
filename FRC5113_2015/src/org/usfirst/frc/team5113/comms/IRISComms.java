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
public class IRISComms
{

	private String tableName = "IRISTable";
	private NetworkTable table;
	
	private static IRISComms commsInst;
	
	public String getYellowToteData()
	{
		return table.getString("YellowToteData");
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
        NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);	
	}
	
	public IRISComms()
	{
		table = NetworkTable.getTable(tableName);
		table.putBoolean("HighCamera", true);
		
		
		
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
        NIVision.IMAQdxStartAcquisition(session);		
	}
	
	public void SetCamera(boolean high)
	{
		table.putBoolean("HighCamera", high);
        NIVision.IMAQdxStopAcquisition(session);
        
        if(high)
        {
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        }
        else
        {
            session = NIVision.IMAQdxOpenCamera("cam1",
                    NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        }
        
        NIVision.IMAQdxConfigureGrab(session);
        
        NIVision.IMAQdxStartAcquisition(session);		
        
	}
	
	//True = high camera, false = low camera
	public boolean GetCamera()
	{
		return table.getBoolean("HighCamera");
	}
	
    int session;
    Image frame;

	public static void init()
	{
		commsInst = new IRISComms();
	}
	
	public static IRISComms GetInstance()
	{
		return commsInst;
	}
}
