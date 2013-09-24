/*******
>>> CraftingController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import net.minecraft.item.Item;
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
		GameRegistry.addSmelting(BlockController.brokenStarstone.blockID, new ItemStack(BlockController.starstone), 0.1F);
		GameRegistry.addSmelting(BlockController.crackedAcrana.blockID, new ItemStack(BlockController.acrana), 0.1F);
		GameRegistry.addSmelting(Item.snowball.itemID, new ItemStack(ItemController.snowBrick), 0.1F);
	}

	private static void createBlockRecipes() {
		GameRegistry.addRecipe(new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Adobe Bricks"), 4, ProtonStoneRegistry.fetchStoneMetaForName("Adobe Bricks")), new Object[] {"AA", "AA", 'A', BlockController.adobe});
		GameRegistry.addRecipe(new ItemStack(BlockController.chiseledAdobe), new Object[] {"A", "A", 'A', new ItemStack(ProtonStoneRegistry.fetchSlabIDForName("Adobe Bricks"), 1, ProtonStoneRegistry.fetchSlabMetaForName("Adobe Bricks"))});

		GameRegistry.addRecipe(new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Arctic Sandstone"), 4, ProtonStoneRegistry.fetchStoneMetaForName("Arctic Sandstone")), new Object[] {"AA", "AA", 'A', BlockController.arcticSand});
		GameRegistry.addRecipe(new ItemStack(BlockController.smoothArcticSandstone, 4, 0), new Object[] {"AA", "AA", 'A', new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Arctic Sandstone"), 1, ProtonStoneRegistry.fetchStoneMetaForName("Arctic Sandstone"))});
		GameRegistry.addRecipe(new ItemStack(BlockController.chiseledArcticSandstone), new Object[] {"A", "A", 'A', new ItemStack(ProtonStoneRegistry.fetchSlabIDForName("Arctic Sandstone"), 1, ProtonStoneRegistry.fetchSlabMetaForName("Arctic Sandstone"))});

		GameRegistry.addRecipe(new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Snow Bricks"), 4, ProtonStoneRegistry.fetchStoneMetaForName("Snow Bricks")), new Object[] {"ss", "ss", 's', ItemController.snowBrick});

		GameRegistry.addRecipe(new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Starstone Bricks"), 4, ProtonStoneRegistry.fetchStoneMetaForName("Starstone Bricks")), new Object[] {"SS", "SS", 'S', BlockController.starstone});
		GameRegistry.addRecipe(new ItemStack(BlockController.chiseledStarstone), new Object[] {"S", "S", 'S', new ItemStack(ProtonStoneRegistry.fetchSlabIDForName("Starstone Bricks"), 1, ProtonStoneRegistry.fetchSlabMetaForName("Starstone Bricks"))});

		GameRegistry.addRecipe(new ItemStack(ProtonStoneRegistry.fetchStoneIDForName("Acrana Bricks"), 4, ProtonStoneRegistry.fetchStoneMetaForName("Acrana Bricks")), new Object[] {"AA", "AA", 'A', BlockController.acrana});
		GameRegistry.addRecipe(new ItemStack(BlockController.chiseledAcrana), new Object[] {"A", "A", 'A', new ItemStack(ProtonStoneRegistry.fetchSlabIDForName("Acrana Bricks"), 1, ProtonStoneRegistry.fetchSlabMetaForName("Acrana Bricks"))});
	}
}
