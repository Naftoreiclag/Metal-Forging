package naftoreiclag.blocksmith.tangible.curingtray;

import naftoreiclag.blocksmith.ModBlocksmith;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCuringtray extends BlockContainer
{
	private static Icon icon_top;
	private static Icon icon_topLeather;
	private static Icon icon_side;
	private static Icon icon_bottom;
	
	// Usual stuff
	public BlockCuringtray(int par1, Material par2Material)
	{
		super(par1, par2Material);
		setCreativeTab(ModBlocksmith.creativetab_smithing);
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 3.0f / 8.0f, 1.0f);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean renderAsNormalBlock() { return false; }
	
	@Override
	public boolean isOpaqueCube() { return false; }
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata)
	{
		return side == 1 ? (metadata == 0 ? icon_top : icon_topLeather) : (side == 0 ? icon_bottom : icon_side);
	}
	
	// When you right-click it
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
	
	// When you break the block
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		// Drop whatever stuff was inside
		dropInventory(world, x, y, z);
		
		// Do whatever blocks are supposed to do after that
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	// Auxiliary function for dropping whatever stuff was inside
	private void dropInventory(World world, int x, int y, int z)
	{
		// Get the tile entity
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		// If it is not an inventory-based tile entity
		if(!(tileEntity instanceof IInventory))
		{
			// Then get outta here
			return;
		}
		
		// Cast it
		IInventory inventory = (IInventory) tileEntity;

		// For every item in the inventory
		for(int index = 0; index < inventory.getSizeInventory(); index ++)
		{
			// Get that stack
			ItemStack itemStackInInventory = inventory.getStackInSlot(index);

			// If it is valid
			if(itemStackInInventory != null && itemStackInInventory.stackSize > 0)
			{
				// Get the location
				float rx = world.rand.nextFloat();
				float ry = world.rand.nextFloat();
				float rz = world.rand.nextFloat();

				// Make a new item entity for that stack
				EntityItem itemEntity = new EntityItem(world, x + rx, y + ry, z + rz, itemStackInInventory.copy());

				// If it has a compound tag
				if (itemStackInInventory.hasTagCompound())
				{
					// Copy that over too
					itemEntity.getEntityItem().setTagCompound((NBTTagCompound) itemStackInInventory.getTagCompound().copy());
				}

				// Directional vectors to toss the items around
				float factor = 0.05F;
				itemEntity.motionX = world.rand.nextGaussian() * factor;
				itemEntity.motionY = world.rand.nextGaussian() * factor + 0.2F;
				itemEntity.motionZ = world.rand.nextGaussian() * factor;
				
				// Spawn it in
				world.spawnEntityInWorld(itemEntity);
				
				// Get rid of the old one
				itemStackInInventory = null;
			}
		}
	}
	
	// Iconz
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icon_top = par1IconRegister.registerIcon(ModBlocksmith.modid + ":curingTray_top");
		icon_topLeather = par1IconRegister.registerIcon(ModBlocksmith.modid + ":curingTray_topLeather");
		icon_bottom = par1IconRegister.registerIcon(ModBlocksmith.modid + ":curingTray_bottom");
		icon_side = par1IconRegister.registerIcon(ModBlocksmith.modid + ":curingTray_side");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new CuringtrayTentity();
	}
}
