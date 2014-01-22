package naftoreiclag.blocksmith.tangible.fryer;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.tangible.curingtray.CuringtrayTentity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTone extends BlockContainer
{

	public BlockTone(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		// Get the tile entity instance
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		// If it is invalid or if the player doesn't want to open the gui
		if(tileEntity == null || player.isSneaking())
		{
			// We did not accomplish anything here
			return false;
		}
		
		// Open the gui
		player.openGui(ModBlocksmith.instance, 0, world, x, y, z);
		
		// We did something
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		// TODO Auto-generated method stub
		return new CuringtrayTentity();
	}
}
