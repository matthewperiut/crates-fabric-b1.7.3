package com.matthewperiut.crate.recipe;

import com.matthewperiut.crate.block.Blocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import java.util.Objects;

public class RecipeListener {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);
        if (Objects.requireNonNull(type) == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
            CraftingRegistry.addShapedRecipe(new ItemInstance(Blocks.Crate), "LLL", "L L", "LLL", 'L', BlockBase.LOG);
        }
    }
}
