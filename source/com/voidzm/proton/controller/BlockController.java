/*******
>>> BlockController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import com.voidzm.proton.block.BlockArcticSand;
import com.voidzm.proton.block.BlockProton;
import com.voidzm.proton.block.BlockProtonStone;
import com.voidzm.proton.registry.ProtonStoneRegistry;
import com.voidzm.proton.util.ProtonConfiguration;

public class BlockController {

	private static ProtonConfiguration config;

	public static BlockProton adobe;
	public static BlockProton arcticSand;

	public static BlockProton fracturedAdobe;
	public static BlockProton chiseledAdobe;

	public static BlockProton smoothArcticSandstone;
	public static BlockProton chiseledArcticSandstone;

	public static void init(ProtonConfiguration cfg) {
		if(config != null) {
			throw new RuntimeException("Block controller already loaded!");
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for block controller!");
		}
		config = cfg;
		createBlocks();
	}

	private static void createBlocks() {
		createWorldGenBlocks();
		createStone();
	}

	private static void createWorldGenBlocks() {
		adobe = new BlockProtonStone(config.adobeID, "adobe", "Adobe", "proton:adobe").setAlternateDrop(config.fracturedadobeID).register();
		arcticSand = new BlockArcticSand(config.arcticsandID).register();
	}

	private static void createStone() {
		ProtonStoneRegistry.init(config);
		ProtonStoneRegistry.registerStone("Adobe Bricks", "Adobe Bricks Slab", "Adobe Brick Stairs", "adobebricks");
		ProtonStoneRegistry.registerStone("Arctic Sandstone", "Arctic Sandstone Slab", "Arctic Sandstone Stairs", "arcticsandstone_top", "arcticsandstone_side", "arcticsandstone_bottom");
		ProtonStoneRegistry.registrationDone();

		fracturedAdobe = new BlockProtonStone(config.fracturedadobeID, "fracturedadobe", "Fractured Adobe", "proton:fracturedadobe").register();
		chiseledAdobe = new BlockProtonStone(config.chiseledadobeID, "chiseledadobe", "Chiseled Adobe", "proton:chiseledadobe").register();

		smoothArcticSandstone = new BlockProtonStone(config.smootharcticsandstoneID, "smootharcticsandstone", "Smooth Arctic Sandstone", "proton:arcticsandstone_top", "proton:arcticsandstone_smooth", "proton:arcticsandstone_top").register();
		chiseledArcticSandstone = new BlockProtonStone(config.chiseledarcticsandstoneID, "chiseledarcticsandstone", "Chiseled Arctic Sandstone", "proton:arcticsandstone_top", "proton:arcticsandstone_chiseled", "proton:arcticsandstone_top").register();
	}

}
