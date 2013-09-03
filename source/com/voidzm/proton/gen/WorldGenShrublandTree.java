/*******
>>> WorldGenShrublandTree.java <<<
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

public class WorldGenShrublandTree extends WorldGenProtonTree {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		return this.generate(world, rand, x, y, z, true);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z, boolean biomeCheck) {
		int logID = ProtonWoodRegistry.fetchLogIDForName(TreeType.SHRUBLAND.treeName);
		int logMeta = ProtonWoodRegistry.fetchLogMetaForName(TreeType.SHRUBLAND.treeName);
		int leavesID = ProtonLeavesRegistry.fetchLeavesIDForName(TreeType.SHRUBLAND.treeName);
		int leavesMeta = ProtonLeavesRegistry.fetchLeavesMetaForName(TreeType.SHRUBLAND.treeName);

		int rootBlockID = world.getBlockId(x, y - 1, z);
		if(rootBlockID != Block.grass.blockID && rootBlockID != Block.dirt.blockID) { // The root block is wrong
			return false;
		}
		if(biomeCheck && world.getBiomeGenForCoords(x, z).biomeID != BiomeController.shrubland.biomeID) { // The biome is wrong
			return false;
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				for(int iy = 0; iy < 2; iy++) {
					if(ix == 0 && iz == 0 && iy == 0) continue;
					int blockID = world.getBlockId(x + ix, y + iy, z + iz);
					if(blockID != 0 && !Block.blocksList[blockID].isLeaves(world, x + ix, y + iy, z + iz)) {
						return false; // Something is in the way
					}
				}
			}
		}
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				for(int iy = 0; iy < 2; iy++) {
					if(iy == 0) {
						if(ix == 0 && iz == 0) {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, logID, logMeta);
						}
						else {
							this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
						}
					}
					else if(Math.abs(ix) != 1 || Math.abs(iz) != 1) {
						this.setBlockAndMetadata(world, x + ix, y + iy, z + iz, leavesID, leavesMeta);
					}
				}
			}
		}
		return true;
	}

}
