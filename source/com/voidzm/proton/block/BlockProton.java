/*******
>>> BlockProton.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.voidzm.proton.protocol.IRegisterable;
import com.voidzm.proton.util.RegisterData;
import com.voidzm.proton.util.Startup;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockProton extends Block {

	private String internalName;
	private String externalName;
	
	public boolean isMultiblock = false;
	
	private ArrayList<String> externalNames = null;
	private Class<? extends ItemBlock> itemClass = null;
	
	private boolean dragonDestroys = true;
	
	public BlockProton(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	public BlockProton setInternalName(String newInternalName) {
		this.internalName = newInternalName;
		this.setUnlocalizedName(newInternalName);
		return this;
	}
	
	public BlockProton setExternalName(String newExternalName) {
		this.externalName = newExternalName;
		return this;
	}
	
	public String internalName() {
		return this.internalName;
	}
	
	public String externalName() {
		return this.externalName;
	}
	
	public void makeMultiblock(ArrayList<String> names, Class<? extends ItemBlock> itemBlockClass) {
		this.isMultiblock = true;
		this.externalNames = names;
		this.itemClass = itemBlockClass;
	}
	
	public ArrayList<String> fetchExternalNames() {
		return this.externalNames;
	}
	
	public BlockProton register() {
		if(!this.isMultiblock) {
			GameRegistry.registerBlock(this, this.internalName);
			LanguageRegistry.addName(this, this.externalName);
			Startup.blockCreated();
		}
		else {
			GameRegistry.registerBlock(this, this.itemClass, this.internalName);
			int i = 0;
			for(String name : externalNames) {
				ItemStack stack = new ItemStack(this, 1, i);
				LanguageRegistry.addName(stack, name);
				Startup.blockCreated();
				i++;
			}
		}
		return this;
	}
	
	public static void register(Block block, RegisterData data) {
		if(!(block instanceof IRegisterable)) return;
		if(!data.isMulti) {
			GameRegistry.registerBlock(block, data.internalName);
			LanguageRegistry.addName(block, data.externalName);
			Startup.blockCreated();
		}
		else {
			GameRegistry.registerBlock(block, data.itemBlockClass, data.internalName);
			int i = 0;
			for(String name : data.externalNames) {
				ItemStack stack = new ItemStack(block, 1, i);
				LanguageRegistry.addName(stack, name);
				Startup.blockCreated();
				i++;
			}
		}
	}
	
	public BlockProton makeDragonUnbreakable() {
		this.dragonDestroys = false;
		return this;
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z) {
		return this.dragonDestroys;
	}
	
}
