package naftoreiclag.blocksmith.tangible.fryer;

import naftoreiclag.blocksmith.tangible.curingtray.CuringtrayTentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ToneContainer extends Container
{
	// Pointer for whatever tile entity this container belongs to
	protected ToneTentity tileEntity;
	
	public static final int size = 10;

	// Constructor
	public ToneContainer(InventoryPlayer playerInventory, ToneTentity curingtrayTentity)
	{
		// Remember who your tile entity is
		tileEntity = curingtrayTentity;

		// Hot-bar
		for(int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
		
		// Inventory
		for(int y = 0; y < 3; y++) // 3 Rows
		{
			for(int x = 0; x < 9; x++) // 9 Columns
			{
				addSlotToContainer(new Slot(playerInventory, x + (y * 9) + 9, 8 + x * 18, 84 + y * 18));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
	{
		ItemStack stack = null;
		
		Slot slot = (Slot) inventorySlots.get(slotId);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if(slot != null && slot.getHasStack())
		{
			ItemStack stackInSlot = slot.getStack();
			
			stack = stackInSlot.copy();

			/** Do the actual shifting **/
			
			// If the slot id is within our storage
			if(slotId < size)
			{
				// Try transfer it to the player inventory
				if(!mergeItemStack(stackInSlot, size, 35, true))
				{
					// If it did not work, return null
					return null;
				}
			}
			
			// If the slot id is within the player inventory
			// Try transfer it to out storage
			else if(!mergeItemStack(stackInSlot, 0, size, false))
			{
				// If it did not work, return null
				return null;
			}

			/** Update the slot **/
			
			// Check if it is now zero
			if(stackInSlot.stackSize == 0)
			{
				// Fix it
				slot.putStack(null);
			}
			
			// It's good
			else
			{
				// Update it
				slot.onSlotChanged();
			}
		}
		
		// Yipee
		return stack;
	}
}