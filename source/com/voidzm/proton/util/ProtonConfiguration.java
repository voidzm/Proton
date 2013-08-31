/*******
>>> ProtonConfiguration.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.util;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ProtonConfiguration {

	public static final String CATEGORY_BIOME = "biome";
	public static final String CATEGORY_DIMENSION = "dimension";

	private Configuration internalCfg;

	/**** BLOCK ****/

	public int protonlogstartID;
	public int protonlogendID;
	public int protonplanksstartID;
	public int protonplanksendID;
	public int protonwoodstairsstartID;
	public int protonwoodstairsendID;
	public int protonwoodslabstartID;
	public int protonwoodslabendID;
	public int protonleavesstartID;
	public int protonleavesendID;
	public int protonsaplingstartID;
	public int protonsaplingendID;

	public int protonstonestartID;
	public int protonstoneendID;
	public int protonstoneslabstartID;
	public int protonstoneslabendID;
	public int protonstonestairsstartID;
	public int protonstonestairsendID;

	public int adobeID;
	public int fracturedadobeID;
	public int chiseledadobeID;

	/**** BIOME ****/

	public int extremeforestID;
	public int insanityheightsID;
	public int grassysummitsID;
	public int frozenforestID;
	public int savannaID;
	public int sandypeaksID;
	public int alpineID;
	public int starwoodforestID;
	public int rainforestID;
	public int temperatehillsID;
	public int dustyhighlandsID;
	public int canyonID;

	/**** DIMENSION ****/

	public boolean doalternatesurface;
	public int alternatesurfaceproviderID;

	public ProtonConfiguration(File file) {
		internalCfg = new Configuration(file);
		internalCfg.save();
		internalCfg.load();
		this.loadBlockConfig();
		this.loadBiomeConfig();
		this.loadDimensionConfig();
		internalCfg.save();
		System.out.println("[Proton] Config loaded.");
	}

	private void loadBlockConfig() {
		protonlogstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonlogstartID", "1600").getInt();
		protonlogendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonlogendID", "1607").getInt();
		protonplanksstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonplanksstartID", "1608").getInt();
		protonplanksendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonplanksendID", "1609").getInt();
		protonwoodstairsstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonwoodstairsstartID", "1610").getInt();
		protonwoodstairsendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonwoodstairsendID", "1641").getInt();
		protonwoodslabstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonwoodslabstartID", "1642").getInt();
		protonwoodslabendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonwoodslabendID", "1645").getInt();
		protonleavesstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonleavesstartID", "1646").getInt();
		protonleavesendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonleavesendID", "1653").getInt();
		protonsaplingstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonsaplingstartID", "1654").getInt();
		protonsaplingendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonsaplingendID", "1661").getInt();

		protonstonestartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonstonestartID", "1662").getInt();
		protonstoneendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonstoneendID", "1663").getInt();
		protonstoneslabstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonstoneslabstartID", "1664").getInt();
		protonstoneslabendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonstoneslabendID", "1667").getInt();
		protonstonestairsstartID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonstonestairsstartID", "1668").getInt();
		protonstonestairsendID = internalCfg.get(Configuration.CATEGORY_BLOCK, "protonstonestairsendID", "1699").getInt();

		adobeID = internalCfg.get(Configuration.CATEGORY_BLOCK, "adobeID", "1700").getInt();
		fracturedadobeID = internalCfg.get(Configuration.CATEGORY_BLOCK, "fracturedadobeID", "1701").getInt();
		chiseledadobeID = internalCfg.get(Configuration.CATEGORY_BLOCK, "chiseledadobeID", "1702").getInt();
	}

	private void loadBiomeConfig() {
		extremeforestID = internalCfg.get(this.CATEGORY_BIOME, "extremeforestID", "50").getInt();
		insanityheightsID = internalCfg.get(this.CATEGORY_BIOME, "insanityheightsID", "51").getInt();
		grassysummitsID = internalCfg.get(this.CATEGORY_BIOME, "grassysummitsID", "52").getInt();
		frozenforestID = internalCfg.get(this.CATEGORY_BIOME, "frozenforestID", "53").getInt();
		savannaID = internalCfg.get(this.CATEGORY_BIOME, "savannaID", "54").getInt();
		sandypeaksID = internalCfg.get(this.CATEGORY_BIOME, "sandypeaksID", "55").getInt();
		alpineID = internalCfg.get(this.CATEGORY_BIOME, "alpineID", "56").getInt();
		starwoodforestID = internalCfg.get(this.CATEGORY_BIOME, "starwoodforestID", "57").getInt();
		rainforestID = internalCfg.get(this.CATEGORY_BIOME, "rainforestID", "58").getInt();
		temperatehillsID = internalCfg.get(this.CATEGORY_BIOME, "temperatehillsID", "59").getInt();
		dustyhighlandsID = internalCfg.get(this.CATEGORY_BIOME, "dustyhighlandsID", "60").getInt();
		canyonID = internalCfg.get(this.CATEGORY_BIOME, "canyonID", "61").getInt();
	}

	private void loadDimensionConfig() {
		doalternatesurface = internalCfg.get(this.CATEGORY_DIMENSION, "doalternatesurface", "true").getBoolean(true);
		alternatesurfaceproviderID = internalCfg.get(this.CATEGORY_DIMENSION, "alternatesurfaceID", "16").getInt();
	}

}
