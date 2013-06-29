package naftoreiclag.blocksmith.tangible.forge;

import java.util.logging.Level;

import naftoreiclag.blocksmith.ModBlocksmith;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GrateTentity extends TileEntity
{
	public float doorRot = 0.0f;
	private float targRot = 0.0f;
	
	public GrateTentity()
	{
		ModBlocksmith.logSide(Level.INFO, "tile entity created at " + xCoord + ", " + yCoord + ", " + zCoord);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		ModBlocksmith.logSide(Level.INFO, "writing!");
		super.writeToNBT(nbt);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		ModBlocksmith.logSide(Level.INFO, "readin!");
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
