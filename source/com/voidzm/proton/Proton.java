/*******
>>> Proton.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton;

import com.voidzm.proton.controller.BiomeController;
import com.voidzm.proton.controller.DimensionController;
import com.voidzm.proton.registry.ProtonLogRegistry;
import com.voidzm.proton.util.Constants;
import com.voidzm.proton.util.ProtonConfiguration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=Constants.modid, name=Constants.modname, version=Constants.modversion)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Proton {

	@Instance(value=Constants.modid)
	public static Proton instance;
	
	@SidedProxy(clientSide=Constants.clientproxy, serverSide=Constants.serverproxy)
	public static ServerProxy proxy;
	
	public static ProtonConfiguration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = new ProtonConfiguration(event.getSuggestedConfigurationFile());
		DimensionController.init(config);
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		BiomeController.init(config, false);
		ProtonLogRegistry.registerLog("Olive Wood", "woodolive");
		ProtonLogRegistry.registerLog("Olive Wood 1", "woodolive");
		ProtonLogRegistry.registerLog("Olive Wood 2", "woodolive");
		ProtonLogRegistry.registerLog("Olive Wood 3", "woodolive");
		ProtonLogRegistry.registerLog("Olive Wood 4", "woodolive");
		ProtonLogRegistry.registrationDone();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
	
}
