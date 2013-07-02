package naftoreiclag.blocksmith.tangible.forge;

import naftoreiclag.blocksmith.modelclass.ModelBellow;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class BellowTentityRenderer extends TileEntitySpecialRenderer
{
	private ModelBellow model = new ModelBellow();
	
	private void renderTileEntityBellowAt(BellowTentity tileEntity, double x, double y, double z, float tick)
	{
		model.render(tileEntity, x, y, z, tileEntity.getBlockMetadata(), tileEntity.squishy);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
	{
		renderTileEntityBellowAt((BellowTentity) tileEntity, x, y, z, tick);
	}
}
