package com.matthewperiut.crate.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class CrateConfigFields {

    @ConfigEntry(
            name = "More b1.7.3-like texture",
            description = "May require rejoining world!"
    )
    public Boolean altTexture = false;
}