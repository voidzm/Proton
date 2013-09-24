/*******
>>> WorldGenLargeForestTree.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLargeForestTree extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		WorldGenBigTree internalGenerator = new WorldGenBigTree(false);
		internalGenerator.generate(world, rand, x, y, z);
		return false;
	}

}
