/*******
>>> BiomeController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

import com.voidzm.proton.biome.BiomeProperties;
import com.voidzm.proton.biome.BiomeProton;
import com.voidzm.proton.util.ProtonConfiguration;
import com.voidzm.proton.util.Startup;

import cpw.mods.fml.common.registry.GameRegistry;

public class BiomeController {

	private static ProtonConfiguration config;

	public static BiomeProton extremeForest;
	public static BiomeProton insanityHeights;
	public static BiomeProton grassySummits;
	public static BiomeProton frozenForest;
	public static BiomeProton savanna;
	public static BiomeProton sandyPeaks;
	public static BiomeProton alpine;
	public static BiomeProton starwoodForest;
	public static BiomeProton rainforest;
	public static BiomeProton temperateHills;
	public static BiomeProton dustyHighlands;
	public static BiomeProton canyon;
	public static BiomeProton mossyPools;
	public static BiomeProton shrubland;
	public static BiomeProton arcticDesert;

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
	}

	public static void init(ProtonConfiguration cfg) {
		init(cfg, true);
	}

	private static void createBiomes() {
		extremeForest = new BiomeProton(config.extremeforestID, BiomeProperties.EXTREMEFOREST, "Extreme Forest");
		insanityHeights = new BiomeProton(config.insanityheightsID, BiomeProperties.INSANITYHEIGHTS, "Insanity Heights");
		grassySummits = new BiomeProton(config.grassysummitsID, BiomeProperties.GRASSYSUMMITS, "Grassy Summits");
		frozenForest = new BiomeProton(config.frozenforestID, BiomeProperties.FROZENFOREST, "Frozen Forest");
		savanna = new BiomeProton(config.savannaID, BiomeProperties.SAVANNA, "Savanna").setSkyColor(0x9DB1CC).disableLakes();
		sandyPeaks = new BiomeProton(config.sandypeaksID, BiomeProperties.SANDYPEAKS, "Sandy Peaks").setDesert();
		alpine = new BiomeProton(config.alpineID, BiomeProperties.ALPINE, "Alpine").setSkyColor(0x6A95CC).setSnowy();
		starwoodForest = new BiomeProton(config.starwoodforestID, BiomeProperties.STARWOODFOREST, "Starwood Forest").setSkyColor(0xCADBD8);
		rainforest = new BiomeProton(config.rainforestID, BiomeProperties.RAINFOREST, "Rainforest").setSkyColor(0xB7ADC4);
		temperateHills = new BiomeProton(config.temperatehillsID, BiomeProperties.TEMPERATEHILLS, "Temperate Hills");
		dustyHighlands = new BiomeProton(config.dustyhighlandsID, BiomeProperties.DUSTYHIGHLANDS, "Dusty Highlands").disableLakes().setSkyColor(0xD3DCE3);
		canyon = new BiomeProton(config.canyonID, BiomeProperties.CANYON, "Canyon").setArid().setTopBlock((byte)BlockController.adobe.blockID).setFillerBlock((byte)BlockController.adobe.blockID).setSkyColor(0xDBC3AF);
		mossyPools = new BiomeProton(config.mossypoolsID, BiomeProperties.MOSSYPOOLS, "Mossy Pools");
		shrubland = new BiomeProton(config.shrublandID, BiomeProperties.SHRUBLAND, "Shrubland").setNoAnimals().setSkyColor(0xB9D6F3);
		arcticDesert = new BiomeProton(config.arcticdesertID, BiomeProperties.ARCTICDESERT, "Arctic Desert").setArid().setTopBlock((byte)BlockController.arcticSand.blockID).setFillerBlock((byte)BlockController.arcticSand.blockID).setSkyColor(0xEEEEEE);
	}

	private static void registerBiomes() {
		addStandardBiome(arcticDesert);
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
