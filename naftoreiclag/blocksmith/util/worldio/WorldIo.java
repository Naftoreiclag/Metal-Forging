package naftoreiclag.blocksmith.util.worldio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.vector.Iitexture;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class WorldIo
{
	//
	public static final String BLOCKSMITH_DATA_DIR = "bs";
	
	// Imitator Item Texture (Iit)
	public static void writeIit(World world)
	{
		Iitexture iit = new Iitexture();
		
		// The client does not handle stuff like this
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()) { return; }
		
		String fileLoc = getPath(world) + "iit_" + getAvailableIit() + ".dat";
		
		(new File(getPath(world))).mkdirs();
		
		ModBlocksmith.logSide("FILE = " + fileLoc);
		
		File file = new File(fileLoc);
		
		ModBlocksmith.logSide("Checking to see if file exists...");
		/*if(!file.exists())
		{
			ModBlocksmith.logSide("Whoops! I couldn't find the file, I'm going to try make it.");
		    try
		    {
				file.createNewFile();
			}
		    catch (IOException e)
			{
				ModBlocksmith.logSide("Could not create the file!");
				e.printStackTrace();
			}
		}
		else
		{
			ModBlocksmith.logSide("It does! Horray.");
		}*/
		
		ModBlocksmith.logSide("Initing FOS");
		FileOutputStream fos = null;
		
		try
		{
			ModBlocksmith.logSide("Trying to make a FOS for our file");
			fos = new FileOutputStream(file);
		}
		catch (FileNotFoundException e)
		{
			ModBlocksmith.logSide("Could not find file!");
			e.printStackTrace();
		}
		finally
		{
			if(fos != null)
			{
				try
				{
					//
					ModBlocksmith.logSide("orangepeel");
					
					fos.write(iit.getData());
					
					fos.close();
				}
				catch (IOException e)
				{
					ModBlocksmith.logSide("two");
					e.printStackTrace();
				}
			}
		}
	}
	
	//
	public static String getPath(World world)
	{
		//FMLClientHandler.instance().getClient()
		
		try
		{
			// Return
			return
			
			/* This heap of stuff */
			Minecraft.getMinecraftDir().getCanonicalPath() + 
			"\\saves\\" + world.getSaveHandler().getWorldDirectoryName() + 
			"\\data\\" + BLOCKSMITH_DATA_DIR + 
			"\\world_" + world.provider.dimensionId + "\\";
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//
			return null;
		}
	}
	
	//
	public static int getAvailableIit()
	{
		return 7;
	}
}
