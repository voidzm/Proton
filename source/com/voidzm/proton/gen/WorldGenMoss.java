/*******
>>> WorldGenMoss.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.proton.controller.BlockController;

public class WorldGenMoss extends WorldGenerator {

	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z) {
		Block block = null;
		do {
			block = Block.blocksList[par1World.getBlockId(x, y, z)];
			if(block != null && !block.isLeaves(par1World, x, y, z)) {
				break;
			}
			y--;
		} while(y > 0);
		for(int i1 = 0; i1 < 128; ++i1) {
			int j1 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
			int k1 = y + par2Random.nextInt(4) - par2Random.nextInt(4);
			int l1 = z + par2Random.nextInt(8) - par2Random.nextInt(8);
			if(par1World.isAirBlock(j1, k1, l1) && BlockController.moss.canBlockStay(par1World, j1, k1, l1)) {
				par1World.setBlock(j1, k1, l1, BlockController.moss.blockID);
			}
		}
		return true;
	}

}
