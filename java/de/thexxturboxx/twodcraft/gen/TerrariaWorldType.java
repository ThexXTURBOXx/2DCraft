package de.thexxturboxx.twodcraft.gen;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.*;
import net.minecraft.world.chunk.IChunkGenerator;

public class TerrariaWorldType extends WorldType {
	
	public TerrariaWorldType() {
		super("2D");
	}
	
	@Override
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new TerrariaChunkGenerator(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
	}
	
	@Override
	public int getSpawnFuzz(WorldServer world, MinecraftServer server) {
		return 1;
	}
	
}