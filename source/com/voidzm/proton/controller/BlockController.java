/*******
>>> BlockController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import net.minecraft.block.Block;

import com.voidzm.proton.block.BlockAdumbratedAcrana;
import com.voidzm.proton.block.BlockArcticSand;
import com.voidzm.proton.block.BlockIjolite;
import com.voidzm.proton.block.BlockMoss;
import com.voidzm.proton.block.BlockOverworldCrevassePortal;
import com.voidzm.proton.block.BlockProton;
import com.voidzm.proton.block.BlockProtonStairs;
import com.voidzm.proton.block.BlockProtonStone;
import com.voidzm.proton.block.BlockVapor;
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

	public static BlockProton moss;

	public static BlockProton starstone;
	public static BlockProton brokenStarstone;
	public static BlockProton chiseledStarstone;

	public static BlockProton acrana;
	public static BlockProton crackedAcrana;
	public static BlockProton chiseledAcrana;
	public static BlockProton adumbratedAcrana;

	public static BlockProton ijolite;
	public static Block ijoliteStairs;

	public static BlockProton overworldCrevassePortal;

	public static Block vapor;

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
		ProtonStoneRegistry.registerStone("Snow Bricks", "Snow Bricks Slab", "Snow Brick Stairs", "snowbricks");
		ProtonStoneRegistry.registerStone("Starstone Bricks", "Starstone Bricks Slab", "Starstone Brick Stairs", "starstonebricks");
		ProtonStoneRegistry.registerStone("Acrana Bricks", "Acrana Bricks Slab", "Acrana Brick Stairs", "acranabricks");
		ProtonStoneRegistry.registrationDone();

		fracturedAdobe = new BlockProtonStone(config.fracturedadobeID, "fracturedadobe", "Fractured Adobe", "proton:fracturedadobe").register();
		chiseledAdobe = new BlockProtonStone(config.chiseledadobeID, "chiseledadobe", "Chiseled Adobe", "proton:chiseledadobe").register();

		smoothArcticSandstone = new BlockProtonStone(config.smootharcticsandstoneID, "smootharcticsandstone", "Smooth Arctic Sandstone", "proton:arcticsandstone_top", "proton:arcticsandstone_smooth", "proton:arcticsandstone_top").register();
		chiseledArcticSandstone = new BlockProtonStone(config.chiseledarcticsandstoneID, "chiseledarcticsandstone", "Chiseled Arctic Sandstone", "proton:arcticsandstone_top", "proton:arcticsandstone_chiseled", "proton:arcticsandstone_top").register();

		moss = new BlockMoss(config.mossID).register();

		starstone = new BlockProtonStone(config.starstoneID, "starstone", "Starstone", "proton:starstone").setAlternateDrop(config.brokenstarstoneID).register();
		brokenStarstone = new BlockProtonStone(config.brokenstarstoneID, "brokenstarstone", "Broken Starstone", "proton:brokenstarstone").register();
		chiseledStarstone = new BlockProtonStone(config.chiseledstarstoneID, "chiseledstarstone", "Chiseled Starstone", "proton:chiseledstarstone").register();

		acrana = new BlockProtonStone(config.acranaID, "acrana", "Acrana", "proton:acrana").setAlternateDrop(config.crackedacranaID).register();
		crackedAcrana = new BlockProtonStone(config.crackedacranaID, "crackedacrana", "Cracked Acrana", "proton:crackedacrana").register();
		chiseledAcrana = new BlockProtonStone(config.chiseledacranaID, "chiseledacrana", "Chiseled Acrana", "proton:chiseledacrana").register();
		adumbratedAcrana = new BlockAdumbratedAcrana(config.adumbratedacranaID).register();

		ijolite = new BlockIjolite(config.ijoliteID).register();
		ijoliteStairs = new BlockProtonStairs(config.ijolitestairsID, ijolite, 0, "ijolitestairs", "Ijolite Stairs");
		BlockProton.register(ijoliteStairs, ((BlockProtonStairs)ijoliteStairs).getRegisterData());

		overworldCrevassePortal = new BlockOverworldCrevassePortal(config.overworldcrevasseportalID).register();

		vapor = new BlockVapor(config.vaporID, FluidController.vapor);
		BlockProton.register(vapor, ((BlockVapor)vapor).getRegisterData());
	}

}
