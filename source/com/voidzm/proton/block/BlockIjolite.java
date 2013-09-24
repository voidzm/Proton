/*******
>>> BlockIjolite.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BlockIjolite extends BlockProton {

	public BlockIjolite(int par1) {
		super(par1, Material.rock);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setStepSound(soundStoneFootstep);
		this.setInternalName("ijolite");
		this.setExternalName("Ijolite");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("proton:ijolite");
	}

}
