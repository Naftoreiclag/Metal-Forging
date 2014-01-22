package naftoreiclag.blocksmith.tangible.fryer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ToneGuiHandler implements IGuiHandler
{
	// Dunno what this does, returns container
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		// Get the tile entity at the position
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		// If it is valid
		if (tileEntity instanceof ToneTentity)
		{
			return new ToneContainer(player.inventory, (ToneTentity) tileEntity);
		}
		
		//
		return null;
	}

	// Dunno what this does either, returns gui
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		// Get the tile entity at the position
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		// If it is valid
		if (tileEntity instanceof ToneTentity)
		{
			return new ToneGui(player.inventory, (ToneTentity) tileEntity);
		}
		
		//
		return null;
	}
}
