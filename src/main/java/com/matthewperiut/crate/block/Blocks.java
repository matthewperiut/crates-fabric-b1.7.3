package com.matthewperiut.crate.block;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Blocks {
    @Entrypoint.Namespace
    static final Namespace MOD_ID = Null.get();

    public static Block Crate;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        Crate = (new CrateBlock(MOD_ID.id("crate")).setHardness(2.5f)
                .setTranslationKey(MOD_ID, "crate")
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .ignoreMetaUpdates());
    }
}
