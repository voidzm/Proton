/*******
>>> BlockProtonLog.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.voidzm.proton.item.ItemBlockProton;

public class BlockProtonLog extends BlockProton {

	private String[][] textureNames = new String[4][2];
	private Icon[][] textures = new Icon[4][2];
	private ArrayList<String> names = new ArrayList<String>();

	public int logsRepresented = 0;
	
	public BlockProtonLog(int par1) {
		super(par1, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setStepSound(Block.soundWoodFootstep);
		this.setInternalName("protonlog");
	}
	
	public void finalize() {
		this.makeMultiblock(names, ItemBlockProton.class);
	}

	public void addLog(String name, String icon) {
		if(logsRepresented >= 4) {
			System.out.println("Block " + name + " not registered: this ID already contains four logs!");
		}
		this.names.add(name + " Wood");
		textureNames[logsRepresented][0] = "proton:log" + icon + "_side";
		textureNames[logsRepresented][1] = "proton:log" + icon + "_end";
		logsRepresented++;
	}
	
	@Override
	public int getRenderType() {
		return 31;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		byte var7 = 4;
		int var8 = var7 + 1;
		if(par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
			for(int var9 = -var7; var9 <= var7; ++var9) {
				for(int var10 = -var7; var10 <= var7; ++var10) {
					for(int var11 = -var7; var11 <= var7; ++var11) {
						int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
						if(Block.blocksList[var12] != null) {
							Block.blocksList[var12].beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
						}
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
		int var10 = par9 & 3;
		byte var11 = 0;
		switch(par5) {
			case 0:
			case 1:
				var11 = 0;
				break;
			case 2:
			case 3:
				var11 = 8;
				break;
			case 4:
			case 5:
				var11 = 4;
		}
		return var10 | var11;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < logsRepresented; i++) {
			textures[i][0] = par1IconRegister.registerIcon(textureNames[i][0]);
			textures[i][1] = par1IconRegister.registerIcon(textureNames[i][1]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta) {
		int logCode = meta & 3;
		int rotationCode = meta & 12;
		Icon logSide, logEnd;
		logSide = textures[logCode][0];
		logEnd = textures[logCode][1];
		if(rotationCode == 0) {
			Icon val = null;
			switch(side) {
				case 0:
				case 1:
					val = logEnd;
					break;
				case 2:
				case 3:
					val = logSide;
					break;
				case 4:
				case 5:
					val = logSide;
					break;
			}
			return val;
		}
		else if(rotationCode == 4) {
			Icon val = null;
			switch(side) {
				case 0:
				case 1:
					val = logSide;
					break;
				case 2:
				case 3:
					val = logSide;
					break;
				case 4:
				case 5:
					val = logEnd;
					break;
			}
			return val;
		}
		else if(rotationCode == 8) {
			Icon val = null;
			switch(side) {
				case 0:
				case 1:
					val = logSide;
					break;
				case 2:
				case 3:
					val = logEnd;
					break;
				case 4:
				case 5:
					val = logSide;
					break;
			}
			return val;
		}
		else {
			Icon val = null;
			switch(side) {
				case 0:
				case 1:
					val = logSide;
					break;
				case 2:
				case 3:
					val = logSide;
					break;
				case 4:
				case 5:
					val = logSide;
					break;
			}
			return val;
		}
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	public static int limitToValidMetadata(int par0){
		return par0 & 3;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < logsRepresented; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

}
