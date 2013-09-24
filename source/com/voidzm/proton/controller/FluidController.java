/*******
>>> FluidController.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.controller;

import net.minecraftforge.fluids.Fluid;

import com.voidzm.proton.fluid.FluidVapor;
import com.voidzm.proton.util.ProtonConfiguration;

public class FluidController {

	private static ProtonConfiguration config;

	public static Fluid vapor;

	public static void init(ProtonConfiguration cfg) {
		if(config != null) {
			throw new RuntimeException("Fluid controller already loaded!");
		}
		if(cfg == null) {
			throw new RuntimeException("Config required for fluid controller!");
		}
		config = cfg;
		createFluids();
	}

	private static void createFluids() {
		vapor = new FluidVapor();
	}

}
