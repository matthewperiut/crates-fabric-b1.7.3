package com.matthewperiut.crate.gui;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import com.matthewperiut.crate.inventory.ContainerCrate;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiCrate extends HandledScreen {
    private final String name;

    public GuiCrate(PlayerInventory inv, CrateBlockEntity crate) {
        super(new ContainerCrate(inv, crate));
        name = crate.getName();
        this.backgroundWidth = 176;
        this.backgroundHeight = 222;
    }

    @Override
    protected void drawForeground() {
        textRenderer.draw(name, 53, 14, 0x121212);
        this.textRenderer.draw("Inventory", 8, this.backgroundHeight - 122, 4210752);
    }

    @Override
    protected void drawBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/crate/stationapi/textures/gui/crategui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.backgroundWidth) / 2;
        int var4 = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(var3, var4, 0, 0, this.backgroundWidth, 222);
    }
}
