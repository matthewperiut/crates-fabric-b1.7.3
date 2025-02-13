package com.matthewperiut.crate.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.api.PreConfigSavedListener;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;

@EventListener
public class CratePreConfig implements PreConfigSavedListener {

    @Override
    public void onPreConfigSaved(int source, GlassYamlFile oldValues, GlassYamlFile newValues) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            Minecraft mc = (Minecraft)FabricLoader.getInstance().getGameInstance();
            if (mc != null) {
                mc.textureManager.reload();
            }
        }
    }
}
