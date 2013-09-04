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

	private String[][] textureNames = new String[16][3];
	private Icon[][] textures = new Icon[16][3];
	private ArrayList<String> names = new ArrayList<String>();

	public int stonesRepresented = 0;

	private String[] standaloneTextureNames = new String[3];
	private Icon[] standaloneTextures = new Icon[3];

	public BlockProtonStone(int par1) {
		super(par1, Material.rock);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setInternalName("protonstone");
	}

	public BlockProtonStone(int par1, String internal, String external, String texture) {
		this(par1, internal, external, texture, texture, texture);
	}

	public BlockProtonStone(int par1, String internal, String external, String textureTop, String textureSide, String textureBottom) {
		super(par1, Material.rock);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10.0F);
		this.setInternalName(internal);
		this.setExternalName(external);
		this.standaloneTextureNames[0] = textureTop;
		this.standaloneTextureNames[1] = textureSide;
		this.standaloneTextureNames[2] = textureBottom;
	}

	@Override
	public void finalize() {
		this.makeMultiblock(names, ItemBlockProton.class);
	}

	public void addStone(String name, String topIcon, String sideIcon, String bottomIcon) {
		if(stonesRepresented >= 16) {
			System.out.println("Block " + name + " not registered: this ID already contains sixteen stones!");
		}
		this.names.add(name);
		textureNames[stonesRepresented][0] = "proton:" + topIcon;
		textureNames[stonesRepresented][1] = "proton:" + sideIcon;
		textureNames[stonesRepresented][2] = "proton:" + bottomIcon;
		stonesRepresented++;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		if(standaloneTextureNames[0] != null) {
			standaloneTextures[0] = par1IconRegister.registerIcon(standaloneTextureNames[0]);
			standaloneTextures[1] = par1IconRegister.registerIcon(standaloneTextureNames[1]);
			standaloneTextures[2] = par1IconRegister.registerIcon(standaloneTextureNames[2]);
		}
		for(int i = 0; i < stonesRepresented; i++) {
			textures[i][0] = par1IconRegister.registerIcon(textureNames[i][0]);
			textures[i][1] = par1IconRegister.registerIcon(textureNames[i][1]);
			textures[i][2] = par1IconRegister.registerIcon(textureNames[i][2]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		if(standaloneTextures[0] != null) {
			switch(side) {
				case 0:
					return standaloneTextures[2];
				case 1:
					return standaloneTextures[0];
				default:
					return standaloneTextures[1];
			}
		}
		else {
			switch(side) {
				case 0:
					return textures[meta][2];
				case 1:
					return textures[meta][0];
				default:
					return textures[meta][1];
			}
		}
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		if(standaloneTextureNames[0] != null) {
			super.getSubBlocks(par1, par2CreativeTabs, par3List);
			return;
		}
		for(int i = 0; i < stonesRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

}
