/*******
>>> WorldGenCanyonSurface.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.proton.controller.BiomeController;
import com.voidzm.proton.controller.BlockController;

public class WorldGenCanyonSurface extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if(world.getBiomeGenForCoords(x, z).biomeID != BiomeController.canyon.biomeID) {
			return false;
		}
		int radius = rand.nextInt(7) + 5;
		int type = rand.nextInt(10);
		int newID;
		if(type < 6) newID = Block.dirt.blockID;
		else if(type < 8) newID = Block.gravel.blockID;
		else newID = Block.stone.blockID;
		for(int ix = -radius; ix < radius; ix++) {
			for(int iz = -radius; iz < radius; iz++) {
				int iy = world.getHeightValue(x + ix, z + iz);
				if(world.getBlockId(x + ix, iy - 1, z + iz) == BlockController.adobe.blockID) {
					double randomFactor = Math.sqrt((ix * ix) + (iz * iz));
					randomFactor /= Math.sqrt(2.0D);
					randomFactor /= radius;
					int intFactor = (int)(randomFactor * 130);
					if(rand.nextInt(100) > intFactor) {
						world.setBlock(x + ix, iy - 1, z + iz, newID);
					}
				}
			}
		}
		return true;
	}

}
