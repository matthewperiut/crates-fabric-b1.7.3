package com.matthewperiut.crate;

import com.matthewperiut.crate.config.CrateConfigFields;
import com.matthewperiut.crate.retrocommands.CommandListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class Crate implements ModInitializer {

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("retrocommands")) {
            CommandListener.addCrateCommand();
        }
    }

    @ConfigRoot(value = "config", visibleName = "Crates Config")
    public static CrateConfigFields config = new CrateConfigFields();
}
