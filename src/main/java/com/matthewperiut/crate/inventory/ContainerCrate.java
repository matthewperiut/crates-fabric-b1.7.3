package com.matthewperiut.crate.inventory;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import com.matthewperiut.crate.blockitem.CrateBlockItem;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class ContainerCrate extends ContainerBase
{
    private static class CrateSlot extends Slot {

        public CrateSlot(InventoryBase arg, int i, int j, int k) {
            super(arg, i, j, k);
        }

        @Override
        public boolean canInsert(ItemInstance arg) {
            return !(arg.getType() instanceof CrateBlockItem);
        }
    }

    public ContainerCrate(PlayerInventory inventory, CrateBlockEntity crate) {
        int offset = 28;
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
                addSlot(new Slot(inventory, k + i * 9 + 9, 8 + k * 18, offset + 84 + i * 18));
            }
        }

        for(int j = 0; j < 9; j++)
        {
            addSlot(new Slot(inventory, j, 8 + j * 18, offset + 142));
        }

        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 4; k++)
            {
                addSlot(new CrateSlot(crate, (i * 4) + k, 53 + k * 18, 26 + (i * 18)));
            }
        }
    }

    @Override
    public boolean canUse(PlayerBase arg) {
        return true;
    }
}