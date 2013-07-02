package naftoreiclag.blocksmith;

import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlocksmithEventHooks
{
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		for(String sound : naftoreiclag.blocksmith.lib.Sounds.sounds)
		{
			try
			{
				event.manager.soundPoolSounds.addSound(sound, ModBlocksmith.class.getResource("/" + sound));
			} 
			catch (Exception e)
			{
				ModBlocksmith.logSide(Level.WARNING, "Failed to register sound at " + sound);
			}
		}
	}
}
