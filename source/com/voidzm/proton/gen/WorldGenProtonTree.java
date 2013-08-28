/*******
>>> ProtonTreeGenerator.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenProtonTree extends WorldGenerator {

	public abstract boolean generate(World world, Random rand, int x, int y, int z);
	
	public abstract boolean generate(World world, Random rand, int x, int y, int z, boolean biomeCheck);
	
}
