/*******
>>> WorldGenVaporStreams.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.proton.controller.BlockController;

public class WorldGenVaporStreams extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int xPos = x + random.nextInt(16) + 8;
		int zPos = z + random.nextInt(16) + 8;
		for(int iy = 254; iy > 64; iy--) {
			int bID = world.getBlockId(xPos, iy, zPos);
			if(bID == 0) {
				if(iy >= 253) {
					world.setBlock(xPos + 1, iy, zPos, Block.bedrock.blockID);
					world.setBlock(xPos - 1, iy, zPos, Block.bedrock.blockID);
					world.setBlock(xPos, iy, zPos + 1, Block.bedrock.blockID);
					world.setBlock(xPos, iy, zPos - 1, Block.bedrock.blockID);
					world.setBlock(xPos, iy, zPos, BlockController.vapor.blockID, 0, 2);
					world.scheduledUpdatesAreImmediate = true;
					BlockController.vapor.updateTick(world, xPos, iy, zPos, random);
					world.scheduledUpdatesAreImmediate = false;
					break;
				}
				if(world.getBlockId(xPos, iy + 1, zPos) == Block.stone.blockID) {
					if((world.getBlockId(xPos + 1, iy + 1, zPos) == Block.stone.blockID && world.getBlockId(xPos - 1, iy + 1, zPos) == Block.stone.blockID) && (world.getBlockId(xPos, iy + 1, zPos + 1) == Block.stone.blockID && world.getBlockId(xPos, iy + 1, zPos - 1) == Block.stone.blockID)) {
						world.setBlock(xPos, iy + 1, zPos, BlockController.vapor.blockID, 0, 2);
						world.scheduledUpdatesAreImmediate = true;
						BlockController.vapor.updateTick(world, xPos, iy + 1, zPos, random);
						world.scheduledUpdatesAreImmediate = false;
						break;
					}
				}
			}
		}
		return false;
	}

}
