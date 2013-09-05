/*******
>>> ItemProton.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.item;

import java.util.ArrayList;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.voidzm.proton.protocol.IRegisterable;
import com.voidzm.proton.util.RegisterData;
import com.voidzm.proton.util.Startup;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemProton extends Item {

	private String internalName;
	private String externalName;

	public boolean isMultiitem = false;

	private ArrayList<String> externalNames = null;

	private String iconString;
	private boolean shimmering = false;

	public ItemProton(int par1, String icon, CreativeTabs tab) {
		super(par1);
		this.setMaxStackSize(64);
		this.iconString = icon;
		this.setCreativeTab(tab);
	}

	public ItemProton(int par1, String icon) {
		this(par1, icon, CreativeTabs.tabMaterials);
	}

	public ItemProton setInternalName(String newInternalName) {
		this.internalName = newInternalName;
		this.setUnlocalizedName(newInternalName);
		return this;
	}

	public ItemProton setExternalName(String newExternalName) {
		this.externalName = newExternalName;
		return this;
	}

	public String internalName() {
		return this.internalName;
	}

	public String externalName() {
		return this.externalName;
	}

	public void makeMultiitem(ArrayList<String> names) {
		this.isMultiitem = true;
		this.externalNames = names;
	}

	public ArrayList<String> fetchExternalNames() {
		return this.externalNames;
	}

	public ItemProton register() {
		if(!this.isMultiitem) {
			GameRegistry.registerItem(this, this.internalName);
			LanguageRegistry.addName(this, this.externalName);
			Startup.itemCreated();
		}
		else {
			GameRegistry.registerItem(this, this.internalName);
			int i = 0;
			for(String name : externalNames) {
				ItemStack stack = new ItemStack(this, 1, i);
				LanguageRegistry.addName(stack, name);
				Startup.itemCreated();
				i++;
			}
		}
		return this;
	}

	public static void register(Item item, RegisterData data) {
		if(!(item instanceof IRegisterable)) return;
		if(!data.isMulti) {
			GameRegistry.registerItem(item, data.internalName);
			LanguageRegistry.addName(item, data.externalName);
			Startup.itemCreated();
		}
		else {
			GameRegistry.registerItem(item, data.internalName);
			int i = 0;
			for(String name : data.externalNames) {
				ItemStack stack = new ItemStack(item, 1, i);
				LanguageRegistry.addName(stack, name);
				Startup.itemCreated();
				i++;
			}
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(this.iconString);
	}

	public ItemProton setHasShimmerEffect(boolean value) {
		this.shimmering = value;
		return this;
	}

	@Override
	public boolean hasEffect(ItemStack par1) {
		return this.shimmering;
	}

}
