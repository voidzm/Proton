/*******
>>> Proton.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton;

import com.voidzm.proton.controller.BlockController;
import com.voidzm.proton.controller.CraftingController;
import com.voidzm.proton.controller.DimensionController;
import com.voidzm.proton.controller.EnvironmentController;
import com.voidzm.proton.controller.ItemController;
import com.voidzm.proton.handler.EventsHandler;
import com.voidzm.proton.util.Constants;
import com.voidzm.proton.util.ProtonConfiguration;
import com.voidzm.proton.util.Startup;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Constants.modid, name = Constants.modname, version = Constants.modversion)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Proton {

	@Instance(value = Constants.modid)
	public static Proton instance;

	@SidedProxy(clientSide = Constants.clientproxy, serverSide = Constants.serverproxy)
	public static ServerProxy proxy;

	public static ProtonConfiguration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = new ProtonConfiguration(event.getSuggestedConfigurationFile());
		DimensionController.init(config);
		EventsHandler.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		BlockController.init(config);
		ItemController.init(config);
		EnvironmentController.init(config);
		CraftingController.init(config);
		proxy.registerRenderers();
		Startup.loadingDone();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
