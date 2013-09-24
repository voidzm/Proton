/*******
>>> ChunkProviderCrevasse.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;

import com.voidzm.proton.gen.WorldGenCrevasseCaves;
import com.voidzm.proton.gen.WorldGenCrevasseFissures;

public class ChunkProviderCrevasse implements IChunkProvider {

	public Random deepRNG;
	public World worldObj;

	private MapGenBase caveGenerator = new WorldGenCrevasseCaves();
	private MapGenBase fissureGenerator = new WorldGenCrevasseFissures();

	public ChunkProviderCrevasse(World par1World, long par2Long) {
		this.worldObj = par1World;
		this.deepRNG = new Random(par2Long);
	}

	@Override
	public boolean chunkExists(int i, int j) {
		return true;
	}

	@Override
	public Chunk provideChunk(int i, int j) {
		this.deepRNG.setSeed(i * 341873128712L + j * 132897987541L);
		byte[] cdata = new byte[65536];
		this.genDeepTerrain(cdata, i, j);
		this.caveGenerator.generate(this, this.worldObj, i, j, cdata);
		this.fissureGenerator.generate(this, this.worldObj, i, j, cdata);
		Chunk chunk = new Chunk(this.worldObj, i, j);
		BiomeGenBase[] biomearray = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, i * 16, j * 16, 16, 16);
		ExtendedBlockStorage[] ebs = chunk.getBlockStorageArray();
		byte[] abyte1 = chunk.getBiomeArray();
		for(int k = 0; k < abyte1.length; ++k) {
			abyte1[k] = (byte)biomearray[k].biomeID;
		}
		for(int ix = 0; ix < 16; ix++) {
			for(int iz = 0; iz < 16; iz++) {
				for(int iy = 0; iy < 256; iy++) {
					byte b0 = cdata[ix << 12 | iz << 8 | iy];
					if(b0 == 0) {
						continue;
					}
					int cy = iy >> 4;
					if(ebs[cy] == null) {
						ebs[cy] = new ExtendedBlockStorage(cy << 4, true);
					}
					ebs[cy].setExtBlockID(ix, iy & 15, iz, b0 & 255);
				}
			}
		}
		chunk.resetRelightChecks();
		return chunk;
	}

	private void genDeepTerrain(byte[] array, int cx, int cy) {
		int i = 0;
		for(int ix = 0; ix < 16; ix++) {
			for(int iz = 0; iz < 16; iz++) {
				for(int iy = 0; iy < (array.length / 256); iy++) {
					if(iy == 0 || iy == (array.length / 256) - 1) {
						array[i] = (byte)Block.bedrock.blockID;
					}
					else if(iy == 1 || iy == (array.length / 256) - 2) {
						if(this.deepRNG.nextInt(2) == 0) array[i] = (byte)Block.bedrock.blockID;
						else array[i] = (byte)Block.stone.blockID;
					}
					else if(iy == 2 || iy == (array.length / 256) - 3) {
						if(this.deepRNG.nextInt(4) == 0) array[i] = (byte)Block.bedrock.blockID;
						else array[i] = (byte)Block.stone.blockID;
					}
					else array[i] = (byte)Block.stone.blockID;
					i++;
				}
			}
		}
	}

	@Override
	public Chunk loadChunk(int i, int j) {
		return this.provideChunk(i, j);
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		int x = i * 16;
		int z = j * 16;
		BiomeGenBase biomeGen = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);
		this.deepRNG.setSeed(this.worldObj.getSeed());
		long i1 = this.deepRNG.nextLong() / 2L * 2L + 1L;
		long j1 = this.deepRNG.nextLong() / 2L * 2L + 1L;
		this.deepRNG.setSeed(i * i1 + j * j1 ^ this.worldObj.getSeed());
		biomeGen.decorate(this.worldObj, this.deepRNG, x, z);
	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "CrevasseChunkProvider";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
		BiomeGenBase bgen = this.worldObj.getBiomeGenForCoords(i, j);
		return bgen == null ? null : bgen.getSpawnableList(enumcreaturetype);
	}

	@Override
	public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j) {
	}

	@Override
	public void saveExtraData() {
	}

}
