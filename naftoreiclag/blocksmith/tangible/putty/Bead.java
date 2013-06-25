package naftoreiclag.blocksmith.tangible.putty;

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

// Copied from lump
public class Bead extends Item
{
	// Constructor
	public Bead(int id)
	{
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	// Returns appropriate name from a certain metadata
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		Smaterial s = Smaterial.getSmaterialFromMetadata(itemStack.getItemDamage());
		if(s != null)
		{
			return getUnlocalizedName() + "." + s.getInternalName();
		}
		return getUnlocalizedName() + ".error";
	}
	
	// Returns the friendly adjective and the noun; used by LanguageRegistery since this class can't access the language
	public String getCompleteFriendlyName(int metadata)
	{
		return getFriendlyAdjective(metadata) + " " + getFriendlyName(metadata);
	}
	
	// Get the friendly adjective of this item from the given metadata
	protected String getFriendlyAdjective(int metadata)
	{
		for(Smaterial s : Smaterial.smList)
		{
			if(s.getMetaId() == metadata)
			{
				return s.getFriendlyAdjective();
			}
		}
		return "Erroneous";
	}
	
	// Get the friendly name of this item from the given metadata
	// Override me for subclasses
	protected String getFriendlyName(int metadata)
	{
		Smaterial s = Smaterial.getSmaterialFromMetadata(metadata);
		if(s != null)
		{
			return s.getFriendlyBeadAlias();
		}
		return "Gitch!";
	}
	
	// Gets all subitems for the creative tabs; reused to get all metadatas
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int id, CreativeTabs par2CreativeTabs, List listOfItemStacks)
	{
		for(Smaterial s : Smaterial.smList)
		{
			if(validWith(s))
			{
				listOfItemStacks.add(new ItemStack(id, 1, s.getMetaId()));
			}
		}
	}

	// Returns the right visual for given metadata
	// Override me for subclasses
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int metadata)
	{
		Smaterial s = Smaterial.getSmaterialFromMetadata(metadata);
		if(s != null)
		{
			return s.iconBead;
		}
		
		return itemIcon;
	}
	
	// Is this smaterial intended for making this item?
	// Override it for subclasses
	public boolean validWith(Smaterial smaterial)
	{
		return smaterial.isValidForBeads();
	}
	
	// Called on post-init
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		// Tells the smaterials to register their icons
		Smaterial.registerSmaterialIcons(iconRegister);
		
		// Just in case something weird happens
		itemIcon = iconRegister.registerIcon(ModBlocksmith.modid + ":error");
	}
}
