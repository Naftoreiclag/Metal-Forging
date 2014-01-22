package naftoreiclag.blocksmith.tangible.magnifyingglass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.util.worldio.WorldIo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class ItemMagnifyingGlass extends Item
{
	
	public ItemMagnifyingGlass(int par1)
	{
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int blockX, int blockY, int blockZ, int par7, float par8, float par9, float par10)
    {
		WorldIo.writeIit(world);
		
		return true;
		
		/*if(entityPlayer.isSneaking())
		{
			int blockId = world.getBlockId(blockX, blockY, blockZ);
			TileEntity tileEntity = world.getBlockTileEntity(blockX, blockY, blockZ);
			
			if(blockId == Block.furnaceBurning.blockID || blockId == Block.furnaceIdle.blockID)
			{
				if(tileEntity != null)
				{
					if(tileEntity instanceof TileEntityFurnace)
					{
						TileEntityFurnace t = (TileEntityFurnace) tileEntity;
						ItemStack fuel = t.getStackInSlot(1);
						int rp = 0;
						if(fuel != null)
						{
							rp += TileEntityFurnace.getItemBurnTime(fuel) / 200;// * fuel.stackSize;
						}
						//rp += t.furnaceBurnTime / 200;
						
						entityPlayer.sendChatToPlayer
						(
							"This furnace has the potential to cook " + rp + " items."
						);
						
						return true;
					}
				}
			}
		}
		
		return false;*/
    }
	
	/*@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
	
	@Override
	public boolean isFull3D()
    {
        return true;
    }*/

	// Called on post-init
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		// Just in case something weird happens
		itemIcon = iconRegister.registerIcon(ModBlocksmith.modid + ":magnifyingGlass");
	}
}
