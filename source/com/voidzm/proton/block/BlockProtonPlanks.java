/*******
>>> BlockProtonPlanks.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.proton.item.ItemBlockProton;

public class BlockProtonPlanks extends BlockProton {

	private String[] textureNames = new String[16];
	private Icon[] textures = new Icon[16];
	private ArrayList<String> names = new ArrayList<String>();

	public int planksRepresented = 0;

	public BlockProtonPlanks(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setInternalName("protonplanks");
	}
	
	public void finalize() {
		this.makeMultiblock(names, ItemBlockProton.class);
	}
	
	public void addPlanks(String name, String icon) {
		if(planksRepresented >= 16) {
			System.out.println("Block " + name + " not registered: this ID already contains sixteen planks!");
		}
		this.names.add(name + " Wood Planks");
		textureNames[planksRepresented] = "proton:planks" + icon;
		planksRepresented++;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < planksRepresented; i++) {
			textures[i] = par1IconRegister.registerIcon(textureNames[i]);
			textures[i] = par1IconRegister.registerIcon(textureNames[i]);
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta];
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1;
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < planksRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
}
