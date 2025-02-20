package com.matthewperiut.crate.gui;

import com.matthewperiut.crate.block.Blocks;
import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.Registry;

public class GuiListener {

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        Registry.register(event.registry, Blocks.NAMESPACE.id("crate"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openCrate, CrateBlockEntity::new));
    }

    @Environment(EnvType.CLIENT)
    public Screen openCrate(PlayerEntity player, Inventory inventoryBase) {
        return new GuiCrate(player.inventory, (CrateBlockEntity) inventoryBase);
    }
}
