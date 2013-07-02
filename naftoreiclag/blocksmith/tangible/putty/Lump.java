package naftoreiclag.blocksmith.tangible.putty;

import naftoreiclag.blocksmith.vector.Smaterial;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Plain ol' lump, nothing but a way for smaterials and items to interact
public class Lump extends Bead
{
	// Constructor
	public Lump(int id)
	{
		super(id);
	}
	
	// Get the friendly name of this item from the given metadata
	@Override
	protected String getFriendlyName(int metadata)
	{
		return "Lump";
	}
	
	// Returns the right visual for given metadata
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int metadata)
	{
		Smaterial s = Smaterial.getSmaterialFromMetadata(metadata);
		if(s != null)
		{
			return s.iconLump;
		}
		
		return itemIcon;
	}
	
	// Is this smaterial intended for making this item?
	@Override
	public boolean validWith(Smaterial smaterial)
	{
		return smaterial.isValidForLumps();
	}
}
