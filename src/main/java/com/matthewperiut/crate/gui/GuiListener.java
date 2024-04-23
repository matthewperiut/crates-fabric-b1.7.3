package com.matthewperiut.crate.gui;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GuiListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(Identifier.of(MOD_ID, "crate"), BiTuple.of(this::openCrate, CrateBlockEntity::new));
    }

    @Environment(EnvType.CLIENT)
    public ContainerBase openCrate(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiCrate(player.inventory, (CrateBlockEntity) inventoryBase);
    }
}
