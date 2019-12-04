package dev.emi.trinkets;

import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.container.Slot;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/**
 * A slot for a TrinketInventory, only properly utilized in the survival inventory due to restrictions in the creative inventory
 */
public class TrinketSlot extends Slot {
	public String group, slot;
	public boolean keepVisible;

	public TrinketSlot(Inventory inventory, int id, int x, int y, String group, String slot) {
		super(inventory, id, x, y);
		this.group = group;
		this.slot = slot;
	}

	public boolean canInsert(ItemStack itemStack) {
		if (group.equals(SlotGroups.CHEST) && slot.equals(Slots.CAPE) && itemStack.getItem() == Items.ELYTRA) return true;
		if (itemStack.getItem() instanceof ITrinket) {
			ITrinket trinket = (ITrinket) itemStack.getItem();
			return trinket.canWearInSlot(group, slot) && trinket.canInsert(itemStack);
		}
		return false;
	}

	@Override
	public boolean canTakeItems(PlayerEntity player) {
		ItemStack stack = this.getStack();
		if (EnchantmentHelper.hasBindingCurse(stack)) {
			return false;
		}
		if (stack.getItem() instanceof ITrinket) {
			return ((ITrinket) stack.getItem()).canTake(stack);
		}
		return super.canTakeItems(player);
	}

	@Override
	public void setStack(ItemStack stack) {
		super.setStack(stack);
	}

	@Override
	public ItemStack takeStack(int i) {
		return super.takeStack(i);
	}
}