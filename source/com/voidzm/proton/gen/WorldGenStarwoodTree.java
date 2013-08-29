/*******
>>> WorldGenStarwoodTree.java <<<
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

public class WorldGenStarwoodTree extends WorldGenProtonTree {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		return this.generate(world, rand, x, y, z, true);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z, boolean biomeCheck) {
		int logID = ProtonWoodRegistry.fetchLogIDForName(TreeType.STARWOOD.treeName);
		int logMeta = ProtonWoodRegistry.fetchLogMetaForName(TreeType.STARWOOD.treeName);
		int leavesID = ProtonLeavesRegistry.fetchLeavesIDForName(TreeType.STARWOOD.treeName);
		int leavesMeta = ProtonLeavesRegistry.fetchLeavesMetaForName(TreeType.STARWOOD.treeName);

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
		boolean doOddLayers = false;
		if(rand.nextBoolean()) doOddLayers = true;
		int layers = doOddLayers ? (rand.nextBoolean() ? 3 : 5) : (rand.nextBoolean() ? 4 : 6);
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				for(int iy = 0; iy < layers + 3; iy++) {
					int blockID = world.getBlockId(x + ix, y + iy + exposedTrunkHeight, z + iz);
					if(blockID != 0 && !Block.blocksList[blockID].isLeaves(world, x + ix, y + iy + exposedTrunkHeight, z + iz)) {
						return false; // Something is in the way of the leaves;
					}
				}
			}
		}
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				for(int iy = 0; iy < exposedTrunkHeight + layers + 3; iy++) {
					if(iy < exposedTrunkHeight) {
						if(ix == 0 && iz == 0) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
						}
					}
					else {
						int layer = iy - exposedTrunkHeight;
						if(layer < layers) {
							if(layer % 2 == 0) {
								if(doOddLayers) {
									if(Math.abs(ix) + Math.abs(iz) <= 1) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
									}
									if(ix == 0 && iz == 0) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
									}
								}
								else {
									if(Math.abs(ix) != 2 || Math.abs(iz) != 2) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
									}
									if(ix == 0 && iz == 0) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
									}
								}
							}
							else {
								if(!doOddLayers) {
									if(Math.abs(ix) + Math.abs(iz) <= 1) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
									}
									if(ix == 0 && iz == 0) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
									}
								}
								else {
									if(Math.abs(ix) != 2 || Math.abs(iz) != 2) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
									}
									if(ix == 0 && iz == 0) {
										this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
									}
								}
							}
						}
						else if(layer == layers) {
							if(ix == 0 && iz == 0) {
								this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
							}
						}
						else if(layer == layers + 1) {
							if(Math.abs(ix) + Math.abs(iz) <= 1) {
								this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
							}
							if(ix == 0 && iz == 0) {
								this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
							}
						}
						else {
							if(ix == 0 && iz == 0) {
								this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
							}
						}
					}
				}
			}
		}
		return true;
	}

}
