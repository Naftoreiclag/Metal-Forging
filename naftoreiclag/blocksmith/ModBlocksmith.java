package naftoreiclag.blocksmith;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import naftoreiclag.blocksmith.registry.RegistrySmaterial;
import naftoreiclag.blocksmith.registry.Smaterial;
import naftoreiclag.blocksmith.tangible.putty.Bead;
import naftoreiclag.blocksmith.tangible.putty.Lump;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

// Declare that this is a mod
@Mod(modid = ModBlocksmith.modid, name = "Blocksmith", version = "indev_1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

// This is where the magic happens
public class ModBlocksmith
{
	// Mod id
	public static final String modid = "nrBlocksmith";
	
	//
	public static Logger logger;
	
	// Id offset
	public static final int idOffset = 2013;
	
	//
	private static Smaterial smat_iron;
	private static Smaterial smat_copper;
	private static Smaterial smat_gold;
	private static Smaterial smat_tin;
	private static Smaterial smat_silver;
	private static Smaterial smat_diamond;
	private static Smaterial smat_emerald;
	private static Smaterial smat_ender;
	private static Smaterial smat_flint;
	private static Smaterial smat_glowstone;
	private static Smaterial smat_lapis;
	private static Smaterial smat_quartz;
	private static Smaterial smat_redstone;
	private static Smaterial smat_glass;
	
	//
	public static Item item_lump;
	public static Item item_bead;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = Logger.getLogger(modid);
		logger.setParent(FMLLog.getLogger());
		
		logger.log(Level.INFO, "PRE INIT EVENT");
		
		// Read config files
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		logger.log(Level.INFO, "INIT EVENT");
		
		// lump
		item_lump = new Lump(idOffset + 0).setUnlocalizedName("lump");
		item_bead = new Bead(idOffset + 1).setUnlocalizedName("bead");
		
		// smaterials
		smat_iron = RegistrySmaterial.newSmaterial(0, "iron").setFriendlyAdjective("Iron").setMeltingPoint(1500).setMakesBeads(true); // vanilla
		smat_copper = RegistrySmaterial.newSmaterial(1, "copper").setFriendlyAdjective("Copper").setMeltingPoint(1000).setMakesBeads(true); // ic2
		smat_gold = RegistrySmaterial.newSmaterial(2, "gold").setFriendlyAdjective("Gold").setMeltingPoint(1000).setMakesBeads(true); // vanila
		smat_tin = RegistrySmaterial.newSmaterial(3, "tin").setFriendlyAdjective("Tin").setMeltingPoint(250).setMakesBeads(true); // ic2
		smat_silver = RegistrySmaterial.newSmaterial(4, "silver").setFriendlyAdjective("Silver").setMeltingPoint(1000).setMakesBeads(true); // rp2
		smat_diamond = RegistrySmaterial.newSmaterial(5, "diamond").setFriendlyAdjective("Diamond").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_emerald = RegistrySmaterial.newSmaterial(6, "emerald").setFriendlyAdjective("Emerald").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_ender = RegistrySmaterial.newSmaterial(7, "ender").setFriendlyAdjective("Pearly").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_flint = RegistrySmaterial.newSmaterial(8, "flint").setFriendlyAdjective("Flint").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_glowstone = RegistrySmaterial.newSmaterial(9, "glowstone").setFriendlyAdjective("Glowing").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_lapis = RegistrySmaterial.newSmaterial(10, "lapis").setFriendlyAdjective("Lazurite").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_quartz = RegistrySmaterial.newSmaterial(11, "quartz").setFriendlyAdjective("Quartz").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_redstone = RegistrySmaterial.newSmaterial(12, "redstone").setFriendlyAdjective("Redstone").setMeltingPoint(-1).setMakesBeads(true); // vanilla
		smat_glass = RegistrySmaterial.newSmaterial(255, "glass").setFriendlyAdjective("Glass").setMeltingPoint(1500).setMakesBeads(true); // vanilla
		
		// Name all the things
		List<ItemStack> lumps = new LinkedList<ItemStack>();
		item_lump.getSubItems(item_lump.itemID, null, lumps);
		for(ItemStack i : lumps)
		{
			LanguageRegistry.addName(i, ((Lump) item_lump).getCompleteFriendlyName(i.getItemDamage()));
		}
		GameRegistry.registerItem(item_lump, modid + ".lump");

		List<ItemStack> beads = new LinkedList<ItemStack>();
		item_bead.getSubItems(item_bead.itemID, null, beads);
		for(ItemStack i : beads)
		{
			LanguageRegistry.addName(i, ((Bead) item_bead).getCompleteFriendlyName(i.getItemDamage()));
		}
		GameRegistry.registerItem(item_bead, modid + ".bead");
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.log(Level.INFO, "POST INIT EVENT");
		
		// Interact with other mods
	}
}
