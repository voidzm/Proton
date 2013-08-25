/*******
>>> ProtonPlugin.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.core;

import java.util.Map;

import com.voidzm.novamenu.asm.NovamenuPlugin;
import com.voidzm.proton.util.Constants;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions(value={"com.voidzm.proton.core", "com.voidzm.proton.gui", "com.voidzm.novamenu.asm", "com.voidzm.novamenu.gui"})
@MCVersion(value=Constants.minecraftversion)
public class ProtonPlugin implements IFMLLoadingPlugin, IFMLCallHook {

	public static boolean isDevEnvironment = true;
	
	@Override
	public Void call() throws Exception {
		return null;
	}

	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"com.voidzm.proton.core.ProtonTransformer", "com.voidzm.novamenu.asm.NovamenuTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return "com.voidzm.proton.core.ProtonPlugin";
	}

	@Override
	public void injectData(Map<String, Object> data) {
		if(data.containsKey("runtimeDeobfuscationEnabled")) isDevEnvironment = !(Boolean)data.get("runtimeDeobfuscationEnabled");
		NovamenuPlugin.instance().injectData(data);
	}

}
