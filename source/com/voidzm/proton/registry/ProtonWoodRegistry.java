/*******
>>> ProtonWoodRegistry.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.registry;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.voidzm.proton.block.BlockProton;
import com.voidzm.proton.block.BlockProtonLog;
import com.voidzm.proton.block.BlockProtonPlanks;
import com.voidzm.proton.block.BlockProtonSlab;
import com.voidzm.proton.block.BlockProtonStairs;
import com.voidzm.proton.util.ProtonConfiguration;

import cpw.mods.fml.common.registry.GameRegistry;

public class ProtonWoodRegistry {
	
	private static ArrayList<BlockProtonLog> protonLogs = new ArrayList<BlockProtonLog>();
	private static ArrayList<BlockProtonPlanks> protonPlanks = new ArrayList<BlockProtonPlanks>();
	private static ArrayList<BlockProtonStairs> protonStairs = new ArrayList<BlockProtonStairs>();
	private static ArrayList<BlockProtonSlab> protonSlabs = new ArrayList<BlockProtonSlab>();
	private static BlockProtonLog constructingLog = null;
	private static BlockProtonPlanks constructingPlanks = null;
	private static BlockProtonSlab constructingSlab = null;
	
	private static ProtonConfiguration config = null;
	
	public static boolean initialized = false;
	public static boolean finalized = false;
	
	private static boolean idsSetUp = false;
	private static int logIDStart = 0;
	private static int logIDSize = 0;
	private static int planksIDStart = 0;
	private static int planksIDSize = 0;
	private static int stairsIDStart = 0;
	private static int stairsIDSize = 0;
	private static int slabIDStart = 0;
	private static int slabIDSize = 0;
	
	private static HashMap<String, Integer> logIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> logMetaMap = new HashMap<String, Integer>();
	
	private static HashMap<String, Integer> planksIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> planksMetaMap = new HashMap<String, Integer>();
	
	private static HashMap<String, Integer> stairsIDMap = new HashMap<String, Integer>();
	
	private static HashMap<String, Integer> slabIDMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> slabMetaMap = new HashMap<String, Integer>();
	
	public static int fetchLogIDForName(String name) {
		if(logIDMap.containsKey(name)) {
			return (int)logIDMap.get(name);
		}
		else return -1;
	}
	
	public static int fetchLogMetaForName(String name) {
		if(logMetaMap.containsKey(name)) {
			return (int)logMetaMap.get(name);
		}
		else return -1;
	}
	
	public static int fetchPlanksIDForName(String name) {
		if(planksIDMap.containsKey(name)) {
			return (int)planksIDMap.get(name);
		}
		else return -1;
	}
	
	public static int fetchPlanksMetaForName(String name) {
		if(planksMetaMap.containsKey(name)) {
			return (int)planksMetaMap.get(name);
		}
		else return -1;
	}
	
	public static int fetchStairsIDForName(String name) {
		if(stairsIDMap.containsKey(name)) {
			return (int)stairsIDMap.get(name);
		}
		else return -1;
	}
	
	public static int fetchSlabIDForName(String name) {
		if(slabIDMap.containsKey(name)) {
			return (int)slabIDMap.get(name);
		}
		else return -1;
	}
	
	public static int fetchSlabMetaForName(String name) {
		if(slabMetaMap.containsKey(name)) {
			return (int)slabMetaMap.get(name);
		}
		else return -1;
	}
	
	private static void setupIDs() {
		/* Just basic error checking against backwards ranges and all that */
		int start = config.protonlogstartID;
		int end = config.protonlogendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton logs cannot be less than start of range!");
		}
		logIDSize = (end - start) + 1;
		logIDStart = start;
		start = config.protonplanksstartID;
		end = config.protonplanksendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton planks cannot be less than start of range!");
		}
		planksIDSize = (end - start) + 1;
		planksIDStart = start;
		start = config.protonwoodstairsstartID;
		end = config.protonwoodstairsendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton wood stairs cannot be less than start of range!");
		}
		stairsIDSize = (end - start) + 1;
		stairsIDStart = start;
		start = config.protonwoodslabstartID;
		end = config.protonwoodslabendID;
		if(end < start) {
			throw new RuntimeException("End of range for Proton wood slabs cannot be less than start of range!");
		}
		slabIDSize = (end - start) + 1;
		slabIDStart = start;
		idsSetUp = true;
	}
	
	public static void init(ProtonConfiguration cfgObject) {
		if(config != null) {
			throw new RuntimeException("ProtonWoodRegistry already loaded!"); 
		}
		if(cfgObject == null) {
			throw new RuntimeException("Config required for ProtonWoodRegistry!");
		}
		config = cfgObject;
		initialized = true;
	}
	
	public static void registerLog(String name, String texture) {
		if(finalized == true) { /* New logs cannot be added once everything has been registered and setup with Forge. */
			System.out.println("Registration of " + name + " failed: ProtonWoodRegistry is already finalized.");
			return;
		}
		if(initialized == false) { /* We don't have a config yet, so we'll hit a NullPointerException with the ID check */
			System.out.println("Registration of " + name + " failed: ProtonWoodRegistry is not initialized.");
			return;
		}
		
		if(idsSetUp == false) { /* Reads all of the ranges for the wood IDs from the config that we have established to exist. */
			setupIDs();
		}
		
		/* We need to store this stuff for later when we put all of the IDs into the HashMaps. */
		int logID;
		int logMeta;
		int planksID;
		int planksMeta;
		int stairsID;
		int slabID;
		int slabMeta;
		
		/* Log */
		if(constructingLog == null) {
			if(protonLogs.size() >= logIDSize) {
				System.out.println("Registration of " + name + " failed: ProtonWoodRegistry has used all available IDs.");
				return;
			}
			constructingLog = new BlockProtonLog(logIDStart + protonLogs.size());
			constructingLog.setInternalName("protonlog" + (protonLogs.size() + 1));
		}
		constructingLog.addLog(name, texture);
		logID = constructingLog.blockID;
		logMeta = constructingLog.logsRepresented - 1;
		if(constructingLog.logsRepresented >= 4) {
			constructingLog.finalize();
			protonLogs.add(constructingLog);
			constructingLog = null;
		}
		
		/* Planks */
		if(constructingPlanks == null) {
			if(protonPlanks.size() >= planksIDSize) {
				System.out.println("Registration of " + name + " failed: ProtonWoodRegistry has used all available IDs.");
				return;
			}
			constructingPlanks = new BlockProtonPlanks(planksIDStart + protonPlanks.size());
			constructingPlanks.setInternalName("protonplanks" + (protonPlanks.size() + 1));
		}
		constructingPlanks.addPlanks(name, texture);
		planksID = constructingPlanks.blockID;
		planksMeta = constructingPlanks.planksRepresented - 1;
		
		/* Stairs */
		if(protonStairs.size() >= stairsIDSize) {
			System.out.println("Registration of " + name + " failed: ProtonWoodRegistry has used all available IDs.");
			return;
		}
		BlockProtonStairs constructingStairs = new BlockProtonStairs(stairsIDStart + protonStairs.size(), constructingPlanks, planksMeta, "protonstairs" + (protonStairs.size() + 1), name + " Wood Stairs");
		stairsID = constructingStairs.blockID;
		BlockProton.register(constructingStairs, constructingStairs.getRegisterData());
		protonStairs.add(constructingStairs);
		
		/* Slab */
		if(constructingSlab == null) {
			if(protonSlabs.size() >= slabIDSize) {
				System.out.println("Registration of " + name + " failed: ProtonWoodRegistry has used all available IDs.");
				return;
			}
			constructingSlab = new BlockProtonSlab(slabIDStart + protonSlabs.size(), Material.wood, "protonslab" + (protonSlabs.size() + 1));
		}
		constructingSlab.addSlab(name + " Wood Slab", "planks" + texture);
		slabID = constructingSlab.blockID;
		slabMeta = constructingSlab.slabsRepresented - 1;
		if(constructingSlab.slabsRepresented >= 8) {
			constructingSlab.finalize();
			protonSlabs.add(constructingSlab);
			constructingSlab = null;
		}
		
		/* Finish up the planks */
		if(constructingPlanks.planksRepresented >= 16) {
			constructingPlanks.finalize();
			protonPlanks.add(constructingPlanks);
			constructingPlanks = null;
		}
		
		/* Recipes and OreDictionary */
		GameRegistry.addShapelessRecipe(new ItemStack(planksID, 4, planksMeta), new ItemStack(logID, 1, logMeta));
		GameRegistry.addRecipe(new ItemStack(stairsID, 4, 0), new Object[] {"P  ", "PP ", "PPP", 'P', new ItemStack(planksID, 1, planksMeta)});
		GameRegistry.addRecipe(new ItemStack(stairsID, 4, 0), new Object[] {"  P", " PP", "PPP", 'P', new ItemStack(planksID, 1, planksMeta)});
		GameRegistry.addRecipe(new ItemStack(slabID, 6, slabMeta), new Object[] {"PPP", 'P', new ItemStack(planksID, 1, planksMeta)});
		OreDictionary.registerOre("logWood", new ItemStack(logID, 1, logMeta));
		OreDictionary.registerOre("plankWood", new ItemStack(planksID, 1, planksMeta));
		OreDictionary.registerOre("stairWood", new ItemStack(stairsID, 1, 0));
		OreDictionary.registerOre("slabWood", new ItemStack(slabID, 1, slabMeta));
		
		/* Input everything to the HashMaps */
		logIDMap.put(name, (Integer)logID);
		logMetaMap.put(name, (Integer)logMeta);
		planksIDMap.put(name, (Integer)planksID);
		planksMetaMap.put(name, (Integer)planksMeta);
		stairsIDMap.put(name, (Integer)stairsID);
		slabIDMap.put(name, (Integer)slabID);
		slabMetaMap.put(name, (Integer)slabMeta);
	}
	
	public static void registrationDone() {
		/* If any incomplete blocks are still in the system, finalize them and pull them out. */
		if(constructingLog != null) {
			constructingLog.finalize();
			protonLogs.add(constructingLog);
			constructingLog = null;
		}
		if(constructingPlanks != null) {
			constructingPlanks.finalize();
			protonPlanks.add(constructingPlanks);
			constructingPlanks = null;
		}
		if(constructingSlab != null) {
			constructingSlab.finalize();
			protonSlabs.add(constructingSlab);
			constructingSlab = null;
		}
		finalized = true;
		/* Register everything with Forge. */
		for(BlockProtonLog log : protonLogs) {
			log.register();
		}
		for(BlockProtonPlanks planks : protonPlanks) {
			planks.register();
		}
		for(BlockProtonSlab slab : protonSlabs) {
			BlockProton.register(slab, slab.getRegisterData());
		}
	}
	
}
