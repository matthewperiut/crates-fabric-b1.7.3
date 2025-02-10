package com.matthewperiut.crate.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.api.PreConfigSavedListener;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import net.minecraft.client.Minecraft;

public class CratePreConfig implements PreConfigSavedListener {
    @Override
    public void onPreConfigSaved(int i, GlassYamlFile glassYamlFile, GlassYamlFile glassYamlFile1) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            Minecraft mc = (Minecraft)FabricLoader.getInstance().getGameInstance();
            if (mc != null) {
                mc.textureManager.reload();
            }
        }
    }
}
