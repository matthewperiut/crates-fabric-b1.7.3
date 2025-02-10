package com.matthewperiut.crate.gui;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class GuiListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("crate"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openCrate, CrateBlockEntity::new));
    }

    @Environment(EnvType.CLIENT)
    public HandledScreen openCrate(PlayerEntity player, Inventory inventoryBase) {
        return new GuiCrate(player.inventory, (CrateBlockEntity) inventoryBase);
    }
}
