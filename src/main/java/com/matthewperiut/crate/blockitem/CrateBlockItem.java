package com.matthewperiut.crate.blockitem;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateBlockItem;

public class CrateBlockItem extends TemplateBlockItem implements CustomTooltipProvider {
    public CrateBlockItem(int i) {
        super(i);
        setMaxCount(1);
    }

    @Override
    public boolean useOnBlock(ItemStack arg, PlayerEntity arg2, World level, int i, int j, int k, int l) {
        boolean value = super.useOnBlock(arg, arg2, level, i, j, k, l);;
        if (value) {
            if (level.getBlockId(i, j, k) == Block.SNOW.id) {
                l = 0;
            } else {
                if (l == 0) {
                    --j;
                }

                if (l == 1) {
                    ++j;
                }

                if (l == 2) {
                    --k;
                }

                if (l == 3) {
                    ++k;
                }

                if (l == 4) {
                    --i;
                }

                if (l == 5) {
                    ++i;
                }
            }
            // success
            if (arg.getStationNbt().contains("Items")) {

                CrateBlockEntity c = (CrateBlockEntity) level.getBlockEntity(i, j, k);
                NbtList items = arg.getStationNbt().getList("Items");

                c.contents = new ItemStack[c.size()];

                for(int m = 0; m < items.size(); ++m) {
                    NbtCompound tag = (NbtCompound)items.get(m);
                    int var5 = tag.getByte("Slot") & 255;
                    if (var5 >= 0 && var5 < c.contents.length) {
                        c.contents[var5] = new ItemStack(tag);
                    }
                }
            }

            if (arg.getStationNbt().contains("Name")) {
                CrateBlockEntity c = (CrateBlockEntity) level.getBlockEntity(i, j, k);
                c.setContainerName(arg.getStationNbt().getString("Name"));
            }
        }
        return value;
    }

    @Override
    public String[] getTooltip(ItemStack stack, String originalTooltip) {

        if (stack.getStationNbt().contains("Name")) {
            return new String[]{stack.getStationNbt().getString("Name")};
        }

        return new String[]{originalTooltip};
    }
}
