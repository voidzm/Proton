/*******
>>> ItemController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import com.voidzm.proton.item.ItemProton;
import com.voidzm.proton.util.ProtonConfiguration;

public class ItemController {

	private static ProtonConfiguration config;

	public static ItemProton snowBrick;

	public static void init(ProtonConfiguration cfg) {
		if(config != null) {
			throw new RuntimeException("Item controller already loaded!");
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for item controller!");
		}
		config = cfg;
		createItems();
	}

	private static void createItems() {
		createMaterials();
	}

	private static void createMaterials() {
		snowBrick = new ItemProton(config.snowbrickID, "proton:snowbrick").setInternalName("snowbrick").setExternalName("Snow Brick").register();
	}

}
