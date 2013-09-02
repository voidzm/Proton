/*******
>>> DimensionController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import net.minecraftforge.common.DimensionManager;

import com.voidzm.proton.dimension.WorldProviderProtonSurface;
import com.voidzm.proton.util.ProtonConfiguration;

public class DimensionController {

	private static ProtonConfiguration config;

	public static void init(ProtonConfiguration cfg) {
		if(config != null) {
			throw new RuntimeException("Dimension controller already loaded!");
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for dimension controller!");
		}
		config = cfg;
		if(config.doalternatesurface) swapSurfaceProvider();
		createDimensions();
	}

	private static void swapSurfaceProvider() {
		DimensionManager.unregisterDimension(0);
		DimensionManager.registerProviderType(config.alternatesurfaceproviderID, WorldProviderProtonSurface.class, true);
		DimensionManager.registerDimension(0, config.alternatesurfaceproviderID);
	}

	private static void createDimensions() {
	}

}
