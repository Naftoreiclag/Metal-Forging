package naftoreiclag.blocksmith.vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.tangible.putty.Bead;
import naftoreiclag.blocksmith.tangible.putty.Lump;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Vector containing all information about a given smaterial
public class Smaterial
{
	// Keeps track of all created smaterials
	/****************************
	 * Start Registry / Manager *
	 ****************************/
	
	// Keeps track of whether the icons were registered or not
	@SideOnly(Side.CLIENT)
	private static boolean smaterialIconsRegistered = false;
	
	// A list of all registered smaterials
	public static List<Smaterial> smList = new LinkedList<Smaterial>();
	
	// Makes a new smaterial and returns it
	public static Smaterial newSmaterial(int metadata, String internalName)
	{
		// Checks to make sure that smaterials are not being overwriten
		for(Smaterial s : smList)
		{
			// If they share the same value
			if(s.getMetaId() == metadata)
			{
				// Uh oh, someone is trying to overwrite it!
				
				// Warn them
				ModBlocksmith.logger.log(Level.WARNING, "Smithing material is being overwriten! Bugs imminent! (Metadata/Metaid: " + metadata + ")");
				ModBlocksmith.logger.log(Level.WARNING, "Attempting to replace " + s.getInternalName() + " with " + internalName + "!");
				
				// Remove the old one
				smList.remove(s);
			}
		}
		
		// Make a new smaterial
		Smaterial s = new Smaterial(internalName, metadata);
		smList.add(s);
		
		// Inform
		ModBlocksmith.logger.log(Level.INFO, "Smithing material: " + internalName + " registered with metaid: " + metadata);
		
		// Return
		return s;
	}
	
	// Register the icons if they haven't already been registered
	@SideOnly(Side.CLIENT)
	public static void registerSmaterialIcons(IconRegister iconRegister)
	{
		// Check to make sure that we haven't already registered the icons
		if(!smaterialIconsRegistered)
		{
			// Iterate through all smaterials
			for(Smaterial s : smList)
			{
				// Call its icon registry method
				s.registerIcon(iconRegister);
			}
			
			// Remember that we did this so we don't have to do it again
			smaterialIconsRegistered = true;
			
			// Inform
			ModBlocksmith.logger.log(Level.INFO, "Smith materials finalized! Loaded " + smList.size() + " smithing materials!");
		}
	}
	
	// Returns the appropriate smaterial from the given metadata
	public static Smaterial getSmaterialFromMetadata(int metadata)
	{
		// Iterate through all smaterials
		for(Smaterial s : smList)
		{
			// Check if its the one we want
			if(s.getMetaId() == metadata)
			{
				// Ooh it is! Return it
				return s;
			}
		}

		// Inform
		ModBlocksmith.logger.log(Level.SEVERE, "Cannot find smaterial with metaId: " + metadata + "! Returning null!");
		
		// Guess we didn't find it
		return null;
	}
	
	/**************************
	 * End Registry / Manager *
	 **************************/
	
	// The name used in all those internal strings
	private String internalName;
	public String getInternalName() { return internalName + ""; }
	
	// The number used for metadata. WARNING: you cannot have multiple smaterials with a common metaId!
	private int metaId;
	public int getMetaId() { return metaId; }
	
	// Constructor
	private Smaterial(String internalName, int metadata)
	{
		// The name used for internal function
		this.internalName = internalName;
		
		// The metadata for items
		this.metaId = metadata;
		
		// Sets the friendly adjective to be the internalName in proper case by default
		this.setFriendlyAdjective(internalName.substring(0, 1).toUpperCase() + internalName.substring(1).toLowerCase());
	}
	
	// Description to be added to item names (e.g. "IRON sword", "lump of IRON", "IRON ingot")
	private String friendlyAdjective = "Baloney";
	public String getFriendlyAdjective() { return friendlyAdjective; }
	public Smaterial setFriendlyAdjective(String friendlyAdjective) { this.friendlyAdjective = friendlyAdjective; return this; }
	
	// Aliases for beads (e.g. call Diamond Beads "Diamond Pebbles" instead, if you want to)
	private String friendlyBeadAlias = "Bead";
	public String getFriendlyBeadAlias() { return friendlyBeadAlias; }
	public Smaterial setFriendlyBeadAlias(String friendlyBeadAlias) { this.friendlyBeadAlias = friendlyBeadAlias; return this; }
	
	// At what temperature are lumps made (negative values indicate a non-lumpable form)
	private int meltingPoint = 500;
	public int getMeltingPoint() { return meltingPoint; }
	public Smaterial setMeltingPoint(int meltingPoint) { this.meltingPoint = meltingPoint; return this; }
	
	// Can you make beads from it?
	private boolean makesBeads = true;
	public boolean canMakeBeads() { return makesBeads; }
	public Smaterial setMakesBeads(boolean makesBeads) { this.makesBeads = makesBeads; return this; }
	
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
	public Icon iconBead;
	@SideOnly(Side.CLIENT)
	public Icon iconLump;
	//@SideOnly(Side.CLIENT)
	//public Icon iconLumpThin;

	// Registers them when appropriate
	@SideOnly(Side.CLIENT)
	protected void registerIcon(IconRegister iconRegister)
	{
		if(isValidForLumps())
		{
			iconLump = iconRegister.registerIcon(ModBlocksmith.modid + ":lump_" + internalName);
		}
		if(isValidForBeads())
		{
			iconBead = iconRegister.registerIcon(ModBlocksmith.modid + ":bead_" + internalName);
		}
		//iconLumpThin = iconRegister.registerIcon(ModBlocksmith.modid + ":lumpThin_" + internalName);
	}
	
	public boolean isValidForLumps()
	{
		return meltingPoint >= 0;
	}
	
	public boolean isValidForBeads()
	{
		return makesBeads;
	}
}
