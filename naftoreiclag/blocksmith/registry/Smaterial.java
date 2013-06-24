package naftoreiclag.blocksmith.registry;

import java.util.ArrayList;
import java.util.List;

import naftoreiclag.blocksmith.ModBlocksmith;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Vector containing all information about a given smaterial
public class Smaterial
{
	// The name used in all those internal strings
	private String internalName;
	public String getInternalName() { return internalName + ""; }
	
	// The number used for metadata. WARNING: you cannot have multiple smaterials with a common metaId!
	private int metaId;
	public int getMetaId() { return metaId; }
	
	// Constructor
	protected Smaterial(String internalName, int metadata)
	{
		this.internalName = internalName;
		this.metaId = metadata;
	}
	
	// Description to be added to item names (e.g. "IRON sword", "lump of IRON", "IRON ingot")
	private String friendlyAdjective = "Baloney";
	public String getFriendlyAdjective() { return friendlyAdjective; }
	public Smaterial setFriendlyAdjective(String friendlyAdjective) { this.friendlyAdjective = friendlyAdjective; return this; }
	
	// At what temperature are lumps made (negative values indicate a non-lumpable form)
	private int meltingPoint = 500;
	public Smaterial setMeltingPoint(int meltingPoint) { this.meltingPoint = meltingPoint; return this; }
	
	// Whether you can make handles
	private boolean makesHandles = true;
	public Smaterial setMakesHandles(boolean makesHandles) { this.makesHandles = makesHandles; return this; }
	
	// Duribility each lump adds
	private int addedDurability = 5;
	public Smaterial setAddedDurability(int addedDurability) { this.addedDurability = addedDurability; return this; }
	
	// The ingots that can create this stuff
	private List<LumpRecipe> sourceIngots = new ArrayList<LumpRecipe>();
	
	// Add only a certain subtype of an ingot (In case a mod uses one item id for multiple ingots)
	public void addSourceIngot(int itemId, int metadata, int outputAmount)
	{
		sourceIngots.add(new LumpRecipe(itemId, metadata, outputAmount));
	}
	// Add an ingot with a custom amount of lumps (e.g. Dusts make only 9; Nuggets make only 2)
	public void addSourceIngot(int itemId, int outputAmount)
	{
		addSourceIngot(itemId, 0, outputAmount);
	}
	// Add an ingot that can be used to make the standard number of lumps
	public void addSourceIngot(int itemId)
	{
		addSourceIngot(itemId, 0, 18);
	}
	
	// A vector containing the input amount of material, and the output number of lumps
	protected class LumpRecipe
	{
		private int inputId;
		private int inputMetadata;
		private int outputAmount;
		
		protected LumpRecipe(int inputId, int inputMetadata, int outputAmount)
		{
			this.inputId = inputId;
			this.inputMetadata = inputMetadata;
			this.outputAmount = outputAmount;
		}
		
		public int getInputId()
		{
			return inputId;
		}
		
		public int getInputMetadata()
		{
			return inputMetadata;
		}
		
		public int getOutputAmount()
		{
			return outputAmount;
		}
	}
	
	// Different forms this smaterial can assume
	@SideOnly(Side.CLIENT)
	public Icon iconLump;
	@SideOnly(Side.CLIENT)
	public Icon iconLumpThin;

	// Registers them when appropriate
	@SideOnly(Side.CLIENT)
	protected void registerIcon(IconRegister iconRegister)
	{
		iconLump = iconRegister.registerIcon(ModBlocksmith.modid + ":lump_" + internalName);
		iconLumpThin = iconRegister.registerIcon(ModBlocksmith.modid + ":lumpThin_" + internalName);
	}
}
