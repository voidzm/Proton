/*******
>>> WorldGenWitheredStumps.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.proton.controller.BiomeController;
import com.voidzm.proton.registry.ProtonWoodRegistry;

public class WorldGenWitheredStumps extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if(world.getBiomeGenForCoords(x, z).biomeID != BiomeController.canyon.biomeID) {
			return false;
		}
		if(world.isAirBlock(x, y, z)) {
			world.setBlock(x, y, z, ProtonWoodRegistry.fetchLogIDForName("Withered"));
			int metadataFlag = 0;
			switch(rand.nextInt(3)) {
				case 0:
					break;
				case 1:
					metadataFlag = 4;
					break;
				case 2:
					metadataFlag = 8;
					break;
			}
			world.setBlockMetadataWithNotify(x, y, z, ProtonWoodRegistry.fetchLogMetaForName("Withered") | metadataFlag, 2);
		}
		return true;
	}
}
