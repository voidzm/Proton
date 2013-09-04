/*******
>>> ProtonStoneRegistry.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.registry;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import com.voidzm.proton.block.BlockProton;
import com.voidzm.proton.block.BlockProtonSlab;
import com.voidzm.proton.block.BlockProtonStairs;
import com.voidzm.proton.block.BlockProtonStone;
import com.voidzm.proton.util.ProtonConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;

public class ProtonStoneRegistry {

	private static ArrayList<BlockProtonStone> protonStones = new ArrayList<BlockProtonStone>();
	private static ArrayList<BlockProtonStairs> protonStairs = new ArrayList<BlockProtonStairs>();
	private static ArrayList<BlockProtonSlab> protonSlabs = new ArrayList<BlockProtonSlab>();
	private static BlockProtonStone constructingStone = null;
	private static BlockProtonSlab constructingSlab = null;

	private static ProtonConfiguration config = null;

	public static boolean initialized = false;
	public static boolean finalized = false;

	private static boolean idsSetUp = false;
	private static int stoneIDStart = 0;
	private static int stoneIDSize = 0;
	private static int slabIDStart = 0;
	private static int slabIDSize = 0;
	private static int stairsIDStart = 0;
	private static int stairsIDSize = 0;

	private static HashMap<String, Integer> stoneIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> stoneMetaMap = new HashMap<String, Integer>();

	private static HashMap<String, Integer> slabIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> slabMetaMap = new HashMap<String, Integer>();

	private static HashMap<String, Integer> stairsIDMap = new HashMap<String, Integer>();

	public static int fetchStoneIDForName(String name) {
		if(stoneIDMap.containsKey(name)) {
			return stoneIDMap.get(name);
		}
		else return -1;
	}

	public static int fetchStoneMetaForName(String name) {
		if(stoneMetaMap.containsKey(name)) {
			return stoneMetaMap.get(name);
		}
		else return -1;
	}

	public static int fetchSlabIDForName(String name) {
		if(slabIDMap.containsKey(name)) {
			return slabIDMap.get(name);
		}
		else return -1;
	}

	public static int fetchSlabMetaForName(String name) {
		if(slabMetaMap.containsKey(name)) {
			return slabMetaMap.get(name);
		}
		else return -1;
	}

	public static int fetchStairsIDForName(String name) {
		if(stairsIDMap.containsKey(name)) {
			return stairsIDMap.get(name);
		}
		else return -1;
	}

	private static void setupIDs() {
		/* Just basic error checking against backwards ranges and all that */
		int start = config.protonstonestartID;
		int end = config.protonstoneendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton stone cannot be less than start of range!");
		}
		stoneIDSize = (end - start) + 1;
		stoneIDStart = start;
		start = config.protonstoneslabstartID;
		end = config.protonstoneslabendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton stone slabs cannot be less than start of range!");
		}
		slabIDSize = (end - start) + 1;
		slabIDStart = start;
		start = config.protonstonestairsstartID;
		end = config.protonstonestairsendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton stone stairs cannot be less than start of range!");
		}
		stairsIDSize = (end - start) + 1;
		stairsIDStart = start;
		idsSetUp = true;
	}

	public static void init(ProtonConfiguration cfgObject) {
		if(config != null) {
			throw new RuntimeException("ProtonStoneRegistry already loaded!");
		}
		if(cfgObject == null) {
			throw new RuntimeException("Config required for ProtonStoneRegistry!");
		}
		config = cfgObject;
		initialized = true;
	}

	public static void registerStone(String mainName, String slabName, String stairsName, String texture) {
		registerStone(mainName, slabName, stairsName, texture, texture, texture);
	}

	public static void registerStone(String mainName, String slabName, String stairsName, String textureTop, String textureSide, String textureBottom) {
		if(finalized == true) { /* New logs cannot be added once everything has been registered and setup with Forge. */
			System.out.println("Registration of " + mainName + " failed: ProtonStoneRegistry is already finalized.");
			return;
		}
		if(initialized == false) { /* We don't have a config yet, so we'll hit a NullPointerException with the ID check */
			System.out.println("Registration of " + mainName + " failed: ProtonStoneRegistry is not initialized.");
			return;
		}

		if(idsSetUp == false) { /* Reads all of the ranges for the wood IDs from the config that we have established to exist. */
			setupIDs();
		}

		/* We need to store this stuff for later when we put all of the IDs into the HashMaps. */
		int stoneID;
		int stoneMeta;
		int stairsID;
		int slabID;
		int slabMeta;

		/* Stone */
		if(constructingStone == null) {
			if(protonStones.size() >= stoneIDSize) {
				System.out.println("Registration of " + mainName + " failed: ProtonStoneRegistry has used all available IDs.");
				return;
			}
			constructingStone = new BlockProtonStone(stoneIDStart + protonStones.size());
			constructingStone.setInternalName("protonstone" + (protonStones.size() + 1));
		}
		constructingStone.addStone(mainName, textureTop, textureSide, textureBottom);
		stoneID = constructingStone.blockID;
		stoneMeta = constructingStone.stonesRepresented - 1;

		/* Stairs */
		if(protonStairs.size() >= stairsIDSize) {
			System.out.println("Registration of " + stairsName + " failed: ProtonStoneRegistry has used all available IDs.");
			return;
		}
		BlockProtonStairs constructingStairs = new BlockProtonStairs(stairsIDStart + protonStairs.size(), constructingStone, stoneMeta, "protonstonestairs" + (protonStairs.size() + 1), stairsName);
		stairsID = constructingStairs.blockID;
		BlockProton.register(constructingStairs, constructingStairs.getRegisterData());
		protonStairs.add(constructingStairs);

		/* Slab */
		if(constructingSlab == null) {
			if(protonSlabs.size() >= slabIDSize) {
				System.out.println("Registration of " + slabName + " failed: ProtonStoneRegistry has used all available IDs.");
				return;
			}
			constructingSlab = new BlockProtonSlab(slabIDStart + protonSlabs.size(), Material.rock, "protonstoneslab" + (protonSlabs.size() + 1));
		}
		constructingSlab.addSlab(slabName, textureTop, textureSide, textureBottom);
		slabID = constructingSlab.blockID;
		slabMeta = constructingSlab.slabsRepresented - 1;
		if(constructingSlab.slabsRepresented >= 8) {
			constructingSlab.finalize();
			protonSlabs.add(constructingSlab);
			constructingSlab = null;
		}

		/* Finish up the stone */
		if(constructingStone.stonesRepresented >= 16) {
			constructingStone.finalize();
			protonStones.add(constructingStone);
			constructingStone = null;
		}

		/* Recipes */
		GameRegistry.addRecipe(new ItemStack(stairsID, 4, 0), new Object[] {"P  ", "PP ", "PPP", 'P', new ItemStack(stoneID, 1, stoneMeta)});
		GameRegistry.addRecipe(new ItemStack(stairsID, 4, 0), new Object[] {"  P", " PP", "PPP", 'P', new ItemStack(stoneID, 1, stoneMeta)});
		GameRegistry.addRecipe(new ItemStack(slabID, 6, slabMeta), new Object[] {"PPP", 'P', new ItemStack(stoneID, 1, stoneMeta)});

		/* Input everything to the HashMaps */
		stoneIDMap.put(mainName, stoneID);
		stoneMetaMap.put(mainName, stoneMeta);
		stairsIDMap.put(mainName, stairsID);
		slabIDMap.put(mainName, slabID);
		slabMetaMap.put(mainName, slabMeta);
	}

	public static void registrationDone() {
		/* If any incomplete blocks are still in the system, finalize them and pull them out. */
		if(constructingStone != null) {
			constructingStone.finalize();
			protonStones.add(constructingStone);
			constructingStone = null;
		}
		if(constructingSlab != null) {
			constructingSlab.finalize();
			protonSlabs.add(constructingSlab);
			constructingSlab = null;
		}
		finalized = true;
		/* Register everything with Forge. */
		for(BlockProtonStone stone : protonStones) {
			stone.register();
		}
		for(BlockProtonSlab slab : protonSlabs) {
			BlockProton.register(slab, slab.getRegisterData());
		}
	}

}
