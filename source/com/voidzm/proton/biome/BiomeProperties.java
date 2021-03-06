/*******
>>> BiomeProperties.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.biome;

import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;

import com.voidzm.proton.biome.BiomeStructureGen.StructureGenType;

public enum BiomeProperties {

	EXTREMEFOREST(1, 0, 2, 0.4F, 1.8F, 0.7F, 0.8F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 5), new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 1)),
	INSANITYHEIGHTS(3, 0, 2, -1.0F, 4.0F, 0.7F, 0.8F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 7), new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 1)),
	GRASSYSUMMITS(15, 0, 1, 1.9F, 1.9F, 0.7F, 0.3F, 0x94BA5B, 0x94BA5B, new BiomeStructureGen[] {}),
	FROZENFOREST(-999, -999, -999, 0.2F, 0.4F, 0.0F, 0.8F, 0x98D9D4, 0x98D9D4, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 5), new BiomeStructureGen(StructureGenType.BIGTREE, false, 2)),
	SAVANNA(9, -999, 1, 0.1F, 0.1F, 0.7F, 0.2F, 0xADA263, 0xADA263, new BiomeStructureGen(StructureGenType.OLIVETREE, false, 5)),
	SANDYPEAKS(-999, 10, -999, 0.4F, 1.8F, 1.5F, 0.0F, new BiomeStructureGen[] {}),
	ALPINE(-999, 0, 2, 0.4F, 1.8F, 0.1F, 0.8F, new BiomeStructureGen(StructureGenType.SPRUCETREE1, true, 3), new BiomeStructureGen(StructureGenType.SPRUCETREE2, true, 1)),
	STARWOODFOREST(2, 8, -999, 0.4F, 0.55F, 0.5F, 0.5F, 0xEAEDD8, 0xCCE69A, new BiomeStructureGen(StructureGenType.STARWOODTREE, true, 8), new BiomeStructureGen(StructureGenType.STARSTONEVEIN, true, 4)),
	RAINFOREST(5, 8, 3, 0.3F, 1.4F, 1.2F, 1.0F, new BiomeStructureGen(StructureGenType.MAHOGANYTREE, false, 5), new BiomeStructureGen(StructureGenType.SMALLTREE, true, 8), new BiomeStructureGen(StructureGenType.JUNGLETREE, true, 8), new BiomeStructureGen(StructureGenType.JUNGLESHRUB, true, 12)),
	TEMPERATEHILLS(4, 1, -999, 0.2F, 0.6F, 0.3F, 0.5F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 6), new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 1), new BiomeStructureGen(StructureGenType.SPRUCETREE1, true, 2), new BiomeStructureGen(StructureGenType.SPRUCETREE2, true, 2)),
	DUSTYHIGHLANDS(10, -999, -999, 5.0F, 5.0F, 0.5F, 0.1F, 0xBDC28D, 0xBDC28D, new BiomeStructureGen[] {}),
	CANYON(-999, -999, -999, 0.8F, 1.5F, 0.7F, 0.1F, new BiomeStructureGen(StructureGenType.CANYONSURFACE, false, 5), new BiomeStructureGen(StructureGenType.WITHEREDSTUMPS, false, 4)),
	MOSSYPOOLS(10, 10, 3, -0.3F, 0.1F, 1.2F, 1.2F, new BiomeStructureGen(StructureGenType.SWAMPTREE, true, 15)),
	SHRUBLAND(15, -999, 1, 0.2F, 0.5F, 1.6F, 0.0F, new BiomeStructureGen(StructureGenType.SHRUBLANDTREE, true, 10), new BiomeStructureGen(StructureGenType.OAKSHRUB, true, 2)),
	ARCTICDESERT(-999, -999, -999, 0.1F, 0.2F, 0.0F, 0.0F, new BiomeStructureGen[] {}),
	SNOWYWASTELAND(-999, -999, -999, 0.05F, 0.35F, 0.0F, 0.0F, new BiomeStructureGen[] {}),
	ANCIENTFOREST(8, 1, 1, 0.2F, 0.4F, 0.6F, 0.6F, new BiomeStructureGen(StructureGenType.BIGTREE, true, 9), new BiomeStructureGen(StructureGenType.SMALLTREE, true, 2), new BiomeStructureGen(StructureGenType.MOSS, true, 5), new BiomeStructureGen(StructureGenType.ACRANATHREAD, false, 5)),
	ANCIENTJUNGLE(5, 3, 3, 0.3F, 0.6F, 1.2F, 1.2F, new BiomeStructureGen(StructureGenType.BIGJUNGLETREE, true, 10), new BiomeStructureGen(StructureGenType.JUNGLETREE, true, 2), new BiomeStructureGen(StructureGenType.JUNGLESHRUB, true, 3), new BiomeStructureGen(StructureGenType.MOSS, true, 5), new BiomeStructureGen(StructureGenType.ACRANATHREAD, false, 10)),
	CREVASSE(-999, -999, -999, 0.05F, 0.35F, 0.0F, 0.0F, 0xB8BFBA, 0xB8BFBA, new BiomeStructureGen(StructureGenType.CAVEVINES, true, 32), new BiomeStructureGen(StructureGenType.VAPORSTREAMS, false, 4));

	public int grass;
	public int sugarCane;
	public int flowers;

	public float minHeight;
	public float maxHeight;
	public float temperature;
	public float rainfall;

	public int grassColor;
	public int leavesColor;

	public BiomeStructureGen[] gens;

	private BiomeProperties(int grassSpawn, int sugarCaneSpawn, int flowerSpawn, float min, float max, float temp, float rain, int grassCol, int foliageCol, BiomeStructureGen... generators) {
		this.grass = grassSpawn;
		this.sugarCane = sugarCaneSpawn;
		this.flowers = flowerSpawn;
		this.minHeight = min;
		this.maxHeight = max;
		this.temperature = temp;
		this.rainfall = rain;
		this.grassColor = grassCol;
		this.leavesColor = foliageCol;
		this.gens = generators;
	}

	private BiomeProperties(int grassSpawn, int sugarCaneSpawn, int flowerSpawn, float min, float max, float temp, float rain, BiomeStructureGen... generators) {
		this.grass = grassSpawn;
		this.sugarCane = sugarCaneSpawn;
		this.flowers = flowerSpawn;
		this.minHeight = min;
		this.maxHeight = max;
		this.temperature = temp;
		this.rainfall = rain;
		double d0 = MathHelper.clamp_float(temp, 0.0F, 1.0F);
		double d1 = MathHelper.clamp_float(rain, 0.0F, 1.0F);
		this.grassColor = ColorizerGrass.getGrassColor(d0, d1);
		this.leavesColor = ColorizerFoliage.getFoliageColor(d0, d1);
		this.gens = generators;
	}

}
