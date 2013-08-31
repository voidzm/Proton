/*******
>>> BlockProtonStone.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.proton.item.ItemBlockProton;

public class BlockProtonStone extends BlockProton {

	private String[] textureNames = new String[16];
	private Icon[] textures = new Icon[16];
	private ArrayList<String> names = new ArrayList<String>();

	public int stonesRepresented = 0;

	private String standaloneTextureName = null;
	private Icon standaloneTexture = null;

	public BlockProtonStone(int par1) {
		super(par1, Material.rock);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setInternalName("protonstone");
	}

	public BlockProtonStone(int par1, String internal, String external, String texture) {
		super(par1, Material.rock);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setInternalName(internal);
		this.setExternalName(external);
		this.standaloneTextureName = texture;
	}

	@Override
	public void finalize() {
		this.makeMultiblock(names, ItemBlockProton.class);
	}

	public void addStone(String name, String icon) {
		if(stonesRepresented >= 16) {
			System.out.println("Block " + name + " not registered: this ID already contains sixteen stones!");
		}
		this.names.add(name);
		textureNames[stonesRepresented] = "proton:" + icon;
		stonesRepresented++;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if(standaloneTextureName != null) {
			standaloneTexture = par1IconRegister.registerIcon(standaloneTextureName);
		}
		for(int i = 0; i < stonesRepresented; i++) {
			textures[i] = par1IconRegister.registerIcon(textureNames[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if(standaloneTexture != null) return standaloneTexture;
		else return textures[meta];
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		if(standaloneTextureName != null) {
			super.getSubBlocks(par1, par2CreativeTabs, par3List);
			return;
		}
		for(int i = 0; i < stonesRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

}
