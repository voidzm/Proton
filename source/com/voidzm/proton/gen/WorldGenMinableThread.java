/*******
>>> WorldGenMinableThread.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMinableThread extends WorldGenerator {

	private int blockID;
	private int limiting;
	private int xrad, yrad, zrad;

	public WorldGenMinableThread(int genBlock, int limit, int xr, int yr, int zr) {
		super();
		this.blockID = genBlock;
		this.limiting = limit;
		this.xrad = xr;
		this.yrad = yr;
		this.zrad = zr;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		double xf = ((double)limiting / (double)xrad);
		double yf = ((double)limiting / (double)yrad);
		double zf = ((double)limiting / (double)zrad);
		for(int ix = -xrad; ix <= xrad; ix++) {
			for(int iy = -yrad; iy <= yrad; iy++) {
				for(int iz = -zrad; iz <= zrad; iz++) {
					double dx = Math.abs((double)ix) * xf;
					double dy = Math.abs((double)iy) * yf;
					double dz = Math.abs((double)iz) * zf;
					double d = Math.sqrt(Math.pow(dx, 2.0D) + Math.pow(dy, 2.0D) + Math.pow(dz, 2.0D));
					if(d < (limiting - 1.0D)) {
						this.setBlockIfAllowed(world, x + ix, y + iy, z + iz);
					}
					else if(d < (limiting + 3.0D) && random.nextInt(5) == 0) {
						this.setBlockIfAllowed(world, x + ix, y + iy, z + iz);
					}
				}
			}
		}
		return true;
	}

	private void setBlockIfAllowed(World world, int x, int y, int z) {
		if(world.getBlockId(x, y, z) == Block.stone.blockID || world.getBlockId(x, y, z) == Block.dirt.blockID || world.getBlockId(x, y, z) == Block.gravel.blockID) {
			this.setBlock(world, x, y, z, this.blockID);
		}
	}

}
