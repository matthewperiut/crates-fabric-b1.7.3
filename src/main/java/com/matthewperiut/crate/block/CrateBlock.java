package com.matthewperiut.crate.block;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import com.matthewperiut.crate.blockitem.CrateBlockItem;
import com.matthewperiut.crate.inventory.ContainerCrate;
import net.minecraft.block.Chest;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
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
    protected TileEntityBase createTileEntity() {
        return new CrateBlockEntity();
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        CrateBlockEntity crate = (CrateBlockEntity)level.getTileEntity(x, y, z);
        ItemInstance modifiedItem = new ItemInstance(Blocks.Crate, 1);
        ListTag listTag = new ListTag();

        for(int i = 0; i < crate.getInventorySize(); ++i) {
            if (crate.contents[i] != null) {
                CompoundTag var4 = new CompoundTag();
                var4.put("Slot", (byte)i);
                crate.contents[i].toTag(var4);
                listTag.add(var4);
            }
        }

        if (!crate.getContainerName().equals("Crate")) {
            modifiedItem.getStationNbt().put("Name", crate.getContainerName());
        }

        modifiedItem.getStationNbt().put("Items", listTag);
        drop(level, x, y, z, modifiedItem);
    }

    @Override
    public int getDropCount(Random random) {
        return 0;
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase entityplayer) {
        TileEntityBase t = level.getTileEntity(x, y, z);
        if (t instanceof CrateBlockEntity crate) {
            GuiHelper.openGUI(entityplayer, of(MOD_ID, "crate"), crate, new ContainerCrate(entityplayer.inventory, crate));
        }
        return true;
    }
}
