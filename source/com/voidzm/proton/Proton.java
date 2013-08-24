/*******
>>> Proton.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton;

import com.voidzm.proton.handler.PacketHandler;
import com.voidzm.proton.util.Constants;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
	
}
