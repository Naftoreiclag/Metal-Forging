package naftoreiclag.blocksmith;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import naftoreiclag.blocksmith.registry.RegistrySmaterial;
import naftoreiclag.blocksmith.registry.Smaterial;
import naftoreiclag.blocksmith.tangible.lump.Lump;
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
	private static Smaterial smat_glass;
	
	//
	public static Item item_lump;
	
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
    	item_lump = new Lump(idOffset + 0);
    	
    	// smaterials
    	smat_iron = RegistrySmaterial.newSmaterial(0, "iron").setFriendlyAdjective("Iron").setMeltingPoint(1500); // vanilla
    	smat_copper = RegistrySmaterial.newSmaterial(1, "copper").setFriendlyAdjective("Copper").setMeltingPoint(1000); // ic2
    	smat_gold = RegistrySmaterial.newSmaterial(2, "gold").setFriendlyAdjective("Gold").setMeltingPoint(1000); // vanila
    	smat_tin = RegistrySmaterial.newSmaterial(3, "tin").setFriendlyAdjective("Tin").setMeltingPoint(250); // ic2
    	smat_silver = RegistrySmaterial.newSmaterial(4, "silver").setFriendlyAdjective("Silver").setMeltingPoint(1000); // rp2
    	smat_glass = RegistrySmaterial.newSmaterial(255, "glass").setFriendlyAdjective("Glass").setMeltingPoint(1500); // vanilla
    	
    	// Name all the lumps
    	List<ItemStack> lumps = new LinkedList<ItemStack>();
    	item_lump.getSubItems(item_lump.itemID, null, lumps);
    	for(ItemStack i : lumps)
    	{
    		LanguageRegistry.addName(i, ((Lump) item_lump).getFriendlyAdjective(i.getItemDamage()) + " Lump");
    	}
		GameRegistry.registerItem(item_lump, modid + ".lump");
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
		logger.log(Level.INFO, "POST INIT EVENT");
		
    	// Interact with other mods
    }
}
