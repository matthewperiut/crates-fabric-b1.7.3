package com.matthewperiut.crate.recipe;

import com.matthewperiut.crate.block.Blocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Objects;

public class RecipeListener {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);
        if (Objects.requireNonNull(type) == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
            CraftingRegistry.addShapedRecipe(new ItemStack(Blocks.CrateBlock), "LLL", "L L", "LLL", 'L', TagKey.of(ItemRegistry.KEY, Identifier.of("c:logs")));
        }
    }
}
