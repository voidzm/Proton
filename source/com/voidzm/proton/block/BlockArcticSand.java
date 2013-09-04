/*******
>>> BlockArcticSand.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class BlockArcticSand extends BlockProton {

	public BlockArcticSand(int par1) {
		super(par1, Material.sand);
		this.setHardness(0.8F);
		this.setStepSound(soundSandFootstep);
		this.setInternalName("arcticsand");
		this.setExternalName("Arctic Sand");
		this.setCreativeTab(CreativeTabs.tabBlock);
		MinecraftForge.setBlockHarvestLevel(this, "shovel", 0);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("proton:arcticsand");
	}

}
