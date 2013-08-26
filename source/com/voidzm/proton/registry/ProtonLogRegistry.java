/*******
>>> ProtonLogRegistry.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.registry;

import java.util.ArrayList;

import com.voidzm.proton.block.BlockProtonLog;

public class ProtonLogRegistry {
	
	private static ArrayList<BlockProtonLog> protonLogs = new ArrayList<BlockProtonLog>();
	private static BlockProtonLog constructingLog = null;
	
	public static boolean finalized = false;
	
	public static void registerLog(String name, String texture) {
		if(finalized == true) {
			System.out.println("Registration of " + name + " failed: ProtonLogRegistry is already finalized.");
			return;
		}
		if(constructingLog == null) {
			constructingLog = new BlockProtonLog(1600 + protonLogs.size());
			constructingLog.setInternalName("protonlog" + (protonLogs.size() + 1));
		}
		constructingLog.addLog(name, texture);
		if(constructingLog.logsRepresented >= 4) {
			constructingLog.finalize();
			protonLogs.add(constructingLog);
			constructingLog = null;
		}
	}
	
	public static void registrationDone() {
		if(constructingLog != null) {
			constructingLog.finalize();
			protonLogs.add(constructingLog);
			constructingLog = null;
		}
		finalized = true;
		for(BlockProtonLog log : protonLogs) {
			log.register();
		}
	}
	
}
