package naftoreiclag.blocksmith.registry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.tangible.lump.Lump;
import net.minecraft.client.renderer.texture.IconRegister;

// Handles all loaded smaterials
public class RegistrySmaterial
{
	// Keeps track of whether the icons were registered or not
	private static boolean smaterialIconsRegistered = false;
	
	// A list of all registered smaterials
	public static List<Smaterial> smList = new LinkedList<Smaterial>();
	
	// Makes a new smaterial and returns it
	public static Smaterial newSmaterial(int metadata, String internalName)
	{
		Smaterial s = new Smaterial(internalName, metadata);
		smList.add(s);
		
		ModBlocksmith.logger.log(Level.INFO, "Smithing material: " + internalName + " registered with metaid: " + metadata + "");
		
		return s;
	}
	
	// Makes a nuw unnamed smaterial (Try not to use this one)
	public static Smaterial newSmaterial(int metadata)
	{
		return newSmaterial(metadata, "unnamedSmaterial-" + metadata);
	}
	
	// Register the icons if they haven't already been registered
	@SideOnly(Side.CLIENT)
	public static void registerSmaterialIcons(IconRegister iconRegister)
	{
		if(!smaterialIconsRegistered)
		{
			for(Smaterial s : smList)
			{
				s.registerIcon(iconRegister);
			}
			smaterialIconsRegistered = true;
		}
		
		ModBlocksmith.logger.log(Level.INFO, "Smith materials finalized! Loaded " + smList.size() + " smithing materials!");
	}
}
