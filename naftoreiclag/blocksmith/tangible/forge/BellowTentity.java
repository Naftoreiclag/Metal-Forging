package naftoreiclag.blocksmith.tangible.forge;

import java.util.logging.Level;

import naftoreiclag.blocksmith.ModBlocksmith;
import naftoreiclag.blocksmith.lib.Sounds;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BellowTentity extends TileEntity
{
	public float squishy = 0.0f;
	public boolean inhaling = false;
	private double squishyDir = 0.0d;
	private float squishyRelative = 0.0f;
	
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
		squishyDir += 0.15d;
		
		squishyRelative = ((float) (Math.sin(squishyDir) * 0.4d) + 0.6f) - squishy;
		
		squishy = squishy + squishyRelative;
		
		if(inhaling && squishyRelative < 0)
		{
			// play exhaling sound
			worldObj.playSoundEffect(xCoord + 0.5d, yCoord + 0.5d, zCoord + 0.5d, Sounds.BELLOW_EXHALE, 1.0f, worldObj.rand.nextFloat() * 0.1f + 0.9f);
		}
		else if(!inhaling && squishyRelative > 0)
		{
			// play inhaling sound
			worldObj.playSoundEffect(xCoord + 0.5d, yCoord + 0.5d, zCoord + 0.5d, Sounds.BELLOW_INHALE, 1.0f, worldObj.rand.nextFloat() * 0.1f + 0.9f);
		}
		
		inhaling = squishyRelative > 0;
	}
}
