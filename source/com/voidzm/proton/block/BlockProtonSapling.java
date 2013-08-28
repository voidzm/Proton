/*******
>>> BlockProtonSapling.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.voidzm.proton.gen.WorldGenProtonTree;
import com.voidzm.proton.item.ItemBlockProton;

public class BlockProtonSapling extends BlockProtonFlower {

	private String[] textureNames = new String[4];
	private Icon[] textures = new Icon[4];
	private ArrayList<String> names = new ArrayList<String>();
	private WorldGenProtonTree[] generators = new WorldGenProtonTree[4];

	public int saplingsRepresented = 0;

	public BlockProtonSapling(int par1) {
		super(par1);
		float var3 = 0.4F;
		this.setTickRandomly(true);
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.0F);
		this.setStepSound(Block.soundGrassFootstep);
		this.setInternalName("protonsapling");
	}

	@Override
	public void finalize() {
		this.makeMultiblock(names, ItemBlockProton.class);
	}

	public void addSapling(String name, String icon, WorldGenProtonTree gen) {
		if(saplingsRepresented >= 4) {
			System.out.println("Block " + name + " not registered: this ID already contains four saplings!");
		}
		this.names.add(name + " Sapling");
		textureNames[saplingsRepresented] = "proton:sapling" + icon;
		generators[saplingsRepresented] = gen;
		saplingsRepresented++;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!par1World.isRemote) {
			super.updateTick(par1World, par2, par3, par4, par5Random);
			if(par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
				int var6 = par1World.getBlockMetadata(par2, par3, par4);
				if((var6 & 8) == 0) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 | 8, 3);
				}
				else {
					this.growTree(par1World, par2, par3, par4, par5Random);
				}
			}
		}
	}

	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;
		WorldGenerator treeGenerator = null;
		int metadata = par1World.getBlockMetadata(par2, par3, par4) & 3;
		if(generators[metadata] != null) {
			generators[metadata].generate(par1World, par5Random, par2, par3, par4, false);
		}
	}

	public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
		return par1World.getBlockId(par2, par3, par4) == this.blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < saplingsRepresented; i++) {
			textures[i] = par1IconRegister.registerIcon(textureNames[i]);
		}
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < saplingsRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta & 3];
	}

	public static void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		if((l & 8) == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, l | 8, 4);
		}
		else {
			((BlockProtonSapling)Block.blocksList[par1World.getBlockId(par2, par3, par4)]).growTree(par1World, par2, par3, par4, par5Random);
		}
	}

}
