/*******
>>> ItemBlockProton.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.voidzm.proton.block.BlockProton;
import com.voidzm.proton.protocol.IRegisterable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockProton extends ItemBlock {

	private Block block;

	public ItemBlockProton(int par1) {
		super(par1);
		this.block = Block.blocksList[this.getBlockID()];
		setHasSubtypes(true);
		setUnlocalizedName("itemblockproton");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		if(block == null) return null;
		if(block instanceof BlockProton) {
			BlockProton protonBlock = (BlockProton)block;
			ArrayList<String> names = protonBlock.fetchExternalNames();
			return this.getUnlocalizedName() + "." + names.get(itemstack.getItemDamage());
		}
		if(block instanceof IRegisterable) {
			ArrayList<String> names = ((IRegisterable)block).getRegisterData().externalNames;
			return this.getUnlocalizedName() + "." + names.get(itemstack.getItemDamage());
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		if(block == null) return null;
		return block.getIcon(0, par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return this.block.getRenderColor(par1ItemStack.getItemDamage());
	}

}
