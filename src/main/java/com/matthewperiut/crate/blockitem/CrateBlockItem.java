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
    public static ItemStack crateItem;
    public CrateBlockItem(int i) {
        super(i);
        setMaxCount(1);
    }

    @Override
    public boolean useOnBlock(ItemStack arg, PlayerEntity user, World world, int x, int y, int z, int side) {
        crateItem = arg.copy();
        return super.useOnBlock(arg, user, world, x, y, z, side);
    }

    @Override
    public String[] getTooltip(ItemStack stack, String originalTooltip) {

        if (stack.getStationNbt().contains("Name")) {
            return new String[]{stack.getStationNbt().getString("Name")};
        }

        return new String[]{originalTooltip};
    }
}
