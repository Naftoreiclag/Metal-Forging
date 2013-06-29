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
	
	private Boolean funny;
	
	public GrateTentity()
	{
		ModBlocksmith.logSide(Level.INFO, "tile entity created at " + xCoord + ", " + yCoord + ", " + zCoord);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		ModBlocksmith.logSide(Level.INFO, "writing!");
		super.writeToNBT(nbt);
		nbt.setBoolean("funny", isFunny());
		ModBlocksmith.logSide(Level.INFO, "funny is set to " + isFunny());
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		ModBlocksmith.logSide(Level.INFO, "readin!");
		super.readFromNBT(nbt);
		
		ModBlocksmith.logSide(Level.INFO, xCoord + ", " + yCoord + ", " + zCoord);
		
		if(nbt.hasKey("funny"))
		{
			ModBlocksmith.logSide(Level.INFO, "funny was found");
			ModBlocksmith.logSide(Level.INFO, "funny found is: " + nbt.getBoolean("funny"));
			setFunny(nbt.getBoolean("funny"));
		}
		else
		{
			ModBlocksmith.logSide(Level.INFO, "funny was not found");
			setFunny(false);
		}
		ModBlocksmith.logSide(Level.INFO, "funny is " + funny);
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
	
	public boolean isFunny()
	{
		if(funny == null)
		{
			return false;
		}
		return funny;
	}
	
	public void setFunny(boolean funny)
	{
		this.funny = funny;
	}
}
