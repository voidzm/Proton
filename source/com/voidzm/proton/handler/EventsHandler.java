/*******
>>> ProtonEventHandler.java <<<
>>> Proton <<<
>>> Copyright voidzm 2013 <<<
 *******/

package com.voidzm.proton.handler;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import com.voidzm.proton.biome.BiomeProton;
import com.voidzm.proton.block.BlockProtonSapling;

public class EventsHandler {

	private static EventsHandler instance;

	public static EventsHandler instance() {
		if(instance == null) {
			instance = new EventsHandler();
		}
		return instance;
	}

	public static void init() {
		MinecraftForge.EVENT_BUS.register(instance());
		MinecraftForge.TERRAIN_GEN_BUS.register(instance());
	}

	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event) {
		if(Block.blocksList[event.ID] instanceof BlockProtonSapling) {
			if(event.world.rand.nextFloat() < 0.45D) {
				if(!event.world.isRemote) {
					BlockProtonSapling.markOrGrowMarked(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
			event.setResult(Result.ALLOW);
		}
	}

	@ForgeSubscribe
	public void onPopulateChunk(PopulateChunkEvent.Populate event) {
		if(event.type != PopulateChunkEvent.Populate.EventType.LAKE) return;
		BiomeGenBase biome = event.world.getBiomeGenForCoords(event.chunkX << 4, event.chunkZ << 4);
		if(biome instanceof BiomeProton) {
			if(((BiomeProton)biome).doLakeGen == false) {
				event.setResult(Result.DENY);
			}
		}
	}
}
