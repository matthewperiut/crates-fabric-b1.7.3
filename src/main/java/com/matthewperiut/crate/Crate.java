package com.matthewperiut.crate;

import com.matthewperiut.crate.config.CrateConfigFields;
import com.matthewperiut.crate.spc.CommandListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class Crate implements ModInitializer {

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("spc")) {
            CommandListener.addCrateCommand();
        }
    }

    @GConfig(value = "config", visibleName = "Crates Config")
    public static CrateConfigFields config = new CrateConfigFields();
}
