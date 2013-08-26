/*******
>>> BiomeController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.controller;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

import com.voidzm.proton.biome.BiomeProperties;
import com.voidzm.proton.biome.BiomeProton;
import com.voidzm.proton.util.ProtonConfiguration;
import com.voidzm.proton.util.Startup;

import cpw.mods.fml.common.registry.GameRegistry;

public class BiomeController {

	private static ProtonConfiguration config;
	
	public static BiomeGenBase extremeForest;
	public static BiomeGenBase insanityHeights;
	public static BiomeGenBase grassySummits;
	public static BiomeGenBase frozenForest;
	
	public static void init(ProtonConfiguration cfg, boolean doVanillaBiomes) {
		if(config != null) {
			throw new RuntimeException("Biome controller already loaded!"); 
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for biome controller!");
		}
		config = cfg;
		createBiomes();
		registerBiomes();
		if(!doVanillaBiomes) removeVanillaBiomes();
		Startup.outputBiomeStats();
	}
	
	public static void init(ProtonConfiguration cfg) {
		init(cfg, true);
	}
	
	private static void createBiomes() {
		extremeForest = new BiomeProton(config.extremeforestID, BiomeProperties.EXTREMEFOREST, "Extreme Forest");
		insanityHeights = new BiomeProton(config.insanityheightsID, BiomeProperties.INSANITYHEIGHTS, "Insanity Heights");
		grassySummits = new BiomeProton(config.grassysummitsID, BiomeProperties.GRASSYSUMMITS, "Grassy Summits");
		frozenForest = new BiomeProton(config.frozenforestID, BiomeProperties.FROZENFOREST, "Frozen Forest");
	}
	
	private static void registerBiomes() {
		addStandardBiome(grassySummits);
		addStandardBiome(frozenForest);
	}
	
	private static void removeVanillaBiomes() {
		GameRegistry.removeBiome(BiomeGenBase.forest);
		GameRegistry.removeBiome(BiomeGenBase.extremeHills);
		GameRegistry.removeBiome(BiomeGenBase.desert);
		GameRegistry.removeBiome(BiomeGenBase.icePlains);
		GameRegistry.removeBiome(BiomeGenBase.jungle);
		GameRegistry.removeBiome(BiomeGenBase.plains);
		GameRegistry.removeBiome(BiomeGenBase.swampland);
		GameRegistry.removeBiome(BiomeGenBase.taiga);
		GameRegistry.removeBiome(BiomeGenBase.mushroomIsland);
	}
	
	private static void addStandardBiome(BiomeGenBase biome) {
		GameRegistry.addBiome(biome);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addStrongholdBiome(biome);
		Startup.biomeCreated();
	}
	
	private static void addVillageBiome(BiomeGenBase biome) {
		GameRegistry.addBiome(biome);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addVillageBiome(biome, true);
		BiomeManager.addStrongholdBiome(biome);
		Startup.biomeCreated();
	}
	
}
