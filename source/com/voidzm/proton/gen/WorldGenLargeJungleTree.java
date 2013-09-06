/*******
>>> WorldGenLargeJungleTree.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLargeJungleTree extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		WorldGenHugeTrees internalGenerator = new WorldGenHugeTrees(false, 10 + rand.nextInt(20), 3, 3);
		internalGenerator.generate(world, rand, x, y, z);
		return false;
	}

}
