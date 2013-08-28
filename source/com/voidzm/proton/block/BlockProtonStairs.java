/*******
>>> BlockProtonStairs.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

import com.voidzm.proton.protocol.IRegisterable;
import com.voidzm.proton.util.RegisterData;

public class BlockProtonStairs extends BlockStairs implements IRegisterable {

	private RegisterData rdata = new RegisterData();
	
	public BlockProtonStairs(int par1, Block par2Block, int par3, String internal, String external) {
		super(par1, par2Block, par3);
		Block.useNeighborBrightness[par1] = true;
		this.setUnlocalizedName(internal);
		this.rdata.internalName = internal;
		this.rdata.externalName = external;
	}

	@Override
	public RegisterData getRegisterData() {
		return this.rdata;
	}
	
}
