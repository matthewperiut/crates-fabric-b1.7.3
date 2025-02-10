package com.matthewperiut.crate.block;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class Blocks {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    public static Block Crate;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        Crate = new CrateBlock(NAMESPACE.id("crate")).setHardness(2.5f)
                .setTranslationKey(NAMESPACE, "crate")
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .ignoreMetaUpdates();
    }
}
