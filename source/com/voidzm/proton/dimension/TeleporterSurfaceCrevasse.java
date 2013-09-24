/*******
>>> TeleporterSurfaceCrevasse.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.voidzm.proton.controller.BlockController;
import com.voidzm.proton.registry.ProtonStoneRegistry;

public class TeleporterSurfaceCrevasse extends Teleporter {

	private WorldServer serverWorld;

	public TeleporterSurfaceCrevasse(WorldServer par1WorldServer) {
		super(par1WorldServer);
		this.serverWorld = par1WorldServer;
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
		int ex = MathHelper.floor_double(par2);
		int ez = MathHelper.floor_double(par6);
		ProtonCoordinate coord = this.locateExistingSpawnPlatform(ex, ez);
		if(coord != null) {
			par1Entity.setLocationAndAngles((coord.x) + 0.5, (coord.y) + 1.0, (coord.z) + 0.5, par1Entity.rotationYaw, 0.0F);
			return;
		}
		coord = this.findNewSpawnLocation(ex, ez);
		for(int ix = -3; ix < 4; ix++) {
			for(int iy = -1; iy < 5; iy++) {
				for(int iz = -3; iz < 4; iz++) {
					if(Math.abs(ix) == 3 && Math.abs(iz) > 0) continue;
					if(Math.abs(ix) == 2 && Math.abs(iz) > 1) continue;
					if(Math.abs(ix) == 1 && Math.abs(iz) > 2) continue;
					if(iy == -1) {
						if(ix == 0 && iz == 0) this.replaceBlockForSpawn(serverWorld, coord.x + ix, coord.y + iy, coord.z + iz, BlockController.ijolite.blockID, 0);
						else this.replaceBlockForSpawn(serverWorld, coord.x + ix, coord.y + iy, coord.z + iz, ProtonStoneRegistry.fetchStoneIDForName("Acrana Bricks"), ProtonStoneRegistry.fetchStoneMetaForName("Acrana Bricks"));
					}
					else {
						this.replaceBlockForSpawn(serverWorld, coord.x + ix, coord.y + iy, coord.z + iz, 0, 0);
					}
				}
			}
		}
		par1Entity.setLocationAndAngles((coord.x) + 0.5, (coord.y) + 1.0, (coord.z) + 0.5, par1Entity.rotationYaw, 0.0F);
	}

	public ProtonCoordinate locateExistingSpawnPlatform(int xPos, int zPos) {
		int radius = 64;
		for(int ix = -radius; ix < radius; ix++) {
			for(int iz = -radius; iz < radius; iz++) {
				for(int iy = 0; iy < 250; iy++) {
					if(this.serverWorld.getBlockId(xPos + ix, iy, zPos + iz) == BlockController.ijolite.blockID) {
						return new ProtonCoordinate(xPos + ix, iy, zPos + iz);
					}
				}
			}
		}
		return null;
	}

	public ProtonCoordinate findNewSpawnLocation(int xPos, int zPos) {
		Random rand = new Random(serverWorld.getSeed());
		boolean didFindSpawn = false;
		ProtonCoordinate location = null;
		while(!didFindSpawn) {
			for(int iy = 0; iy < 250; iy++) {
				if(this.serverWorld.getBlockId(xPos, iy, zPos) == 0) {
					if(this.serverWorld.getBlockId(xPos, iy - 1, zPos) == Block.stone.blockID) {
						didFindSpawn = true;
						location = new ProtonCoordinate(xPos, iy - 1, zPos);
					}
				}
			}
			if(!didFindSpawn) {
				xPos += (rand.nextInt(64) - rand.nextInt(64));
				zPos += (rand.nextInt(64) - rand.nextInt(64));
			}
		}
		return location;
	}

	private void replaceBlockForSpawn(World world, int x, int y, int z, int id, int meta) {
		int replacedID = world.getBlockId(x, y, z);
		if(replacedID == 0) world.setBlock(x, y, z, id, meta, 0);
		if(replacedID == Block.stone.blockID) world.setBlock(x, y, z, id, meta, 0);
		if(replacedID == Block.dirt.blockID) world.setBlock(x, y, z, id, meta, 0);
		if(replacedID == Block.gravel.blockID) world.setBlock(x, y, z, id, meta, 0);
	}

	public class ProtonCoordinate {

		public int x, y, z;

		public ProtonCoordinate(int par1, int par2, int par3) {
			this.x = par1;
			this.y = par2;
			this.z = par3;
		}

	}

}
