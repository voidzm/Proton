/*******
>>> WorldProviderProtonSurface.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.dimension;

import net.minecraft.block.Block;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;

import com.voidzm.proton.biome.BiomeProton;
import com.voidzm.proton.controller.BlockController;

public class WorldProviderProtonSurface extends WorldProviderSurface {

	@Override
	public boolean doesXZShowFog(int par1, int par2) {
		BiomeGenBase biome = this.getBiomeGenForCoords(par1, par2);
		if(biome instanceof BiomeProton) {
			if(((BiomeProton)biome).doFog) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		int k = this.worldObj.getFirstUncoveredBlock(par1, par2);
		if(k == Block.grass.blockID || k == Block.sand.blockID || k == BlockController.adobe.blockID || k == BlockController.arcticSand.blockID) {
			return true;
		}
		else return false;
	}

	/* This code activates the alternate chunk provider. */

	/*@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderProton(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
	}*/

}
