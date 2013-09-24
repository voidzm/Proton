/*******
>>> BlockVapor.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.voidzm.proton.controller.FluidController;
import com.voidzm.proton.protocol.IRegisterable;
import com.voidzm.proton.util.RegisterData;

public class BlockVapor extends BlockFluidClassic implements IRegisterable {

	private Fluid blockFluid;

	protected Icon[] icon;

	private RegisterData rdata = new RegisterData();

	public BlockVapor(int id, Fluid fluid) {
		super(id, fluid, Material.water);
		blockFluid = fluid;
		rdata.internalName = "vapor";
		rdata.externalName = "Vapor";
		this.setCreativeTab(CreativeTabs.tabBlock);
		FluidController.vapor.setBlockID(this);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.icon = new Icon[] {par1IconRegister.registerIcon("proton:vapor"), par1IconRegister.registerIcon("proton:vapor_flow")};
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return par1 != 0 && par1 != 1 ? this.icon[1] : this.icon[0];
	}

	@Override
	public RegisterData getRegisterData() {
		return rdata;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return this.maxScaledLight;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par5Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)par5Entity;
			if(player.capabilities.isCreativeMode) return;
			player.addPotionEffect(new PotionEffect(14, 100, 0));
			player.addPotionEffect(new PotionEffect(15, 100, 0));
		}
	}

}
