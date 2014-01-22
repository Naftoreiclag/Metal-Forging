package naftoreiclag.blocksmith.lib.model;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.tangible.bellow.BellowTentity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class ModelBellow
{
	private IModelCustom modelBase;
	private IModelCustom modelShaft;
	private IModelCustom modelTop;
	
	public ModelBellow()
	{
		modelBase = AdvancedModelLoader.loadModel("/mods/" + ModBlocksmith.modid + "/models/bellow_base.obj");
		modelShaft = AdvancedModelLoader.loadModel("/mods/" + ModBlocksmith.modid + "/models/bellow_shaft.obj");
		modelTop = AdvancedModelLoader.loadModel("/mods/" + ModBlocksmith.modid + "/models/bellow_top.obj");
	}

	public void render(BellowTentity tileEntity, double x, double y, double z, int metadata, float squishy)
	{
		/*
		 * BEGIN
		 */
		
		// Push
		GL11.glPushMatrix();
		
		// Move the cursor to the origin
		GL11.glTranslatef((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
		
		// Scale it down to normal size
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		
		// Texture
		/*switch(material)
		{
			case 0: FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + ModBlocksmith.modid + "/textures/models/bellowsPaper.png"); break;
			case 1: FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + ModBlocksmith.modid + "/textures/models/bellowsLeather.png"); break;
			case 2: FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + ModBlocksmith.modid + "/textures/models/bellowsTanned.png"); break;
		}*/
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + ModBlocksmith.modid + "/textures/models/bellowsTanned.png");
		
		/*
		 * BASE
		 */
		
		//
		GL11.glPushMatrix();
		
		// Rotate it to the correct position
		GL11.glRotatef(metadata * 90, 0.0f, 1.0f, 0.0f);
		
		//
		modelBase.renderAll();
		
		//
		GL11.glPopMatrix();
		
		/*
		 * SHAFT
		 */
			
		//
		GL11.glPushMatrix();
		
		GL11.glTranslatef(0.0f, -0.75f, 0.0f);
		GL11.glScalef(1.0f, squishy, 1.0f);
		
		modelShaft.renderAll();
		
		//
		GL11.glPopMatrix();
		
		/*
		 * TOP
		 */
			
		//
		GL11.glPushMatrix();
		
		GL11.glTranslatef(0.0f, -1.5f + (squishy * 1.5f), 0.0f);
		
		modelTop.renderAll();
		
		//
		GL11.glPopMatrix();
		
		/*
		 * END
		 */
		
		// Pop
		GL11.glPopMatrix();
	}
}
