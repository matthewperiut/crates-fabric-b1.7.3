package com.matthewperiut.crate.block;

import com.jcraft.jorbis.Block;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Blocks {
    @Entrypoint.Namespace
    static final Namespace MOD_ID = Null.get();

    public static BlockBase Crate;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        Crate = (new CrateBlock(MOD_ID.id("crate")).setHardness(2.5f)
                .setTranslationKey(MOD_ID, "crate")
                .setSounds(BlockBase.WOOD_SOUNDS)
                .disableNotifyOnMetaDataChange());
    }
}
