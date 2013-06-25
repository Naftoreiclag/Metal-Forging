package naftoreiclag.blocksmith;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import naftoreiclag.blocksmith.tangible.putty.Bead;
import naftoreiclag.blocksmith.tangible.putty.Lump;
import naftoreiclag.blocksmith.vector.Smaterial;
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
		smat_iron = 		Smaterial.newSmaterial(  0, "iron").setMeltingPoint(1500); // vanilla
		smat_copper = 		Smaterial.newSmaterial(  1, "copper").setMeltingPoint(1000); // ic2
		smat_gold = 		Smaterial.newSmaterial(  2, "gold").setMeltingPoint(1000); // vanila
		smat_tin = 			Smaterial.newSmaterial(  3, "tin").setMeltingPoint(250); // ic2
		smat_silver = 		Smaterial.newSmaterial(  4, "silver").setMeltingPoint(1000); // rp2
		smat_diamond = 		Smaterial.newSmaterial(  5, "diamond").setMeltingPoint(-1); // vanilla
		smat_emerald = 		Smaterial.newSmaterial(  6, "emerald").setMeltingPoint(-1); // vanilla
		smat_ender = 		Smaterial.newSmaterial(  7, "ender").setMeltingPoint(-1).setFriendlyAdjective("Pearly"); // vanilla
		smat_flint = 		Smaterial.newSmaterial(  8, "flint").setMeltingPoint(-1); // vanilla
		smat_glowstone = 	Smaterial.newSmaterial(  9, "glowstone").setMeltingPoint(-1).setFriendlyAdjective("Glowing"); // vanilla
		smat_lapis = 		Smaterial.newSmaterial( 10, "lapis").setMeltingPoint(-1).setFriendlyAdjective("Lazurite"); // vanilla
		smat_quartz = 		Smaterial.newSmaterial( 11, "quartz").setMeltingPoint(-1); // vanilla
		smat_redstone = 	Smaterial.newSmaterial( 12, "redstone").setMeltingPoint(-1); // vanilla
		smat_glass = 		Smaterial.newSmaterial(255, "glass").setMeltingPoint(1500); // vanilla
		
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
