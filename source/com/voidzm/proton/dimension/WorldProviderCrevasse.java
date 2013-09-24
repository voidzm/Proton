/*******
>>> WorldProviderCrevasse.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.dimension;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

import com.voidzm.proton.Proton;
import com.voidzm.proton.controller.BiomeController;

public class WorldProviderCrevasse extends WorldProvider {

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeController.crevasse, 0.5F, 0.5F);
		this.isHellWorld = false;
		this.hasNoSky = true;
		this.dimensionId = Proton.config.crevasseID;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderCrevasse(this.worldObj, this.worldObj.getSeed());
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.0F;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	@Override
	public float[] calcSunriseSunsetColors(float par1, float par2) {
		return null;
	}

	@Override
	public Vec3 getFogColor(float par1, float par2) {
		return this.worldObj.getWorldVec3Pool().getVecFromPool(0.1D, 0.1D, 0.1D);
	}

	@Override
	public boolean isSkyColored() {
		return false;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public String getDimensionName() {
		return "The Crevasse";
	}

	@Override
	public double getMovementFactor() {
		return 1.0;
	}

}
