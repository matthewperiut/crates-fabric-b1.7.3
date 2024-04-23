package com.matthewperiut.crate.gui;

import com.matthewperiut.crate.blockentity.CrateBlockEntity;
import com.matthewperiut.crate.inventory.ContainerCrate;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.lwjgl.opengl.GL11;

public class GuiCrate extends ContainerBase {
    private final String name;

    public GuiCrate(PlayerInventory inv, CrateBlockEntity crate) {
        super(new ContainerCrate(inv, crate));
        name = crate.getContainerName();
        this.containerWidth = 176;
        this.containerHeight = 222;
    }

    @Override
    protected void renderForeground() {
        textManager.drawText(name, 53, 14, 0x121212);
        textManager.drawText("Inventory", 8, (height - 140), 0x404040);
    }

    @Override
    protected void renderContainerBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/crate/stationapi/textures/gui/crategui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int var3 = (this.width - this.containerWidth) / 2;
        int var4 = (this.height - this.containerHeight) / 2;
        this.blit(var3, var4, 0, 0, this.containerWidth, 222);
    }
}
