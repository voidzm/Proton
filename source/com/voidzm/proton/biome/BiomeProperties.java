/*******
>>> BiomeProperties.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.biome;

import com.voidzm.proton.biome.BiomeStructureGen.StructureGenType;

import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;

public enum BiomeProperties {

	EXTREMEFOREST(1, 0, 2, 0.5F, 1.8F, 0.7F, 0.8F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 5), new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 1)),
	INSANITYHEIGHTS(1, 0, 2, -1.0F, 4.0F, 0.7F, 0.8F, new BiomeStructureGen(StructureGenType.SMALLTREE, true, 7), new BiomeStructureGen(StructureGenType.BIRCHTREE, true, 1));

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
		double d0 = (double)MathHelper.clamp_float(temp, 0.0F, 1.0F);
		double d1 = (double)MathHelper.clamp_float(rain, 0.0F, 1.0F);
		this.grassColor = ColorizerGrass.getGrassColor(d0, d1);
		this.leavesColor = ColorizerFoliage.getFoliageColor(d0, d1);	
		this.gens = generators;
	}
	
}
