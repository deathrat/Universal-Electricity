package basiccomponents.common.container;

import basiccomponents.common.tileentity.TileEntityBatteryBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import universalelectricity.core.implement.IItemElectric;
import universalelectricity.prefab.SlotElectricItem;

public class ContainerBatteryBox extends Container
{
	private TileEntityBatteryBox tileEntity;

	public ContainerBatteryBox(InventoryPlayer par1InventoryPlayer, TileEntityBatteryBox batteryBox)
	{
		this.tileEntity = batteryBox;
		// Top slot for battery output
		this.addSlotToContainer(new SlotElectricItem(batteryBox, 0, 33, 24));
		// Bottom slot for batter input
		this.addSlotToContainer(new SlotElectricItem(batteryBox, 1, 33, 48));
		int var3;

		for (var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
		}

		tileEntity.openChest();
	}

	public void onCraftGuiClosed(EntityPlayer entityplayer)
	{
		super.onCraftGuiClosed(entityplayer);
		tileEntity.closeChest();
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called to transfer a stack from one inventory to the other eg. when shift clicking.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
	{
		ItemStack var2 = null;
		Slot var3 = (Slot) this.inventorySlots.get(par1);

		if (var3 != null && var3.getHasStack())
		{
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();

			if (par1 != 0 && par1 != 1)
			{
				if (this.getSlot(0).isItemValid(var4))
				{
					if (((IItemElectric) var4.getItem()).canProduceElectricity())
					{
						if (!this.mergeItemStack(var4, 1, 2, false)) { return null; }
					}
					else
					{
						if (!this.mergeItemStack(var4, 0, 1, false)) { return null; }
					}
				}

				else if (par1 >= 30 && par1 < 38 && !this.mergeItemStack(var4, 3, 30, false)) { return null; }
			}
			else if (!this.mergeItemStack(var4, 3, 38, false)) { return null; }

			if (var4.stackSize == 0)
			{
				var3.putStack((ItemStack) null);
			}
			else
			{
				var3.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize) { return null; }

			var3.onPickupFromSlot(par1EntityPlayer, var4);
		}

		return var2;
	}
}
