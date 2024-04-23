package com.matthewperiut.crate.blockentity;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

public class CrateBlockEntity extends TileEntityBase implements InventoryBase {
    private String name = "Crate";
    public ItemInstance[] contents = new ItemInstance[12];

    public int getInventorySize() {
        return contents.length;
    }

    public ItemInstance getInventoryItem(int i) {
        return this.contents[i];
    }

    public ItemInstance takeInventoryItem(int i, int j) {
        if (this.contents[i] != null) {
            ItemInstance var3;
            if (this.contents[i].count <= j) {
                var3 = this.contents[i];
                this.contents[i] = null;
                this.markDirty();
                return var3;
            } else {
                var3 = this.contents[i].split(j);
                if (this.contents[i].count == 0) {
                    this.contents[i] = null;
                }

                this.markDirty();
                return var3;
            }
        } else {
            return null;
        }
    }

    public void setInventoryItem(int i, ItemInstance arg) {
        this.contents[i] = arg;
        if (arg != null && arg.count > this.getMaxItemCount()) {
            arg.count = this.getMaxItemCount();
        }

        this.markDirty();
    }

    public String getContainerName() {
        return name;
    }

    public void setContainerName(String name) {
        this.name = name;
    }

    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);
        ListTag var2 = tag.getListTag("Items");
        this.contents = new ItemInstance[this.getInventorySize()];

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            CompoundTag var4 = (CompoundTag)var2.get(var3);
            int var5 = var4.getByte("Slot") & 255;
            if (var5 >= 0 && var5 < this.contents.length) {
                this.contents[var5] = new ItemInstance(var4);
            }
        }

        if (tag.containsKey("Name")) {
            name = tag.getString("Name");
        }

    }

    public void writeIdentifyingData(CompoundTag arg) {
        super.writeIdentifyingData(arg);
        ListTag var2 = new ListTag();

        for(int var3 = 0; var3 < this.contents.length; ++var3) {
            if (this.contents[var3] != null) {
                CompoundTag var4 = new CompoundTag();
                var4.put("Slot", (byte)var3);
                this.contents[var3].toTag(var4);
                var2.add(var4);
            }
        }

        if (!name.equals("Crate")) {
            arg.put("Name", name);
        }

        arg.put("Items", var2);
    }

    public int getMaxItemCount() {
        return 64;
    }

    public boolean canPlayerUse(PlayerBase arg) {
        if (this.level.getTileEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(arg.squaredDistanceTo((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) > 64.0);
        }
    }
}
