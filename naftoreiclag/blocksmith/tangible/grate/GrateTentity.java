package naftoreiclag.blocksmith.tangible.grate;

import java.util.logging.Level;

import naftoreiclag.blocksmith.ModBlocksmith;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GrateTentity extends TileEntity
{
	public float doorRot = 0.0f;
	private float targRot = 0.0f;
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}
	
	@Override
	public void updateEntity()
	{
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		
		if(meta >= 0 && meta <= 3)
		{
			targRot = 0.0f;
		}
		else
		{
			targRot = 90.0f;
		}
		
		doorRot += (targRot - doorRot) / 2; 
	}
}
