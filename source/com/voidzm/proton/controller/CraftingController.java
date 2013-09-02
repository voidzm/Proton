/*******
>>> CraftingController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import com.voidzm.proton.registry.ProtonStoneRegistry;
import com.voidzm.proton.util.ProtonConfiguration;
import com.voidzm.proton.util.Startup;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingController {

	private static ProtonConfiguration config;

	public static void init(ProtonConfiguration cfg) {
		if(config != null) {
			throw new RuntimeException("Crafting controller already loaded!");
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for crafting controller!");
		}
		config = cfg;
		int initialRecipeListSize = CraftingManager.getInstance().getRecipeList().size();
		createRecipes();
		createSmelting();
		int recipesAdded = CraftingManager.getInstance().getRecipeList().size() - initialRecipeListSize;
		Startup.recipesCreated(recipesAdded);
	}

	private static void createRecipes() {
		createBlockRecipes();
	}

	private static void createSmelting() {
		GameRegistry.addSmelting(BlockController.fracturedAdobe.blockID, new ItemStack(BlockController.adobe), 0.1F);
	}

	private static void createBlockRecipes() {
		GameRegistry.addRecipe(new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Adobe Bricks"), 4, 0), new Object[] {"AA", "AA", 'A', BlockController.adobe});
		GameRegistry.addRecipe(new ItemStack(BlockController.chiseledAdobe), new Object[] {"A", "A", 'A', new ItemStack(ProtonStoneRegistry.fetchSlabIDForName("Adobe Bricks"), 1, 0)});
	}

}
