package naftoreiclag.blocksmith.tangible.misc;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.vector.Smaterial;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

// Allows for reusing the same item id for items that are not tools or armors
public class ItemMisc extends Item
{
	public static final int CURED_LEATHER = 1;
	public static final int TANNED_LEATHER = 2;
	
	public static final String[] unlocalizedNames = 
	{
		"",
		"leatherCured",
		"leatherTanned"
	};
	
	public static final String[] friendlyNames = 
	{
		"",
		"Cured Leather",
		"Tanned Leather"
	};
	
	public static Icon[] metadataIcons = new Icon[unlocalizedNames.length];
	
	public ItemMisc(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	// Returns appropriate name from a certain metadata
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int metadata = itemStack.getItemDamage();
		
		if(metadata >= 1 && metadata < unlocalizedNames.length)
		{
			return getUnlocalizedName() + "." + unlocalizedNames[itemStack.getItemDamage()];
		}
		else
		{
			return getUnlocalizedName() + ".glitch";
		}
	}
	
	// Get the friendly name of this item from the given metadata
	protected String getFriendlyName(int metadata)
	{
		if(metadata >= 1 && metadata < friendlyNames.length)
		{
			return getUnlocalizedName() + "." + friendlyNames[metadata];
		}
		else
		{
			return getUnlocalizedName() + ".glitch";
		}
	}
	
	// Gets all subitems for the creative tabs; reused to get all metadatas
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int id, CreativeTabs par2CreativeTabs, List listOfItemStacks)
	{
		for(int i = 1; i < unlocalizedNames.length; i ++)
		{
			listOfItemStacks.add(new ItemStack(id, 1, i));
		}
	}
	
	// Returns the right visual for given metadata
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int metadata)
	{
		if(metadata >= 1 && metadata < friendlyNames.length)
		{
			return metadataIcons[metadata];
		}
		else
		{
			return itemIcon;
		}
	}
	
	// Called on post-init
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		// Just in case something weird happens
		itemIcon = iconRegister.registerIcon(ModBlocksmith.modid + ":error");
		
		for(int i = 1; i < unlocalizedNames.length; i ++)
		{
			if(!unlocalizedNames[i].endsWith("SK"))
			{
				metadataIcons[i] = iconRegister.registerIcon(ModBlocksmith.modid + ":" + unlocalizedNames[i]);
			}
			else
			{
				metadataIcons[i] = itemIcon;
			}
		}
	}
}
