package com.matthewperiut.crate.blockentity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class CrateBlockEntity extends BlockEntity implements Inventory {
    private String name = "Crate";
    public ItemStack[] contents = new ItemStack[12];

    public int size() {
        return contents.length;
    }

    public ItemStack getStack(int i) {
        return this.contents[i];
    }

    public ItemStack removeStack(int i, int j) {
        if (this.contents[i] != null) {
            ItemStack var3;
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

    public void setStack(int i, ItemStack arg) {
        this.contents[i] = arg;
        if (arg != null && arg.count > this.getMaxCountPerStack()) {
            arg.count = this.getMaxCountPerStack();
        }

        this.markDirty();
    }

    public String getName() {
        return name;
    }

    public void setContainerName(String name) {
        this.name = name;
    }

    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        NbtList var2 = tag.getList("Items");
        this.contents = new ItemStack[this.size()];

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            NbtCompound var4 = (NbtCompound)var2.get(var3);
            int var5 = var4.getByte("Slot") & 255;
            if (var5 >= 0 && var5 < this.contents.length) {
                this.contents[var5] = new ItemStack(var4);
            }
        }

        if (tag.contains("Name")) {
            name = tag.getString("Name");
        }

    }

    public void writeNbt(NbtCompound arg) {
        super.writeNbt(arg);
        NbtList var2 = new NbtList();

        for(int var3 = 0; var3 < this.contents.length; ++var3) {
            if (this.contents[var3] != null) {
                NbtCompound var4 = new NbtCompound();
                var4.putByte("Slot", (byte)var3);
                this.contents[var3].writeNbt(var4);
                var2.add(var4);
            }
        }

        if (!name.equals("Crate")) {
            arg.putString("Name", name);
        }

        arg.put("Items", var2);
    }

    public int getMaxCountPerStack() {
        return 64;
    }

    public boolean canPlayerUse(PlayerEntity arg) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(arg.getSquaredDistance((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) > 64.0);
        }
    }
}
