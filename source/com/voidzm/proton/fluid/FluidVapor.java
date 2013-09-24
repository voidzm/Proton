/*******
>>> FluidVapor.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidVapor extends Fluid {

	public FluidVapor() {
		super("vapor");
		this.setLuminosity(5);
		this.setDensity(600);
		FluidRegistry.registerFluid(this);
	}

}
