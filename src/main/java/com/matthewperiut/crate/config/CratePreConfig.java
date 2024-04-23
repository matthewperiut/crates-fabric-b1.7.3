package com.matthewperiut.crate.config;

import blue.endless.jankson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.api.gcapi.api.PreConfigSavedListener;
import net.minecraft.client.Minecraft;

public class CratePreConfig implements PreConfigSavedListener {
    @Override
    public void onPreConfigSaved(int i, JsonObject jsonObject, JsonObject jsonObject1) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            Minecraft mc = (Minecraft)FabricLoader.getInstance().getGameInstance();
            if (mc != null) {
                mc.textureManager.reloadTexturesFromTexturePack();
            }
        }
    }
}
