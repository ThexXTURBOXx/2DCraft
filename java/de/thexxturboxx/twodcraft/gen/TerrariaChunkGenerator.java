package de.thexxturboxx.twodcraft.gen;

import java.util.Random;

import de.thexxturboxx.twodcraft.util.ReflectionHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import net.minecraftforge.event.ForgeEventFactory;

public class TerrariaChunkGenerator extends ChunkProviderOverworld {
	
	private World w;
	
	public TerrariaChunkGenerator(World w, long l, boolean b, String generatorOptions) {
		super(w, l, b, generatorOptions);
		this.w = w;
		disable("useStrongholds");
		disable("useVillages");
		disable("useMineShafts");
		disable("useTemples");
		disable("useMonuments");
		disable("useRavines");
		disable("useDungeons");
	}
	
	private void disable(String field) {
		setNewSettingValue(field, false);
	}
	
	private void setNewSettingValue(String field, Object value) {
		ChunkProviderSettings cps = (ChunkProviderSettings) ReflectionHelper.getObject(this, ChunkProviderOverworld.class, "settings");
		ReflectionHelper.set(cps, ChunkProviderSettings.class, field, value);
	}
	
	private Object get(String field) {
		return ReflectionHelper.getObject(this, ChunkProviderOverworld.class, field);
	}
	
	@Override
	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
		World worldObj = (World) get("worldObj");
		double[] depthBuffer = (double[]) get("depthBuffer");
		NoiseGeneratorPerlin surfaceNoise = (NoiseGeneratorPerlin) get("surfaceNoise");
		Random rand = (Random) get("rand");
		if (!ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, worldObj)) return;
        double d0 = 0.03125D;
        depthBuffer = surfaceNoise.getRegion(depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);
        
        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
            	Biome biome = biomesIn[j + i * 16];
            	biome.genTerrainBlocks(worldObj, rand, primer, x * 16 + i, z * 16 + j, depthBuffer[j + i * 16]);
            }
        }
        for(int ynew = 0; ynew <= 255; ynew++) {
        	for(int xnew = 0; xnew <= 15; xnew++) {
        		for(int znew = 0; znew <= 15; znew++) {
        			if(znew >= 2) {
                        primer.setBlockState(xnew, ynew, znew, Blocks.AIR.getDefaultState());
        			}
        		}
        	}
        }
	}
	
	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}
	
	@Override
	public Chunk provideChunk(int x, int z) {
		if(z != 0) {
			return new Chunk(w, x, z);
		} else {
			Chunk chunk = super.provideChunk(x, z);
			for(int ynew = 0; ynew <= 255; ynew++) {
	        	for(int xnew = 0; xnew <= 15; xnew++) {
	        		for(int znew = 0; znew <= 15; znew++) {
	        			if(znew >= 2 || znew < 0) {
	                        chunk.setBlockState(new BlockPos(x * 16 + xnew, ynew, z * 16 + znew), Blocks.AIR.getDefaultState());
	        			}
	        		}
	        	}
	        }
			return chunk;
		}
	}
	
	@Override
	public void populate(int x, int z) {
		super.populate(x, z);
		World w = (World) get("worldObj");
		for(int ynew = 0; ynew <= 255; ynew++) {
        	for(int xnew = 0; xnew <= 15; xnew++) {
        		for(int znew = 0; znew <= 15; znew++) {
        			if(znew >= 2 || znew < 0) {
                        w.setBlockState(new BlockPos(x * 16 + xnew, ynew, z * 16 + znew), Blocks.AIR.getDefaultState());
        			}
        		}
        	}
        }
	}
	
	@Override
	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		super.setBlocksInChunk(x, z, primer);
		for(int ynew = 0; ynew <= 255; ynew++) {
        	for(int xnew = 0; xnew <= 15; xnew++) {
        		for(int znew = 0; znew <= 15; znew++) {
        			if(znew >= 2) {
                        primer.setBlockState(xnew, ynew, znew, Blocks.AIR.getDefaultState());
        			}
        		}
        	}
        }
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}
	
}