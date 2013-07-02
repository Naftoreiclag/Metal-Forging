package naftoreiclag.blocksmith.modelclass;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.tangible.forge.GrateTentity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class ModelGrate
{
	private IModelCustom modelFrame;
	private IModelCustom modelDoor;
	
	public ModelGrate()
	{
		modelFrame = AdvancedModelLoader.loadModel("/mods/" + ModBlocksmith.modid + "/models/grate_frame.obj");
		modelDoor = AdvancedModelLoader.loadModel("/mods/" + ModBlocksmith.modid + "/models/grate_door.obj");
	}
	
	public void render(GrateTentity tileEntity, double x, double y, double z, int metadata, float doorRot)
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
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + ModBlocksmith.modid + "/textures/models/grate.png");
		
		/*
		 * FRAME
		 */
		
		//
		GL11.glPushMatrix();
		
		// Rotate it to the correct position
		GL11.glRotatef(metadata * 90, 0.0f, 1.0f, 0.0f);
		
		//
		modelFrame.renderAll();
		
		//
		GL11.glPopMatrix();
		
		/*
		 * DOOR
		 */
			
		//
		GL11.glPushMatrix();
		
		// Rotate it to the correct position
		switch(metadata % 4)
		{
			//0.9375
			//0.0625
			//-0.4375
			case 0:
			{
				GL11.glTranslatef(0.875f, 0.0f, -0.875f);
				break;
			}
			case 1:
			{
				GL11.glTranslatef(-0.875f, 0.0f, -0.875f);
				break;
			}
			case 2:
			{
				GL11.glTranslatef(-0.875f, 0.0f, 0.875f);
				break;
			}
			case 3:
			{
				GL11.glTranslatef(0.875f, 0.0f, 0.875f);
				break;
			}
			default: break;
		}
		
		//GL11.glTranslatef(1.0f, 0.0f, -1.0f);
		GL11.glRotatef((metadata * 90) + doorRot, 0.0f, 1.0f, 0.0f);
		
		//
		GL11.glDisable(GL11.GL_CULL_FACE);
		modelDoor.renderAll();
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		//
		GL11.glPopMatrix();
		
		/*
		 * END
		 */
		
		// Pop
		GL11.glPopMatrix();
	}
}
