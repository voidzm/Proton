/*******
>>> WorldGenCrevasseCracks.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;

public class WorldGenCrevasseFissures extends MapGenBase {

	private float[] array = new float[1024];

	protected void generateRavine(long l, int i, int j, byte[] abyte0, double d, double d1, double d2, float f, float f1, float f2, int k, int i1, double d3) {
		Random random = new Random(l);
		double d4 = i * 16 + 8;
		double d5 = j * 16 + 8;
		float f3 = 0.0F;
		float f4 = 0.0F;
		if(i1 <= 0) {
			int j1 = this.range * 16 - 16;
			i1 = j1 - random.nextInt(j1 / 4);
		}
		boolean flag = false;
		if(k == -1) {
			k = i1 / 2;
			flag = true;
		}
		float f5 = 1.0F;
		for(int k1 = 0; k1 < 256; ++k1) {
			if(k1 == 0 || random.nextInt(3) == 0) {
				f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
			}
			this.array[k1] = f5 * f5;
		}
		for(; k < i1; ++k) {
			double d6 = 1.5D + MathHelper.sin(k * (float)Math.PI / i1) * f * 1.0F;
			double d7 = d6 * d3;
			d6 *= random.nextFloat() * 0.25D + 0.75D;
			d7 *= random.nextFloat() * 0.25D + 0.75D;
			float f6 = MathHelper.cos(f2);
			float f7 = MathHelper.sin(f2);
			d += MathHelper.cos(f1) * f6;
			d1 += f7;
			d2 += MathHelper.sin(f1) * f6;
			f2 *= 0.7F;
			f2 += f4 * 0.05F;
			f1 += f3 * 0.05F;
			f4 *= 0.8F;
			f3 *= 0.5F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
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
					boolean flag1 = false;
					int j3;
					int k3;
					for(j3 = l1; !flag1 && j3 < i2; ++j3) {
						for(int l3 = l2; !flag1 && l3 < i3; ++l3) {
							for(int i4 = k2 + 1; !flag1 && i4 >= j2 - 1; --i4) {
								k3 = (j3 * 16 + l3) * 256 + i4;
								if(i4 >= 0 && i4 < 256) {
									if(abyte0[k3] == Block.waterMoving.blockID || abyte0[k3] == Block.waterStill.blockID) {
										flag1 = true;
									}
									if(i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1) {
										i4 = j2;
									}
								}
							}
						}
					}
					if(!flag1) {
						for(j3 = l1; j3 < i2; ++j3) {
							double d12 = (j3 + i * 16 + 0.5D - d) / d6;
							for(k3 = l2; k3 < i3; ++k3) {
								double d13 = (k3 + j * 16 + 0.5D - d2) / d6;
								int j4 = (j3 * 16 + k3) * 256 + k2;
								boolean flag2 = false;
								if(d12 * d12 + d13 * d13 < 1.0D) {
									for(int k4 = k2 - 1; k4 >= j2; --k4) {
										double d14 = (k4 + 0.5D - d1) / d7;
										if((d12 * d12 + d13 * d13) * this.array[k4] + d14 * d14 / 6.0D < 1.0D) {
											byte b = abyte0[j4];
											if(b == Block.grass.blockID) {
												flag2 = true;
											}
											if(b == Block.stone.blockID || b == Block.dirt.blockID || b == Block.grass.blockID) {
												if(k4 < 10) {
													abyte0[j4] = (byte)Block.lavaMoving.blockID;
												}
												else {
													abyte0[j4] = 0;
													if(flag2 && abyte0[j4 - 1] == Block.dirt.blockID) {
														abyte0[j4 - 1] = worldObj.getBiomeGenForCoords(k3 + i * 16, k4 + j * 16).topBlock;
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
	protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
		if(this.rand.nextInt(40) == 0) {
			double d0 = par2 * 16 + this.rand.nextInt(16);
			double d1 = this.rand.nextInt(100) + 16;
			double d2 = par3 * 16 + this.rand.nextInt(16);
			byte b0 = 1;
			for(int i1 = 0; i1 < b0; ++i1) {
				float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 4.0F;
				this.generateRavine(this.rand.nextLong(), par4, par5, par6ArrayOfByte, d0, d1, d2, f2, f, f1, 0, 0, 24.0D);
			}
		}
	}

}
