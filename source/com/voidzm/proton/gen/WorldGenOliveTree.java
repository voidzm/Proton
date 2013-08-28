/*******
>>> WorldGenOliveTree.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import com.voidzm.proton.controller.BiomeController;
import com.voidzm.proton.controller.EnvironmentController.TreeType;
import com.voidzm.proton.registry.ProtonLeavesRegistry;
import com.voidzm.proton.registry.ProtonWoodRegistry;

public class WorldGenOliveTree extends WorldGenProtonTree {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		return this.generate(world, rand, x, y, z, true);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z, boolean biomeCheck) {
		int oliveLogID = ProtonWoodRegistry.fetchLogIDForName(TreeType.OLIVE.treeName);
		int oliveLogMeta = ProtonWoodRegistry.fetchLogMetaForName(TreeType.OLIVE.treeName);
		int oliveLeavesID = ProtonLeavesRegistry.fetchLeavesIDForName(TreeType.OLIVE.treeName);
		int oliveLeavesMeta = ProtonLeavesRegistry.fetchLeavesMetaForName(TreeType.OLIVE.treeName);

		int rootBlockID = world.getBlockId(x, y - 1, z);
		if(rootBlockID != Block.grass.blockID && rootBlockID != Block.dirt.blockID) { // The root block is wrong
			return false;
		}
		if(biomeCheck && world.getBiomeGenForCoords(x, z).biomeID != BiomeController.savanna.biomeID) { // The biome is wrong
			return false;
		}
		int exposedTrunkHeight = rand.nextInt(3) + 1;
		for(int iy = 0; iy < exposedTrunkHeight; iy++) {
			if(iy == 0) continue;
			if(world.getBlockId(x, y + iy, z) != 0 && !Block.blocksList[world.getBlockId(x, y + iy, z)].isLeaves(world, x, y + iy, z)) {
				return false; // Something is in the way of the lower trunk
			}
		}
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				for(int iy = 0; iy < 4; iy++) {
					int blockID = world.getBlockId(x + ix, y + iy + exposedTrunkHeight, z + iz);
					if(blockID != 0 && !Block.blocksList[blockID].isLeaves(world, x + ix, y + iy + exposedTrunkHeight, z + iz)) {
						return false; // Something is in the way of the leaves;
					}
				}
			}
		}
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				for(int iy = 0; iy < exposedTrunkHeight + 4; iy++) {
					if(iy < exposedTrunkHeight) {
						if(ix == 0 && iz == 0) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, oliveLogID, oliveLogMeta);
						}
					}
					else if(iy == exposedTrunkHeight || iy == exposedTrunkHeight + 2) {
						if(Math.abs(ix) + Math.abs(iz) <= 1) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, oliveLeavesID, oliveLeavesMeta);
						}
						if(ix == 0 && iz == 0) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, oliveLogID, oliveLogMeta);
						}
					}
					else if(iy == exposedTrunkHeight + 1) {
						if(Math.abs(ix) != 2 || Math.abs(iz) != 2) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, oliveLeavesID, oliveLeavesMeta);
						}
						if(ix == 0 && iz == 0) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, oliveLogID, oliveLogMeta);
						}
					}
					else {
						if(ix == 0 && iz == 0) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, oliveLeavesID, oliveLeavesMeta);
						}
					}
				}
			}
		}
		return true;
	}

}
