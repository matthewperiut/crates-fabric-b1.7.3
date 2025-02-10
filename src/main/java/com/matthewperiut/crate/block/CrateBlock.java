package com.matthewperiut.crate.block;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import com.matthewperiut.crate.blockitem.CrateBlockItem;
import com.matthewperiut.crate.inventory.ContainerCrate;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

import static com.matthewperiut.crate.block.Blocks.MOD_ID;
import static net.modificationstation.stationapi.api.util.Identifier.of;

@HasCustomBlockItemFactory(CrateBlockItem.class)
public class CrateBlock extends TemplateBlockWithEntity {
    public CrateBlock(Identifier identifier) {
        super(identifier, Material.WOOD);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new CrateBlockEntity();
    }

    @Override
    public void onBreak(World level, int x, int y, int z) {
        CrateBlockEntity crate = (CrateBlockEntity)level.getBlockEntity(x, y, z);
        ItemStack modifiedItem = new ItemStack(Blocks.Crate, 1);
        NbtList listTag = new NbtList();

        for(int i = 0; i < crate.size(); ++i) {
            if (crate.contents[i] != null) {
                NbtCompound var4 = new NbtCompound();
                var4.putByte("Slot", (byte)i);
                crate.contents[i].writeNbt(var4);
                listTag.add(var4);
            }
        }

        if (!crate.getName().equals("Crate")) {
            modifiedItem.getStationNbt().putString("Name", crate.getName());
        }

        modifiedItem.getStationNbt().put("Items", listTag);
        dropStack(level, x, y, z, modifiedItem);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    public boolean onUse(World level, int x, int y, int z, PlayerEntity entityplayer) {
        BlockEntity t = level.getBlockEntity(x, y, z);
        if (t instanceof CrateBlockEntity crate) {
            GuiHelper.openGUI(entityplayer, of(MOD_ID, "crate"), crate, new ContainerCrate(entityplayer.inventory, crate));
        }
        return true;
    }
}
