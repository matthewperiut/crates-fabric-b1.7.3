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
    public boolean useOnBlock(ItemStack arg, PlayerEntity user, World world, int x, int y, int z, int side) {
        boolean value = super.useOnBlock(arg, user, world, x, y, z, side);;
        if (value) {
            if (world.getBlockId(x, y, z) == Block.SNOW.id) {
                side = 0;
            } else {
                if (side == 0) {
                    --y;
                }

                if (side == 1) {
                    ++y;
                }

                if (side == 2) {
                    --z;
                }

                if (side == 3) {
                    ++z;
                }

                if (side == 4) {
                    --x;
                }

                if (side == 5) {
                    ++x;
                }
            }
            // success
            if (arg.getStationNbt().contains("Items")) {

                CrateBlockEntity c = (CrateBlockEntity) world.getBlockEntity(x, y, z);
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
                CrateBlockEntity c = (CrateBlockEntity) world.getBlockEntity(x, y, z);
                c.setName(arg.getStationNbt().getString("Name"));
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
