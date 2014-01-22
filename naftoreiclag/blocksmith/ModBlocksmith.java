package naftoreiclag.blocksmith;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import naftoreiclag.blocksmith.tangible.bellow.BellowRenderer;
import naftoreiclag.blocksmith.tangible.bellow.BellowTentity;
import naftoreiclag.blocksmith.tangible.bellow.BellowTentityRenderer;
import naftoreiclag.blocksmith.tangible.bellow.BlockBellow;
import naftoreiclag.blocksmith.tangible.curingtray.BlockCuringtray;
import naftoreiclag.blocksmith.tangible.curingtray.CuringtrayGuiHandler;
import naftoreiclag.blocksmith.tangible.curingtray.CuringtrayTentity;
import naftoreiclag.blocksmith.tangible.fryer.BlockTone;
import naftoreiclag.blocksmith.tangible.fryer.ToneGuiHandler;
import naftoreiclag.blocksmith.tangible.fryer.ToneTentity;
import naftoreiclag.blocksmith.tangible.grate.BlockGrate;
import naftoreiclag.blocksmith.tangible.grate.GrateRenderer;
import naftoreiclag.blocksmith.tangible.grate.GrateTentity;
import naftoreiclag.blocksmith.tangible.grate.GrateTentityRenderer;
import naftoreiclag.blocksmith.tangible.magnifyingglass.ItemMagnifyingGlass;
import naftoreiclag.blocksmith.tangible.misc.ItemMisc;
import naftoreiclag.blocksmith.tangible.putty.ItemBead;
import naftoreiclag.blocksmith.tangible.putty.ItemLump;
import naftoreiclag.blocksmith.vector.Smaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Declare that this is a mod
@Mod(modid = ModBlocksmith.modid, name = "Blocksmith", version = "indev_1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

// This is where the magic happens
public class ModBlocksmith
{
	// Mod Id
	public static final String modid = "nrBlocksmith";
	
	// Mod Instance
	@Instance(modid)
	public static ModBlocksmith instance;
	
	// Logger instance
	public static Logger logger;
	
	// Id offset
	public static final int idOffset = 2013;
	
	// Items
	public static Item item_lump;
	public static Item item_bead;
	public static Item item_misc;
	public static Item item_magnifyingGlass;
	
	// Blocks
	public static Block block_grate;
	public static Block block_bellow;
	public static Block block_curingtray;
	public static Block block_tone;
	
	// Do I really need these?
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
	private static Smaterial smat_steel;
	private static Smaterial smat_pigiron;
	private static Smaterial smat_glass;
	
	// Before mods are loaded
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		// Make the logger
		logger = Logger.getLogger(modid);
		logger.setParent(FMLLog.getLogger());
		
		// Info
		logSide(Level.INFO, "PRE INIT EVENT");
		
		// Event hooks
		registerEventHooks();
		
		// Read config files
	}
	
	// Load mods
	@Init
	public void load(FMLInitializationEvent event)
	{
		logSide(Level.INFO, "INIT EVENT");
		
		// creative tabs
		registerCreativeTabs();
		
		// items
		item_lump = new ItemLump(idOffset + 0).setUnlocalizedName("lump");
		item_bead = new ItemBead(idOffset + 1).setUnlocalizedName("bead");
		item_misc = new ItemMisc(idOffset + 4).setUnlocalizedName("miscItem");
		item_magnifyingGlass = new ItemMagnifyingGlass(idOffset + 7).setUnlocalizedName("magnifyingGlass");
		
		// blocks
		block_grate = new BlockGrate(idOffset + 2, Material.iron).setUnlocalizedName("grate");
		block_bellow = new BlockBellow(idOffset + 3, Material.iron).setUnlocalizedName("bellow");
		block_curingtray = new BlockCuringtray(idOffset + 6, Material.iron).setUnlocalizedName("curingtray");
		block_tone = new BlockTone(idOffset + 8, Material.iron).setUnlocalizedName("testOne");
		
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
		smat_steel = 		Smaterial.newSmaterial( 13, "steel").setMeltingPoint(2500).setMakesBeads(false); // rc
		smat_pigiron = 		Smaterial.newSmaterial( 14, "pigIron").setMeltingPoint(1700).setMakesBeads(false).setFriendlyAdjective("Pig Iron"); // me
		smat_glass = 		Smaterial.newSmaterial(255, "glass").setMeltingPoint(1500); // vanilla

		
	}
	
	// Called after mods have loaded, before title screen
	// Icons are registered here
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		logSide(Level.INFO, "POST INIT EVENT");
		
		// TODO: Interact with other mods
		
		// Name all the things
		registerTangibles();
		
		// Crafting recipes
		registerCraftingRecipes();
	}

	/** AUXILIARY FUNCTIONS **/
	
	// Auxiliary function for registering the items and blocks
	@SideOnly(Side.CLIENT)
	private void registerTangibles()
	{
		// Creative tabs
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabSmithing", "Smithing");
		
		// Lumps
		List<ItemStack> lumps = new LinkedList<ItemStack>();
		item_lump.getSubItems(item_lump.itemID, null, lumps);
		for(ItemStack i : lumps)
		{
			LanguageRegistry.addName(i, ((ItemLump) item_lump).getCompleteFriendlyName(i.getItemDamage()));
		}
		GameRegistry.registerItem(item_lump, modid + ".lump");

		// Beads
		List<ItemStack> beads = new LinkedList<ItemStack>();
		item_bead.getSubItems(item_bead.itemID, null, beads);
		for(ItemStack i : beads)
		{
			LanguageRegistry.addName(i, ((ItemBead) item_bead).getCompleteFriendlyName(i.getItemDamage()));
		}
		GameRegistry.registerItem(item_bead, modid + ".bead");
		
		// Grate
		LanguageRegistry.addName(block_grate, "Grate");
		GameRegistry.registerBlock(block_grate, modid + ".grate");
		MinecraftForgeClient.registerItemRenderer(block_grate.blockID, new GrateRenderer());
		
		// Grate Tile Entity
		GameRegistry.registerTileEntity(GrateTentity.class, modid + ".grateTileEntity");
		ClientRegistry.bindTileEntitySpecialRenderer(GrateTentity.class, new GrateTentityRenderer());
		
		// Bellow
		LanguageRegistry.addName(block_bellow, "Bellows");
		GameRegistry.registerBlock(block_bellow, modid + ".bellow");
		MinecraftForgeClient.registerItemRenderer(block_bellow.blockID, new BellowRenderer());
		
		// Bellow Tile Entity
		GameRegistry.registerTileEntity(BellowTentity.class, modid + ".bellowTileEntity");
		ClientRegistry.bindTileEntitySpecialRenderer(BellowTentity.class, new BellowTentityRenderer());
		
		// Misc Items
		for(int i = 1; i < ItemMisc.unlocalizedNames.length; i ++)
		{
			LanguageRegistry.addName(new ItemStack(item_misc.itemID, 1, i), ItemMisc.friendlyNames[i]);
		}
		GameRegistry.registerItem(item_misc, modid + ".miscItem");
		
		// Curing Tray
		LanguageRegistry.addName(block_curingtray, "Curing Tray");
		GameRegistry.registerBlock(block_curingtray, modid + ".curingtray");
		
		// Curing Tray Tile Entity
		GameRegistry.registerTileEntity(CuringtrayTentity.class, modid + ".curingtrayTileEntity");
		
		// Curing Tray Gui
		NetworkRegistry.instance().registerGuiHandler(this, new CuringtrayGuiHandler());
		
		// Magnifying Glass
		LanguageRegistry.addName(item_magnifyingGlass, "Magnifying Glass");
		GameRegistry.registerItem(item_misc, modid + ".magnifyingGlass");
		
		// Tone
		LanguageRegistry.addName(block_tone, "Test One");
		GameRegistry.registerBlock(block_tone, modid + ".testOne");
		
		// Tone Tile Entity
		GameRegistry.registerTileEntity(ToneTentity.class, modid + ".testOneTileEntity");
		
		// Tone Gui
		NetworkRegistry.instance().registerGuiHandler(this, new ToneGuiHandler());
	}
	
	// Creative tabs
	@SideOnly(Side.CLIENT)
	public static CreativeTabs creativetab_smithing;
	
	// Auxiliary function for registering creative mode tabs
	@SideOnly(Side.CLIENT)
	private void registerCreativeTabs()
	{
		creativetab_smithing = new CreativeTabs("tabSmithing")
		{
			public ItemStack getIconItemStack()
			{
				return new ItemStack(ModBlocksmith.item_lump, 1, 13);
			}
		};
	}
	
	// Auxiliary function for registering crafting recipes
	private void registerCraftingRecipes()
	{
		ItemStack ingrWoodenSlab = new ItemStack(Block.woodSingleSlab);
		ItemStack ingrWoodenStair = new ItemStack(Block.stairsWoodOak);
		ItemStack ingrString = new ItemStack(Item.silk);
		ItemStack ingrRails = new ItemStack(Block.rail);
		ItemStack ingrFence = new ItemStack(Block.fence);
		ItemStack ingrIronBars = new ItemStack(Block.fenceIron);
		ItemStack ingrIronDoor = new ItemStack(Item.doorIron);
		
		GameRegistry.addShapelessRecipe(new ItemStack(block_grate), ingrIronBars, ingrIronDoor);
		GameRegistry.addShapelessRecipe(new ItemStack(block_grate, 2), ingrIronBars, ingrIronBars, ingrIronDoor);
	}
	
	// Auxiliary function for registering event hooks
	private void registerEventHooks()
	{
		MinecraftForge.EVENT_BUS.register(new BlocksmithEventHooks());
	}
	
	/** LOGGER **/
	
	// Loggers that also display client or server side
	public static void logSide(Level level, String msg) // logs message with given level
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			logger.log(level, "Server: " + msg);
		}
		else if(FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			logger.log(level, "Client: " + msg);
		}
		else
		{
			logger.log(level, "Null-side:" + msg);
		}
	}
	public static void logSide(String msg) // logs message with level info
	{
		logSide(Level.INFO, msg);
	}
	public static void logSide() // logs the client or server side
	{
		logSide(Level.INFO, "is currently being used");
	}
}
