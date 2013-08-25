/*******
>>> Startup.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.util;

import cpw.mods.fml.common.FMLLog;

public class Startup {

	private static int blocksCreated = 0;
	private static int itemsCreated = 0;
	private static int biomesCreated = 0;
	private static int tileEntitiesCreated = 0;
	
	public static void blockCreated() {
		blocksCreated++;
	}
	
	public static void outputBlockStats() {
		FMLLog.info("[Proton] %d blocks added.", blocksCreated);
	}
	
	public static void itemCreated() {
		itemsCreated++;
	}
	
	public static void outputItemStats() {
		FMLLog.info("[Proton] %d items added.", itemsCreated);
	}
	
	public static void outputCraftingStats(int number) {
		FMLLog.info("[Proton] %d recipes added.", number);
	}
	
	public static void biomeCreated() {
		biomesCreated++;
	}
	
	public static void outputBiomeStats() {
		FMLLog.info("[Proton] %d biomes added.", biomesCreated);
	}
	
	public static void outputDimensionStats() {
		FMLLog.info("[Proton] Dimensions configured.");
	}
	
	public static void tileEntityCreated() {
		tileEntitiesCreated++;
	}
	
	public static void outputTileEntityStats() {
		FMLLog.info("[Proton] %d tile entities added.", tileEntitiesCreated);
	}
	
	public static void loadingDone() {
		FMLLog.info("[Proton] Loading complete.");
	}
	
}
