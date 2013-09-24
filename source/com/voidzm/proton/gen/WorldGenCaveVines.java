/*******
>>> WorldGenCrevasseVines.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCaveVines extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		boolean inTargetAir = false;
		for(int iy = y; iy < 256; iy++) {
			if(world.isAirBlock(x, iy, z)) {
				inTargetAir = true;
				continue;
			}
			if(inTargetAir && !world.isAirBlock(x, iy, z)) {
				for(int io = 2; io < 6; io++) {
					if(Block.vine.canPlaceBlockOnSide(world, x, iy - 1, z, io)) {
						world.setBlock(x, iy - 1, z, Block.vine.blockID, 1 << Direction.facingToDirection[Facing.oppositeSide[io]], 2);
						int length = random.nextInt(8) + 3;
						for(int il = 2; il < length; il++) {
							if(!world.isAirBlock(x, iy - il, z)) break;
							world.setBlock(x, iy - il, z, Block.vine.blockID, 1 << Direction.facingToDirection[Facing.oppositeSide[io]], 2);
						}
						break;
					}
				}
				break;
			}
		}
		return true;
	}

}
