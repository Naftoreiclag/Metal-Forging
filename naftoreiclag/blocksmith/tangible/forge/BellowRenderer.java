package naftoreiclag.blocksmith.tangible.forge;

import naftoreiclag.blocksmith.modelclass.ModelBellow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class BellowRenderer implements IItemRenderer
{
	private ModelBellow model;
	
	public BellowRenderer()
	{
		model = new ModelBellow();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch(type)
		{
			case ENTITY:
			{
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glScalef(1.2f, 1.2f, 1.2f);
				model.render(null, 0, 0, 0, 1, 0.7f);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				return;
			}
			
			case EQUIPPED:
			{
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glScalef(1.2f, 1.2f, 1.2f);
				model.render(null, 0, 0, 0, 1, 0.7f);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				return;
			}
			
			case EQUIPPED_FIRST_PERSON:
			{
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glScalef(1.2f, 1.2f, 1.2f);
				model.render(null, 0, 0, 0, 1, 0.7f);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				return;
			}
				
			case INVENTORY:
			{
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
				GL11.glScalef(1.2f, 1.2f, 1.2f);
				GL11.glTranslatef(0.0f, -0.87f, 0.0f);
				model.render(null, 0, 0, 0, 1, 0.7f);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				return;
			}
			
			default:return;
		}
	}
}