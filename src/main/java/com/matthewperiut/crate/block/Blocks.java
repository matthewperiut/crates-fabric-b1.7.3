package com.matthewperiut.crate.block;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Blocks {

    public static Block CrateBlock;

    //public static final Namespace NAMESPACE = Namespace.resolve();
    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        CrateBlock = new CrateBlock(NAMESPACE.id("crate")).setHardness(2.5f)
                .setTranslationKey(NAMESPACE, "crate")
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .ignoreMetaUpdates();
    }
}
