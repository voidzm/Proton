/*******
>>> BlockAdumbratedAcrana.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.voidzm.proton.controller.BlockController;
import com.voidzm.proton.registry.ProtonStoneRegistry;

public class BlockAdumbratedAcrana extends BlockProton {

	public BlockAdumbratedAcrana(int par1) {
		super(par1, Material.rock);
		this.setInternalName("adumbratedacrana");
		this.setExternalName("Adumbrated Acrana");
		this.setLightValue(0.5F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("proton:adumbratedacrana");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if(checkValidPortalExists(world, x, y, z)) {
			for(int ix = -2; ix < 3; ix++) {
				for(int iy = 0; iy < 2; iy++) {
					for(int iz = -2; iz < 3; iz++) {
						if(iy == 0 && Math.abs(ix) < 2 && Math.abs(iz) < 2 && (ix != 0 || iz != 0)) {
							world.setBlock(x + ix, y + iy, z + iz, BlockController.ijolite.blockID, 0, 3);
						}
						if(iy == 1 && (Math.abs(ix) == 2 || Math.abs(iz) == 2)) {
							world.setBlock(x + ix, y + iy, z + iz, BlockController.ijoliteStairs.blockID, world.getBlockMetadata(x + ix, y + iy, z + iz), 3);
						}
						if(iy == 1 && Math.abs(ix) < 2 && Math.abs(iz) < 2) {
							world.setBlock(x + ix, y + iy, z + iz, BlockController.overworldCrevassePortal.blockID, 0, 3);
						}
					}
				}
			}
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
		this.checkDestruction(par1World, par2, par3, par4);
	}

	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
		this.checkDestruction(par1World, par2, par3, par4);
	}

	private void checkDestruction(World world, int x, int y, int z) {
		if(world.getBlockId(x, y + 1, z) == BlockController.overworldCrevassePortal.blockID) {
			for(int ix = -2; ix < 3; ix++) {
				for(int iy = 0; iy < 2; iy++) {
					for(int iz = -2; iz < 3; iz++) {
						if(iy == 0 && Math.abs(ix) < 2 && Math.abs(iz) < 2 && (ix != 0 || iz != 0)) {
							world.setBlock(x + ix, y + iy, z + iz, ProtonStoneRegistry.fetchStoneIDForName("Acrana Bricks"), ProtonStoneRegistry.fetchStoneMetaForName("Acrana Bricks"), 3);
						}
						if(iy == 1 && (Math.abs(ix) == 2 || Math.abs(iz) == 2)) {
							world.setBlock(x + ix, y + iy, z + iz, ProtonStoneRegistry.fetchStairsIDForName("Acrana Bricks"), world.getBlockMetadata(x + ix, y + iy, z + iz), 3);
						}
						if(iy == 1 && Math.abs(ix) < 2 && Math.abs(iz) < 2) {
							world.setBlockToAir(x + ix, y + iy, z + iz);
						}
					}
				}
			}
		}
	}

	private static boolean checkValidPortalExists(World world, int x, int y, int z) {
		for(int ix = -1; ix < 2; ix++) {
			for(int iz = -1; iz < 2; iz++) {
				if(world.getBlockId(x + ix, y, z + iz) != ProtonStoneRegistry.fetchStoneIDForName("Acrana Bricks") || world.getBlockMetadata(x + ix, y, z + iz) != ProtonStoneRegistry.fetchStoneMetaForName("Acrana Bricks")) {
					if(ix != 0 || iz != 0) {
						return false;
					}
				}
			}
		}
		for(int ix = -2; ix < 3; ix++) {
			for(int iz = -2; iz < 3; iz++) {
				if(Math.abs(ix) < 2 && Math.abs(iz) < 2) {
					if(!world.isAirBlock(x + ix, y + 1, z + iz)) {
						return false;
					}
				}
				int meta = world.getBlockMetadata(x + ix, y + 1, z + iz);
				if(Math.abs(ix) == 2 || Math.abs(iz) == 2) {
					if(world.getBlockId(x + ix, y + 1, z + iz) != ProtonStoneRegistry.fetchStairsIDForName("Acrana Bricks")) {
						return false;
					}
					if(ix == 2) {
						switch(iz) {
							case 2:
								if(meta != 0 && meta != 2) {
									return false;
								}
								break;
							case 1:
							case 0:
							case -1:
								if(meta != 0) {
									return false;
								}
								break;
							case -2:
								if(meta != 0 && meta != 3) {
									return false;
								}
								break;
						}
					}
					else if(ix == -2) {
						switch(iz) {
							case 2:
								if(meta != 1 && meta != 2) {
									return false;
								}
								break;
							case 1:
							case 0:
							case -1:
								if(meta != 1) {
									return false;
								}
								break;
							case -2:
								if(meta != 1 && meta != 3) {
									return false;
								}
								break;
						}
					}
					else {
						if(iz == 2 && meta != 2) {
							return false;
						}
						if(iz == -2 && meta != 3) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
