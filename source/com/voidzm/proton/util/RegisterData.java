/*******
>>> RegisterData.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.util;

import java.util.ArrayList;

import net.minecraft.item.ItemBlock;

public class RegisterData {

	public String internalName = "";
	public String externalName = "";
	
	public boolean isMulti = false;
	
	public ArrayList<String> externalNames = new ArrayList<String>();
	public Class<? extends ItemBlock> itemBlockClass = null;
	
}
