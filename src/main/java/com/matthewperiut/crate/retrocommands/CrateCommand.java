package com.matthewperiut.crate.retrocommands;

import com.matthewperiut.crate.blockitem.CrateBlockItem;
import com.matthewperiut.retrocommands.api.Command;
import com.matthewperiut.retrocommands.util.SharedCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class CrateCommand implements Command {
    @Override
    public void command(SharedCommandSource commandSource, String[] parameters) {
        PlayerEntity player = commandSource.getPlayer();
        if (player != null) {
            ItemStack item = player.inventory.main[player.inventory.selectedSlot];
            if (item == null) {
                commandSource.sendFeedback("You must be holding a crate to give a name to");
                return;
            }
            if (!(item.getItem() instanceof CrateBlockItem)) {
                commandSource.sendFeedback("You must be holding a crate to give a name to");
                return;
            }

            String name = "";
            for (int i = 1; i < parameters.length; i++) {
                name += parameters[i] + " ";
            }
            name = name.stripTrailing();
            if (name.length() > 12) {
                commandSource.sendFeedback("Your title cannot be more than 12 characters long");
                return;
            }
            if (name.isEmpty()) {
                commandSource.sendFeedback("Your title must have at least one character");
                return;
            }

            item.getStationNbt().putString("Name", name);
        }
    }

    @Override
    public String name() {
        return "crate";
    }

    @Override
    public void manual(SharedCommandSource commandSource) {
        commandSource.sendFeedback("Usage: /crate {name}");
        commandSource.sendFeedback("Info: Sets the name of a crate");
    }
}
