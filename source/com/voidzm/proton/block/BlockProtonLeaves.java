/*******
>>> BlockProtonLeaves.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.voidzm.proton.item.ItemBlockProton;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockProtonLeaves extends BlockProton implements IShearable {

	private String[] textureNames = new String[4];
	private Icon[] textures = new Icon[4];
	private ArrayList<String> names = new ArrayList<String>();

	public int leavesRepresented = 0;
	private int colorMask = 0;
	private int[] adjacentTreeBlocks;
	private int saplingID;

	public BlockProtonLeaves(int par1, int par2) {
		super(par1, Material.leaves);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(Block.soundGrassFootstep);
		this.setInternalName("protonleaves");
		saplingID = par2;
	}

	@Override
	public void finalize() {
		this.makeMultiblock(names, ItemBlockProton.class);
	}

	public void addLeaves(String name, String icon, boolean autocolor) {
		if(leavesRepresented >= 4) {
			System.out.println("Block " + name + " not registered: this ID already contains four leaves!");
		}
		this.names.add(name + " Leaves");
		textureNames[leavesRepresented] = "proton:leaves" + icon;
		if(autocolor) {
			switch(leavesRepresented) {
				case 0:
					colorMask |= 1;
					break;
				case 1:
					colorMask |= 2;
					break;
				case 2:
					colorMask |= 4;
					break;
				case 3:
					colorMask |= 8;
					break;
			}
		}
		leavesRepresented++;
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 3);
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 1, par1 & 3);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		if(!par1World.isRemote) {
			byte var8 = 20;
			if(par1World.rand.nextInt(var8) == 0) {
				int var9 = this.idDropped(par5, par1World.rand, par7);
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var9, 1, this.damageDropped(par5)));
			}
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return par1Random.nextInt(20) == 0 ? 1 : 0;
	}

	private void removeLeaves(World par1World, int par2, int par3, int par4) {
		this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
		par1World.setBlockToAir(par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
			double var6 = par2 + par5Random.nextFloat();
			double var8 = par3 - 0.05D;
			double var10 = par4 + par5Random.nextFloat();
			par1World.spawnParticle("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!par1World.isRemote) {
			int var6 = par1World.getBlockMetadata(par2, par3, par4);
			if((var6 & 8) != 0 && (var6 & 4) == 0) {
				byte var7 = 4;
				int var8 = var7 + 1;
				byte var9 = 32;
				int var10 = var9 * var9;
				int var11 = var9 / 2;
				if(this.adjacentTreeBlocks == null) {
					this.adjacentTreeBlocks = new int[var9 * var9 * var9];
				}
				int var12;
				if(par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
					int var13;
					int var14;
					int var15;
					for(var12 = -var7; var12 <= var7; ++var12) {
						for(var13 = -var7; var13 <= var7; ++var13) {
							for(var14 = -var7; var14 <= var7; ++var14) {
								var15 = par1World.getBlockId(par2 + var12, par3 + var13, par4 + var14);
								Block block = Block.blocksList[var15];
								if(block != null && block.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14)) {
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
								}
								else if(block != null && block.isLeaves(par1World, par2 + var12, par3 + var13, par4 + var14)) {
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
								}
								else {
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
								}
							}
						}
					}

					for(var12 = 1; var12 <= 4; ++var12) {
						for(var13 = -var7; var13 <= var7; ++var13) {
							for(var14 = -var7; var14 <= var7; ++var14) {
								for(var15 = -var7; var15 <= var7; ++var15) {
									if(this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1) {
										if(this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
											this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
										}

										if(this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
											this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
										}

										if(this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2) {
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
										}

										if(this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2) {
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
										}

										if(this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2) {
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
										}

										if(this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2) {
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
										}
									}
								}
							}
						}
					}
				}
				var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];
				if(var12 >= 0) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & -9, 3);
				}
				else {
					this.removeLeaves(par1World, par2, par3, par4);
				}
			}
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		byte var7 = 1;
		int var8 = var7 + 1;
		if(par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
			for(int var9 = -var7; var9 <= var7; ++var9) {
				for(int var10 = -var7; var10 <= var7; ++var10) {
					for(int var11 = -var7; var11 <= var7; ++var11) {
						int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
						if(Block.blocksList[var12] != null) {
							Block.blocksList[var12].beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
						}
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
		// Fix for placed leaves in creative sometimes decaying
		return par9 | 4;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return true;
	}

	@Override
	public int getRenderColor(int par1) {
		par1 &= 3;
		if(par1 == 0 && (colorMask & 1) == 1) {
			return ColorizerFoliage.getFoliageColorBasic();
		}
		if(par1 == 1 && (colorMask & 2) == 2) {
			return ColorizerFoliage.getFoliageColorBasic();
		}
		if(par1 == 2 && (colorMask & 4) == 4) {
			return ColorizerFoliage.getFoliageColorBasic();
		}
		if(par1 == 3 && (colorMask & 8) == 8) {
			return ColorizerFoliage.getFoliageColorBasic();
		}
		return super.getRenderColor(par1);
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		boolean flag = false;
		int meta = (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 3);
		if(meta == 0 && (colorMask & 1) == 1) {
			flag = true;
		}
		if(meta == 1 && (colorMask & 2) == 2) {
			flag = true;
		}
		if(meta == 2 && (colorMask & 4) == 4) {
			flag = true;
		}
		if(meta == 3 && (colorMask & 8) == 8) {
			flag = true;
		}
		if(flag == false) {
			return super.colorMultiplier(par1IBlockAccess, par2, par3, par4);
		}
		int var6 = 0;
		int var7 = 0;
		int var8 = 0;
		for(int var9 = -1; var9 <= 1; ++var9) {
			for(int var10 = -1; var10 <= 1; ++var10) {
				int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
				var6 += (var11 & 16711680) >> 16;
				var7 += (var11 & 65280) >> 8;
				var8 += var11 & 255;
			}
		}
		return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		double var1 = 0.5D;
		double var3 = 1.0D;
		return ColorizerFoliage.getFoliageColor(var1, var3);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < leavesRepresented; i++) {
			textures[i] = par1IconRegister.registerIcon(textureNames[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta & 3];
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < leavesRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return saplingID;
	}

}
