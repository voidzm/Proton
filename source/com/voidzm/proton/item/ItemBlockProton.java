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

	private int blockID;

	public ItemBlockProton(int par1) {
		super(par1);
		this.blockID = par1 + 256;
		setHasSubtypes(true);
		setUnlocalizedName("itemblockproton");
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	} 

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		Block block = Block.blocksList[this.blockID];
		if(block == null) return null;
		if(block instanceof BlockProton) {
			BlockProton supercraftBlock = (BlockProton)block;
			ArrayList<String> names = supercraftBlock.fetchExternalNames();
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
		Block block = Block.blocksList[this.blockID];
		if(block == null) return null;
		return block.getIcon(0, par1);
	}

}
