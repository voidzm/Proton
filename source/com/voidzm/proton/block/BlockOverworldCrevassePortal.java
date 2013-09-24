/*******
>>> BlockOverworldCrevassePortal.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.voidzm.proton.dimension.TeleporterSurfaceCrevasse;

public class BlockOverworldCrevassePortal extends BlockProton {

	public BlockOverworldCrevassePortal(int par1) {
		super(par1, Material.portal);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.4F, 1.0F);
		this.setBlockUnbreakable();
		this.setStepSound(soundGlassFootstep);
		this.setLightValue(0.5F);
		this.setInternalName("overworldcrevasseportal");
		this.setExternalName("Crevasse Portal");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("proton:overworldcrevasseportal");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if(par5 == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
		if(!par1World.isRemote && entity != null && entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)entity;
			int currentDim = player.dimension;
			if(currentDim == 0) {
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, -2, new TeleporterSurfaceCrevasse(player.mcServer.worldServerForDimension(-2)));
			}
		}
	}
}
