package naftoreiclag.blocksmith.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Sounds
{
	public static final String soundDir = "mods/nrBlocksmith/sounds/";
	public static final String soundDirDots = soundDir.replace('/', '.');
	
	public static final String GRATE_OPEN = soundDirDots + "grate_open";
	public static final String GRATE_CLOSE = soundDirDots + "grate_close";
	public static final String BELLOW_INHALE = soundDirDots + "bellow_inhale";
	public static final String BELLOW_EXHALE = soundDirDots + "bellow_exhale";
	
	public static final String[] sounds = 
	{
		soundDir + "grate_open.ogg",
		soundDir + "grate_close.ogg",
		soundDir + "bellow_inhale.ogg",
		soundDir + "bellow_exhale.ogg"
	};
}
