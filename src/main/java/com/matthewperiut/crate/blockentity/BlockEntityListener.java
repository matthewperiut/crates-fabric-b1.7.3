package com.matthewperiut.crate.blockentity;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;

public class BlockEntityListener {
    @EventListener
    public void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(CrateBlockEntity.class, "crate:crate");
    }
}
