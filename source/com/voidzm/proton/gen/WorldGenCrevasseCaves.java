/*******
>>> WorldGenCrevasseCaves.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenCaves;

public class WorldGenCrevasseCaves extends MapGenCaves {

	@Override
	protected void generateCaveNode(long seed, int i, int j, byte[] abyte0, double d, double d1, double d2, float f, float f1, float f2, int k, int i1, double d3) {
		double d4 = i * 16 + 8;
		double d5 = j * 16 + 8;
		float f3 = 0.0F;
		float f4 = 0.0F;
		Random random = new Random(seed);
		if(i1 <= 0) {
			int j1 = this.range * 16 - 16;
			i1 = j1 - random.nextInt(j1 / 4);
		}
		boolean flag = false;
		if(k == -1) {
			k = i1 / 2;
			flag = true;
		}
		int k1 = random.nextInt(i1 / 2) + i1 / 4;
		boolean flag1 = random.nextInt(6) == 0;
		for(; k < i1; ++k) {
			double d6 = 1.5D + MathHelper.sin(k * (float)Math.PI / i1) * f * 1.0F;
			double d7 = d6 * d3;
			float f5 = MathHelper.cos(f2);
			float f6 = MathHelper.sin(f2);
			d += MathHelper.cos(f1) * f5;
			d1 += f6;
			d2 += MathHelper.sin(f1) * f5;
			if(flag1) {
				f2 *= 0.92F;
			}
			else {
				f2 *= 0.7F;
			}
			f2 += f4 * 0.1F;
			f1 += f3 * 0.1F;
			f4 *= 0.9F;
			f3 *= 0.75F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
			if(!flag && k == k1 && f > 1.0F && i1 > 0) {
				this.generateCaveNode(random.nextLong(), i, j, abyte0, d, d1, d2, random.nextFloat() * 1.5F + 0.5F, f1 - ((float)Math.PI / 2F), f2 / 3.0F, k, i1, 1.0D);
				this.generateCaveNode(random.nextLong(), i, j, abyte0, d, d1, d2, random.nextFloat() * 1.5F + 0.5F, f1 + ((float)Math.PI / 2F), f2 / 3.0F, k, i1, 1.0D);
				return;
			}
			if(flag || random.nextInt(4) != 0) {
				double d8 = d - d4;
				double d9 = d2 - d5;
				double d10 = i1 - k;
				double d11 = f + 2.0F + 16.0F;
				if(d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
					return;
				}
				if(d >= d4 - 16.0D - d6 * 2.0D && d2 >= d5 - 16.0D - d6 * 2.0D && d <= d4 + 16.0D + d6 * 2.0D && d2 <= d5 + 16.0D + d6 * 2.0D) {
					int l1 = MathHelper.floor_double(d - d6) - i * 16 - 1;
					int i2 = MathHelper.floor_double(d + d6) - i * 16 + 1;
					int j2 = MathHelper.floor_double(d1 - d7) - 1;
					int k2 = MathHelper.floor_double(d1 + d7) + 1;
					int l2 = MathHelper.floor_double(d2 - d6) - j * 16 - 1;
					int i3 = MathHelper.floor_double(d2 + d6) - j * 16 + 1;
					if(l1 < 0) {
						l1 = 0;
					}
					if(i2 > 16) {
						i2 = 16;
					}
					if(j2 < 1) {
						j2 = 1;
					}
					if(k2 > 248) {
						k2 = 248;
					}
					if(l2 < 0) {
						l2 = 0;
					}
					if(i3 > 16) {
						i3 = 16;
					}
					boolean flag2 = false;
					int j3;
					int k3;
					for(j3 = l1; !flag2 && j3 < i2; ++j3) {
						for(int l3 = l2; !flag2 && l3 < i3; ++l3) {
							for(int i4 = k2 + 1; !flag2 && i4 >= j2 - 1; --i4) {
								int j4 = (j3 * 16 + l3) * 256 + i4;
								if(i4 >= 0 && i4 < 128) {
									if(abyte0[j4] == Block.waterMoving.blockID || abyte0[j4] == Block.waterStill.blockID) {
										flag2 = true;
									}
									if(i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1) {
										i4 = j2;
									}
								}
							}
						}
					}
					if(!flag2) {
						for(j3 = l1; j3 < i2; ++j3) {
							double d12 = (j3 + i * 16 + 0.5D - d) / d6;
							for(k3 = l2; k3 < i3; ++k3) {
								double d13 = (k3 + j * 16 + 0.5D - d2) / d6;
								int j4 = (j3 * 16 + k3) * 256 + k2;
								boolean flag3 = false;
								if(d12 * d12 + d13 * d13 < 1.0D) {
									for(int k4 = k2 - 1; k4 >= j2; --k4) {
										double d14 = (k4 + 0.5D - d1) / d7;

										if(d14 > -0.7D && d12 * d12 + d14 * d14 + d13 * d13 < 1.0D) {
											byte b0 = abyte0[j4];

											if(b0 == Block.grass.blockID) {
												flag3 = true;
											}

											if(b0 == Block.stone.blockID || b0 == Block.dirt.blockID || b0 == Block.grass.blockID) {
												if(k4 < 10) {
													abyte0[j4] = (byte)Block.lavaMoving.blockID;
												}
												else {
													abyte0[j4] = 0;

													if(flag3 && abyte0[j4 - 1] == Block.dirt.blockID) {
														abyte0[j4 - 1] = this.worldObj.getBiomeGenForCoords(j3 + i * 16, k3 + j * 16).topBlock;
													}
												}
											}
										}

										--j4;
									}
								}
							}
						}

						if(flag) {
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void recursiveGenerate(World par1World, int offsetChunkX, int offsetChunkZ, int chunkX, int chunkZ, byte[] par6ArrayOfByte) {
		int iterations = this.rand.nextInt(this.rand.nextInt(20) + 1);
		if(this.rand.nextInt(5) != 0) { // This is the frequency that the generator tries to spawn a cave system. 20%.
			iterations = 0;
		}
		for(int i = 0; i < iterations; ++i) {
			double cavePosX = offsetChunkX * 16 + this.rand.nextInt(16);
			double cavePosY = this.rand.nextInt(256);
			double cavePosZ = offsetChunkZ * 16 + this.rand.nextInt(16);
			int smallBranches = 1;
			if(this.rand.nextInt(3) == 0) { // Frequency of making this a large node. 25%.
				this.generateLargeCaveNode(this.rand.nextLong(), chunkX, chunkZ, par6ArrayOfByte, cavePosX, cavePosY, cavePosZ);
				smallBranches += this.rand.nextInt(5); // Number of additional small branches to connect to this one.
			}
			for(int j = 0; j < smallBranches; ++j) {
				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F; // Random angle in radians between 0 and 2*pi.
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F; // Random float between -0.125 and 0.125.
				float f2 = this.rand.nextFloat() * 3.0F + this.rand.nextFloat(); // Weighted float (originally) between 0.0 and 3.0.
				if(this.rand.nextInt(5) == 0) {
					f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F; // Adjusts the weighted float upward by a random factor between 1.0 and 7.0.
				}
				this.generateCaveNode(this.rand.nextLong(), chunkX, chunkZ, par6ArrayOfByte, cavePosX, cavePosY, cavePosZ, f2, f, f1, 0, 0, 1.0D);
			}
		}
	}

}
