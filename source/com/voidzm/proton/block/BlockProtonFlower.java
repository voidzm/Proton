/*******
>>> BlockProtonFlower.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
*******/

package com.voidzm.proton.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockProtonFlower extends BlockProton implements IPlantable {

	public String iconLocation;
	private EnumPlantType plantType;
	
	public BlockProtonFlower(int par1, String par2, EnumPlantType type) {
		super(par1, Material.plants);
		this.setTickRandomly(true);
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.iconLocation = par2;
		this.plantType = type;
	}
	
	public BlockProtonFlower(int par1) {
		this(par1, null, EnumPlantType.Plains);
	}
	
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
	}
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		this.checkFlowerChange(par1World, par2, par3, par4);
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		this.checkFlowerChange(par1World, par2, par3, par4);
	}

	protected final void checkFlowerChange(World par1World, int par2, int par3, int par4) {
		if(!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}
	
	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
		return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && (soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this));
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return 1;
	}
	
	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return this.plantType;
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(this.iconLocation);
	}
	
}
