package com.matthewperiut.crate.texture;

import com.matthewperiut.crate.block.Blocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static net.modificationstation.stationapi.api.util.Identifier.of;

public class TextureListener {
    @Entrypoint.Namespace
    private static final Namespace MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas terrain = Atlases.getTerrain();
        Blocks.Crate.texture = terrain.addTexture(of(MOD_ID, "block/crate")).index;
    }
}
