package naftoreiclag.blocksmith.tangible.lump;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.registry.RegistrySmaterial;
import naftoreiclag.blocksmith.registry.Smaterial;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

// Plain ol' lump, nothing but a way for smaterials and items to interact
public class Lump extends Item
{
	// Constructor
	public Lump(int id)
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
		int metadata = itemStack.getItemDamage();
		
		for(Smaterial s : RegistrySmaterial.smList)
		{
			if(s.getMetaId() == metadata)
			{
		        return super.getUnlocalizedName() + "." + s.getInternalName();
			}
		}

        return super.getUnlocalizedName() + ".error";
    }
	
	// Returns the friendly adjective; used by LanguageRegistery since this class can't access the language
	public String getFriendlyAdjective(int metadata)
    {
		for(Smaterial s : RegistrySmaterial.smList)
		{
			if(s.getMetaId() == metadata)
			{
				return s.getFriendlyAdjective();
			}
		}
		return "Erroneous";
    }
	
	// Gets all subitems for the creative tabs; reused to get all metadatas
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubItems(int id, CreativeTabs par2CreativeTabs, List listOfItemStacks)
    {
		for(Smaterial s : RegistrySmaterial.smList)
		{
            listOfItemStacks.add(new ItemStack(id, 1, s.getMetaId()));
        }
    }
	
	// Returns the right visual for given metadata
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int metadata)
    {
		for(Smaterial s : RegistrySmaterial.smList)
		{
			if(s.getMetaId() == metadata)
			{
				return s.iconLump;
			}
		}
		return itemIcon;
    }
	
	// Called on init
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		// Tells the smaterials to register their icons
		RegistrySmaterial.registerSmaterialIcons(iconRegister);
		
		// Just in case something weird happens
		itemIcon = iconRegister.registerIcon(ModBlocksmith.modid + ":error");
	}
}
