package com.matthewperiut.crate.gui;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.network.packet.MessagePacket;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class GuiListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        Registry.register(event.registry, MOD_ID.id("crate"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openCrate, CrateBlockEntity::new));
        //registry.registerValueNoMessage(Identifier.of(MOD_ID, "crate"), BiTuple.of(this::openCrate, CrateBlockEntity::new));
    }

    @Environment(EnvType.CLIENT)
    public Screen openCrate(PlayerEntity player, Inventory inventoryBase) {
        return new GuiCrate(player.inventory, (CrateBlockEntity) inventoryBase);
    }
}
