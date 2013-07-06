package naftoreiclag.blocksmith.tangible.curingtray;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CuringtrayGuiHandler implements IGuiHandler
{
	// Dunno what this does, returns container
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		// Get the tile entity at the position
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		// If it is valid
		if (tileEntity instanceof CuringtrayTentity)
		{
			// Return a new curing tray
			return new CuringtrayContainer(player.inventory, (CuringtrayTentity) tileEntity);
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
		if (tileEntity instanceof CuringtrayTentity)
		{
			// Return a new curing tray
			return new CuringtrayGui(player.inventory, (CuringtrayTentity) tileEntity);
		}
		
		//
		return null;
	}
}
