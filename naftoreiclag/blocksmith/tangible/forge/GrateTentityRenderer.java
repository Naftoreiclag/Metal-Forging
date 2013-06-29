package naftoreiclag.blocksmith.tangible.forge;

import org.lwjgl.opengl.GL11;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.modelclass.ModelGrate;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class GrateTentityRenderer extends TileEntitySpecialRenderer
{
	private ModelGrate modelTutBox = new ModelGrate();
	
	private void renderTileEntityGrateAt(GrateTentity tileEntity, double x, double y, double z, float tick)
	{
		modelTutBox.render((GrateTentity) tileEntity, x, y, z, tileEntity.getBlockMetadata(), tileEntity.doorRot);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
	{
		renderTileEntityGrateAt((GrateTentity) tileEntity, x, y, z, tick);
	}
}
