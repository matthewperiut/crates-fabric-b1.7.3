package com.matthewperiut.crate;

import com.matthewperiut.crate.spc.CommandListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Crate implements ModInitializer {

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("spc")) {
            CommandListener.addCrateCommand();
        }
    }
}
