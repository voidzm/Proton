/*******
>>> BiomeStructureGen.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.voidzm.proton.controller.BlockController;
import com.voidzm.proton.gen.WorldGenCanyonSurface;
import com.voidzm.proton.gen.WorldGenCaveVines;
import com.voidzm.proton.gen.WorldGenLargeForestTree;
import com.voidzm.proton.gen.WorldGenLargeJungleTree;
import com.voidzm.proton.gen.WorldGenMahoganyTree;
import com.voidzm.proton.gen.WorldGenMinableThread;
import com.voidzm.proton.gen.WorldGenMoss;
import com.voidzm.proton.gen.WorldGenOliveTree;
import com.voidzm.proton.gen.WorldGenShrublandTree;
import com.voidzm.proton.gen.WorldGenStarwoodTree;
import com.voidzm.proton.gen.WorldGenVaporStreams;
import com.voidzm.proton.gen.WorldGenWitheredStumps;

public class BiomeStructureGen {

	public enum StructureGenType {
		SMALLTREE(new WorldGenTrees(false), true),
		BIGTREE(new WorldGenLargeForestTree(), true),
		BIRCHTREE(new WorldGenForest(false), true),
		SPRUCETREE1(new WorldGenTaiga1(), true),
		SPRUCETREE2(new WorldGenTaiga2(false), true),
		JUNGLESHRUB(new WorldGenShrub(0, 3), true),
		OAKSHRUB(new WorldGenShrub(0, 0), true),
		BIGJUNGLETREE(new WorldGenLargeJungleTree(), true),
		JUNGLETREE(new WorldGenTrees(false, 7, 3, 3, true), true),
		SWAMPTREE(new WorldGenSwamp(), true),
		OLIVETREE(new WorldGenOliveTree(), true),
		STARWOODTREE(new WorldGenStarwoodTree(), true),
		MAHOGANYTREE(new WorldGenMahoganyTree(), true),
		CANYONSURFACE(new WorldGenCanyonSurface(), true),
		WITHEREDSTUMPS(new WorldGenWitheredStumps(), true),
		SHRUBLANDTREE(new WorldGenShrublandTree(), true),
		MOSS(new WorldGenMoss(), false),
		STARSTONEVEIN(new WorldGenMinable(BlockController.starstone.blockID, 24), false),
		ACRANATHREAD(new WorldGenMinableThread(BlockController.acrana.blockID, 18, 2, 18, 2), false),
		CAVEVINES(new WorldGenCaveVines(), false),
		VAPORSTREAMS(new WorldGenVaporStreams(), false);

		public WorldGenerator gen;
		public boolean isOnSurface;

		private StructureGenType(WorldGenerator generator, boolean surface) {
			this.gen = generator;
			this.isOnSurface = surface;
		}

	}

	private StructureGenType type;

	private boolean isCommon;

	private int genFrequency;

	public BiomeStructureGen(StructureGenType genType, boolean common, int frequency) {
		this.type = genType;
		this.isCommon = common;
		this.genFrequency = frequency;
	}

	public boolean generate(World world, Random rand, int chunkX, int chunkZ) {
		if(this.isCommon) {
			for(int i = 0; i < this.genFrequency; i++) {
				int x = chunkX + rand.nextInt(16) + 8;
				int z = chunkZ + rand.nextInt(16) + 8;
				int y;
				if(this.type.isOnSurface) y = world.getHeightValue(x, z);
				else y = rand.nextInt(128);
				this.type.gen.generate(world, rand, x, y, z);
			}
		}
		else {
			if(rand.nextInt(this.genFrequency) == 0) {
				int x = chunkX + rand.nextInt(16) + 8;
				int z = chunkZ + rand.nextInt(16) + 8;
				int y;
				if(this.type.isOnSurface) y = world.getHeightValue(x, z);
				else y = rand.nextInt(256);
				this.type.gen.generate(world, rand, x, y, z);
			}
		}
		return true;
	}

}
