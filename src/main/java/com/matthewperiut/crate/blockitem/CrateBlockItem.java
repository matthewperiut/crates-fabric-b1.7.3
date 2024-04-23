package com.matthewperiut.crate.blockitem;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateBlockItem;

public class CrateBlockItem extends TemplateBlockItem implements CustomTooltipProvider {
    public CrateBlockItem(int i) {
        super(i);
        setMaxStackSize(1);
    }

    @Override
    public boolean useOnTile(ItemInstance arg, PlayerBase arg2, Level level, int i, int j, int k, int l) {
        boolean value = super.useOnTile(arg, arg2, level, i, j, k, l);;
        if (value) {
            if (level.getTileId(i, j, k) == BlockBase.SNOW.id) {
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
            if (arg.getStationNbt().containsKey("Items")) {

                CrateBlockEntity c = (CrateBlockEntity) level.getTileEntity(i, j, k);
                ListTag items = arg.getStationNbt().getListTag("Items");

                c.contents = new ItemInstance[c.getInventorySize()];

                for(int m = 0; m < items.size(); ++m) {
                    CompoundTag tag = (CompoundTag)items.get(m);
                    int var5 = tag.getByte("Slot") & 255;
                    if (var5 >= 0 && var5 < c.contents.length) {
                        c.contents[var5] = new ItemInstance(tag);
                    }
                }
            }

            if (arg.getStationNbt().containsKey("Name")) {
                CrateBlockEntity c = (CrateBlockEntity) level.getTileEntity(i, j, k);
                c.setContainerName(arg.getStationNbt().getString("Name"));
            }
        }
        return value;
    }

    @Override
    public String[] getTooltip(ItemInstance stack, String originalTooltip) {

        if (stack.getStationNbt().containsKey("Name")) {
            return new String[]{stack.getStationNbt().getString("Name")};
        }

        return new String[]{originalTooltip};
    }
}
