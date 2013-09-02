/*******
>>> EnvironmentController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import com.voidzm.proton.gen.WorldGenMahoganyTree;
import com.voidzm.proton.gen.WorldGenOliveTree;
import com.voidzm.proton.gen.WorldGenStarwoodTree;
import com.voidzm.proton.registry.ProtonLeavesRegistry;
import com.voidzm.proton.registry.ProtonWoodRegistry;
import com.voidzm.proton.util.ProtonConfiguration;

public class EnvironmentController {

	private static ProtonConfiguration config;

	public enum TreeType {
		OLIVE("Olive", "olive"),
		STARWOOD("Starwood", "starwood"),
		MAHOGANY("Mahogany", "mahogany"),
		WITHERED("Withered", "withered");

		public String treeName;
		public String treeTexture;

		private TreeType(String name, String icon) {
			this.treeName = name;
			this.treeTexture = icon;
		}
	}

	public static void init(ProtonConfiguration cfg) {
		if(config != null) {
			throw new RuntimeException("Environment controller already loaded!");
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for environment controller!");
		}
		config = cfg;
		setupWood();
		setupLeaves();
		setupBiomes();
	}

	private static void setupWood() {
		ProtonWoodRegistry.init(config);
		ProtonWoodRegistry.registerLog(TreeType.OLIVE.treeName, TreeType.OLIVE.treeTexture);
		ProtonWoodRegistry.registerLog(TreeType.STARWOOD.treeName, TreeType.STARWOOD.treeTexture);
		ProtonWoodRegistry.registerLog(TreeType.MAHOGANY.treeName, TreeType.MAHOGANY.treeTexture);
		ProtonWoodRegistry.registerLog(TreeType.WITHERED.treeName, TreeType.WITHERED.treeTexture);
		ProtonWoodRegistry.registrationDone();
	}

	private static void setupLeaves() {
		ProtonLeavesRegistry.init(config);
		ProtonLeavesRegistry.registerLeaves(TreeType.OLIVE.treeName, TreeType.OLIVE.treeTexture, true, new WorldGenOliveTree());
		ProtonLeavesRegistry.registerLeaves(TreeType.STARWOOD.treeName, TreeType.STARWOOD.treeTexture, false, new WorldGenStarwoodTree(), 8);
		ProtonLeavesRegistry.registerLeaves(TreeType.MAHOGANY.treeName, TreeType.MAHOGANY.treeTexture, true, new WorldGenMahoganyTree());
		ProtonLeavesRegistry.registrationDone();
	}

	private static void setupBiomes() {
		BiomeController.init(config, false);
	}

}
