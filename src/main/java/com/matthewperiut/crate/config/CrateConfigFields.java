package com.matthewperiut.crate.config;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;

public class CrateConfigFields {
    @ConfigName("More b1.7.3-like texture")
    @Comment("May require rejoining world!")
    public Boolean altTexture = false;
}