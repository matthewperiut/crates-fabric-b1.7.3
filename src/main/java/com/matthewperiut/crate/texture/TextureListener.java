package com.matthewperiut.crate.texture;

import com.matthewperiut.crate.Crate;
import com.matthewperiut.crate.block.Blocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;

import static net.modificationstation.stationapi.api.util.Identifier.of;

public class TextureListener {

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas terrain = Atlases.getTerrain();
        if (Crate.config.altTexture) {
            Blocks.CrateBlock.textureId = terrain.addTexture(of(Blocks.NAMESPACE, "block/crate_alt")).index;
        }
        else {
            Blocks.CrateBlock.textureId = terrain.addTexture(of(Blocks.NAMESPACE, "block/crate")).index;
        }
    }
}
