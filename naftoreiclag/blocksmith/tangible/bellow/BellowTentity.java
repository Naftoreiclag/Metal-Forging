package naftoreiclag.blocksmith.tangible.bellow;

import naftoreiclag.blocksmith.lib.Sounds;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BellowTentity extends TileEntity
{
	protected float squishy = 0.0f;
	public boolean inhaling = false;
	private double squishyDir = 0.0d;
	private float squishyRelative = 0.0f;
	
	// Teststuffs
	protected byte material;
	
	public BellowTentity setMetadataMaterial(byte material)
	{
		this.material = material;
		
		return this;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setByte("material", material);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		material = nbt.getByte("material");
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
			worldObj.playSoundEffect(xCoord + 0.5d, yCoord + 0.5d, zCoord + 0.5d, Sounds.BELLOW_EXHALE, 0.5f, worldObj.rand.nextFloat() * 0.1f + 0.9f);
		}
		else if(!inhaling && squishyRelative > 0)
		{
			// play inhaling sound
			worldObj.playSoundEffect(xCoord + 0.5d, yCoord + 0.5d, zCoord + 0.5d, Sounds.BELLOW_INHALE, 0.5f, worldObj.rand.nextFloat() * 0.1f + 0.9f);
		}
		
		inhaling = squishyRelative > 0;
	}
}
