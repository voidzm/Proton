/*******
>>> BlockProtonSlab.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.proton.item.ItemBlockProton;
import com.voidzm.proton.protocol.IRegisterable;
import com.voidzm.proton.util.RegisterData;

public class BlockProtonSlab extends BlockHalfSlab implements IRegisterable {

	private String[] textureNames = new String[8];
	private Icon[] textures = new Icon[8];
	private ArrayList<String> names = new ArrayList<String>();
	private RegisterData rdata = new RegisterData();

	public int slabsRepresented = 0;

	private String internalNameString;

	public BlockProtonSlab(int par1, Material par2, String internal) {
		super(par1, false, par2);
		this.setCreativeTab(CreativeTabs.tabBlock);
		Block.useNeighborBrightness[par1] = true;
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		if(par2 == Material.wood) this.setStepSound(Block.soundWoodFootstep);
		else this.setStepSound(Block.soundStoneFootstep);
		this.setUnlocalizedName("protonslab");
		internalNameString = internal;
	}

	@Override
	public void finalize() {
		rdata.internalName = internalNameString;
		rdata.isMulti = true;
		rdata.externalNames = this.names;
		rdata.itemBlockClass = ItemBlockProton.class;
	}

	public void addSlab(String name, String icon) {
		if(slabsRepresented >= 8) {
			System.out.println("Block " + name + " not registered: this ID already contains eight slabs!");
		}
		this.names.add(name);
		textureNames[slabsRepresented] = "proton:" + icon;
		slabsRepresented++;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 2, par1 & 7);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < slabsRepresented; i++) {
			textures[i] = par1IconRegister.registerIcon(textureNames[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta & 7];
	}

	@Override
	public String getFullSlabName(int par1) {
		if(par1 < 0 || par1 >= names.size()) {
			par1 = 0;
		}
		return super.getUnlocalizedName() + "." + names.get(par1);
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < slabsRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}

}
