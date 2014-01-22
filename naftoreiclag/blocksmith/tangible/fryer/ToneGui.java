package naftoreiclag.blocksmith.tangible.fryer;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.tangible.curingtray.CuringtrayContainer;
import naftoreiclag.blocksmith.tangible.curingtray.CuringtrayTentity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToneGui extends GuiContainer
{
	private ToneTentity tileEntity;
	
	public ToneGui(InventoryPlayer inventoryPlayer, ToneTentity tileEntity)
	{
		// Make a new container and then give it to the super class
		super(new ToneContainer(inventoryPlayer, tileEntity));
		
		this.tileEntity = tileEntity;
	}

	// Draw everything rendered in the layer "above" the items
	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2)
	{
		// (4210752 is the color...)
		
		// Reminds me of the good ol' days of Game Maker
		fontRenderer.drawString("Potatoes", 8, 6, 4210752);
		
		// draws "Inventory" or your regional equivalent
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	// Draw everything rendered in the layer "below" the items
	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + ModBlocksmith.modid + "/textures/gui/tone.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
	}

}