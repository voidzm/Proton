/*******
>>> ProtonLeavesRegistry.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.registry;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.voidzm.proton.block.BlockProtonLeaves;
import com.voidzm.proton.block.BlockProtonSapling;
import com.voidzm.proton.gen.WorldGenProtonTree;
import com.voidzm.proton.util.ProtonConfiguration;

public class ProtonLeavesRegistry {

	private static ArrayList<BlockProtonLeaves> protonLeaves = new ArrayList<BlockProtonLeaves>();
	private static ArrayList<BlockProtonSapling> protonSaplings = new ArrayList<BlockProtonSapling>();

	private static BlockProtonLeaves constructingLeaves = null;
	private static BlockProtonSapling constructingSapling = null;

	private static ProtonConfiguration config = null;

	public static boolean initialized = false;
	public static boolean finalized = false;

	private static boolean idsSetUp = false;
	private static int leavesIDStart = 0;
	private static int leavesIDSize = 0;
	private static int saplingIDStart = 0;
	private static int saplingIDSize = 0;

	private static HashMap<String, Integer> leavesIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> leavesMetaMap = new HashMap<String, Integer>();

	private static HashMap<String, Integer> saplingIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> saplingMetaMap = new HashMap<String, Integer>();

	public static int fetchLeavesIDForName(String name) {
		if(leavesIDMap.containsKey(name)) {
			return leavesIDMap.get(name);
		}
		else return -1;
	}

	public static int fetchLeavesMetaForName(String name) {
		if(leavesMetaMap.containsKey(name)) {
			return leavesMetaMap.get(name);
		}
		else return -1;
	}

	public static int fetchSaplingIDForName(String name) {
		if(saplingIDMap.containsKey(name)) {
			return saplingIDMap.get(name);
		}
		else return -1;
	}

	public static int fetchSaplingMetaForName(String name) {
		if(saplingMetaMap.containsKey(name)) {
			return saplingMetaMap.get(name);
		}
		else return -1;
	}

	private static void setupIDs() {
		/* Just basic error checking against backwards ranges and all that */
		int start = config.protonleavesstartID;
		int end = config.protonleavesendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton leaves cannot be less than start of range!");
		}
		leavesIDSize = (end - start) + 1;
		leavesIDStart = start;
		start = config.protonsaplingstartID;
		end = config.protonsaplingendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton saplings cannot be less than start of range!");
		}
		saplingIDSize = (end - start) + 1;
		saplingIDStart = start;
	}

	public static void init(ProtonConfiguration cfgObject) {
		if(config != null) {
			throw new RuntimeException("ProtonLeavesRegistry already loaded!");
		}
		if(cfgObject == null) {
			throw new RuntimeException("Config required for ProtonLeavesRegistry!");
		}
		config = cfgObject;
		initialized = true;
	}

	public static void registerLeaves(String name, String texture, boolean autocolor, WorldGenProtonTree generator) {
		registerLeaves(name, texture, autocolor, generator, 0);
	}

	public static void registerLeaves(String name, String texture, boolean autocolor, WorldGenProtonTree generator, int lightValue) {
		if(finalized == true) { /* New leaves cannot be added once everything has been registered and setup with Forge. */
			System.out.println("Registration of " + name + " failed: ProtonLeavesRegistry is already finalized.");
			return;
		}
		if(initialized == false) { /* We don't have a config yet, so we'll hit a NullPointerException with the ID check */
			System.out.println("Registration of " + name + " failed: ProtonLeavesRegistry is not initialized.");
			return;
		}

		if(idsSetUp == false) { /* Reads all of the ranges for the leaves IDs from the config that we have established to exist. */
			setupIDs();
		}

		/* We need to store this stuff for later when we put all of the IDs into the HashMaps. */
		int leavesID;
		int leavesMeta;
		int saplingID;
		int saplingMeta;

		/* Sapling */
		if(constructingSapling == null) {
			if(protonSaplings.size() >= saplingIDSize) {
				System.out.println("Registration of " + name + " failed: ProtonLeavesRegistry has used all available IDs.");
				return;
			}
			constructingSapling = new BlockProtonSapling(saplingIDStart + protonSaplings.size());
			constructingSapling.setInternalName("protonsapling" + (protonSaplings.size() + 1));
		}
		constructingSapling.addSapling(name, texture, generator);
		saplingID = constructingSapling.blockID;
		saplingMeta = constructingSapling.saplingsRepresented - 1;
		if(constructingSapling.saplingsRepresented >= 4) {
			constructingSapling.finalize();
			protonSaplings.add(constructingSapling);
			constructingSapling = null;
		}

		/* Leaves */
		if(constructingLeaves == null) {
			if(protonLeaves.size() >= leavesIDSize) {
				System.out.println("Registration of " + name + " failed: ProtonLeavesRegistry has used all available IDs.");
				return;
			}
			constructingLeaves = new BlockProtonLeaves(leavesIDStart + protonLeaves.size(), saplingID);
			constructingLeaves.setInternalName("protonleaves" + (protonLeaves.size() + 1));
		}
		lightValue &= 15;
		constructingLeaves.addLeaves(name, texture, autocolor, lightValue);
		leavesID = constructingLeaves.blockID;
		leavesMeta = constructingLeaves.leavesRepresented - 1;
		if(constructingLeaves.leavesRepresented >= 4) {
			constructingLeaves.finalize();
			protonLeaves.add(constructingLeaves);
			constructingLeaves = null;
		}

		/* OreDictionary */
		OreDictionary.registerOre("treeSapling", new ItemStack(saplingID, 1, saplingMeta));
		OreDictionary.registerOre("treeLeaves", new ItemStack(leavesID, 1, leavesMeta));

		/* Input everything to the HashMaps */
		leavesIDMap.put(name, leavesID);
		leavesMetaMap.put(name, leavesMeta);
		saplingIDMap.put(name, saplingID);
		saplingMetaMap.put(name, saplingMeta);
	}

	public static void registrationDone() {
		/* If any incomplete blocks are still in the system, finalize them and pull them out. */
		if(constructingLeaves != null) {
			constructingLeaves.finalize();
			protonLeaves.add(constructingLeaves);
			constructingLeaves = null;
		}
		if(constructingSapling != null) {
			constructingSapling.finalize();
			protonSaplings.add(constructingSapling);
			constructingSapling = null;
		}
		finalized = true;
		/* Register everything with Forge. */
		for(BlockProtonLeaves leaves : protonLeaves) {
			leaves.register();
		}
		for(BlockProtonSapling sapling : protonSaplings) {
			sapling.register();
		}
	}

}
