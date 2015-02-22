package hu.hundevelopers.elysium.world.gen;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE;
import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenCorruption;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenDesert;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenForest;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenPlain;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenCorruptFostimber;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenCrystalSpikes;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDarkFostimber;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenFostimber;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenLakes;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenSand;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class ChunkProviderElysium implements IChunkProvider
{
	/** RNG. */
    private Random rand;
    private NoiseGeneratorOctaves field_147431_j;
    private NoiseGeneratorOctaves field_147432_k;
    private NoiseGeneratorOctaves field_147429_l;
    private NoiseGeneratorPerlin perlinNoise;
    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen5;
    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    /** Reference to the World object. */
    private World worldObj;
    /** are map structures going to be generated (e.g. strongholds) */
    private final boolean mapFeaturesEnabled;
    private WorldType field_147435_p;
    private final double[] field_147434_q;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    /** Holds Stronghold Generator */
//    private MapGenStronghold strongholdGenerator/* = new MapGenStronghold()*/;
    /** Holds Village Generator */
//    private MapGenVillage villageGenerator = new MapGenVillage();
    /** Holds Mineshaft Generator */
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    /** Holds ravine generator */
    private MapGenBase ravineGenerator = new MapGenRavine();
    /** The biomes that are used to generate the chunk */
    private BiomeGenBase[] biomesForGeneration;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    int[][] field_73219_j = new int[32][32];


    ElysiumGenCrystalSpikes crystalEnergyGen = new ElysiumGenCrystalSpikes(0);
    ElysiumGenCrystalSpikes crystalCorruptGen = new ElysiumGenCrystalSpikes(1);
	ElysiumGenLakes lakegenerator = new ElysiumGenLakes(Elysium.blockElysiumWater);
	ElysiumGenSand sandgenerator = new ElysiumGenSand(Elysium.blockSand, 7);
	ElysiumGenSand riltgenerator = new ElysiumGenSand(Elysium.blockRilt, 3);
	ElysiumGenFostimber treegenerator = new ElysiumGenFostimber(Elysium.blockLeaves, Elysium.blockLog, false);
	ElysiumGenCorruptFostimber corrupttreegenerator = new ElysiumGenCorruptFostimber(Elysium.blockLog, 2);
	ElysiumGenDarkFostimber darktreegenerator = new ElysiumGenDarkFostimber(Elysium.blockLeaves, Elysium.blockLog, 1, 1, false, false);
	ElysiumGenDarkFostimber darkcorruptedtreegenerator = new ElysiumGenDarkFostimber(Elysium.blockLeaves, Elysium.blockLog, 1, 1, false, true);

	WorldGenFlowers flowergenerator = new WorldGenFlowers(Elysium.blockFlower);
	
    {
    	flowergenerator.func_150550_a(Elysium.blockFlower, 0);
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, InitMapGenEvent.EventType.CAVE);
//        strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGenerator, STRONGHOLD);
//        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(villageGenerator, VILLAGE);
//        mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGenerator, MINESHAFT);
//        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, InitMapGenEvent.EventType.SCATTERED_FEATURE);
//        ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
    }    

    public ChunkProviderElysium(World par1World, long par2, boolean par4)
    {
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.field_147435_p = par1World.getWorldInfo().getTerrainType();
        this.rand = new Random(par2);
        this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
        this.perlinNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147434_q = new double[825];
        this.parabolicField = new float[25];

        for (int j = -2; j <= 2; ++j)
        {
            for (int k = -2; k <= 2; ++k)
            {
                float f = 10.0F / MathHelper.sqrt_float((float)(j * j + k * k) + 0.2F);
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

        NoiseGenerator[] noiseGens = {field_147431_j, field_147432_k, field_147429_l, perlinNoise, noiseGen5, noiseGen6, mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators(par1World, this.rand, noiseGens);
        this.field_147431_j = (NoiseGeneratorOctaves)noiseGens[0];
        this.field_147432_k = (NoiseGeneratorOctaves)noiseGens[1];
        this.field_147429_l = (NoiseGeneratorOctaves)noiseGens[2];
        this.perlinNoise = (NoiseGeneratorPerlin)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }

    /**
	 * Generates the shape of the terrain for the chunk though its all stone though the water is frozen if the
	 * temperature is low enough
	 */
    public void generateTerrain(int p_147424_1_, int p_147424_2_, Block[] p_147424_3_)
    {
        byte seeLevel = 63;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, p_147424_1_ * 4 - 2, p_147424_2_ * 4 - 2, 10, 10);
        this.func_147423_a(p_147424_1_ * 4, 0, p_147424_2_ * 4);

        for (int k = 0; k < 4; ++k)
        {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1)
            {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2)
                {
                    double d0 = 0.125D;
                    double d1 = this.field_147434_q[k1 + k2];
                    double d2 = this.field_147434_q[l1 + k2];
                    double d3 = this.field_147434_q[i2 + k2];
                    double d4 = this.field_147434_q[j2 + k2];
                    double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3)
                        {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int k3 = 0; k3 < 4; ++k3)
                            {
                                if ((d15 += d16) > 0.0D)
                                {
                                    p_147424_3_[j3 += short1] = Elysium.blockPalestone;
                                }
                                else if (k2 * 8 + l2 < seeLevel)
                                {
                                    p_147424_3_[j3 += short1] = Elysium.blockElysiumWater;
                                }
                                else
                                {
                                    p_147424_3_[j3 += short1] = null;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] blockArray, byte[] mapArray, BiomeGenBase[] paramBiomeGenBase)
    {
    	
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, blockArray, mapArray, paramBiomeGenBase);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

        double d0 = 0.03125D;
        this.stoneNoise = this.perlinNoise.func_151599_a(this.stoneNoise, (double)(chunkX * 16), (double)(chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        boolean genMazeInThisChunk = this.rand.nextInt(Configs.mazeRoomRarity) != 0;//if normal maze
    	
        Maze maze = null;
        if(genMazeInThisChunk)//if normal maze
        {
        	maze = new Maze(this.rand);
        
        	maze.generate();
        }
        
    	for (int z = 0; z < 16; ++z)
        {
            for (int x = 0; x < 16; ++x)
            {
            	
                BiomeGenBase biomegenbase = paramBiomeGenBase[x + z * 16];
                //biomegenbase.genTerrainBlocks(this.worldObj, this.rand, blockArray, mapArray, par1 * 16 + z, par2 * 16 + x, this.stoneNoise[x + z * 16]);
    //										World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_
                //
                
                Block topBlock = biomegenbase.topBlock;
                byte b0 = (byte)(biomegenbase.field_150604_aj & 255);
                Block fillerBlock = biomegenbase.fillerBlock;
                int k = -1;
                int l = (int)(this.stoneNoise[x + z * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int i1 = chunkX * 16 + z & 15;
                int j1 = chunkZ * 16 + x & 15;
                int k1 = blockArray.length / 256;

                for (int y = 255; y >= 0; --y)
                {
                    int index = (j1 * 16 + i1) * k1 + y;

                    if (y < Configs.labyrinthBottom)
                    {
                        blockArray[index] = Blocks.lava;//TODO: magma
                    }
                    else
                    {
                        Block block2 = blockArray[index];

                        if (block2 != null && block2.getMaterial() != Material.air)
                        {
                            if (block2 == Elysium.blockPalestone)
                            {
                                if (k == -1)
                                {
                                    if (l <= 0)
                                    {
                                        topBlock = null;
                                        b0 = 0;
                                        fillerBlock = Elysium.blockPalestone;
                                    }
                                    else if (y >= 59 && y <= 64)
                                    {
                                        topBlock = biomegenbase.topBlock;
                                        b0 = (byte)(biomegenbase.field_150604_aj & 255);
                                        fillerBlock = biomegenbase.fillerBlock;
                                    }

                                    if (y < 63 && (topBlock == null || topBlock.getMaterial() == Material.air))
                                    {
                                        if (biomegenbase.getFloatTemperature(chunkX * 16 + z, y, chunkZ * 16 + x) < 0.15F)
                                        {
                                            topBlock = Blocks.ice;
                                            b0 = 0;
                                        }
                                        else
                                        {
                                            topBlock = Elysium.blockElysiumWater;
                                            b0 = 0;
                                        }
                                    }

                                    k = l;

                                    if (y >= 62)
                                    {
                                        blockArray[index] = topBlock;
                                        mapArray[index] = b0;
                                    }
                                    else if (y < 56 - l)
                                    {
                                        topBlock = null;
                                        fillerBlock = Elysium.blockPalestone;
                                        blockArray[index] = Elysium.blockSand;//gravel
                                    }
                                    else
                                    {
                                        blockArray[index] = fillerBlock;
                                    }
                                }
                                else if (k > 0)
                                {
                                    --k;
                                    blockArray[index] = fillerBlock;
                                }
                            }
                        }
                        else
                        {
                            k = -1;
                        }
                        
                        //maze start
            			if(y <= Configs.labyrinthTop)
                    	{
                			if(y < Configs.labyrinthTop && y > Configs.labyrinthBottom)
	                    	{
	                    		if(!genMazeInThisChunk)
	                    		{
	                    			if(x == 0 || x == 15 || z == 0 || z == 15)
	                    			{
	                    				if((x == 0 || x == 15) && (z == 8 || z == 7) || (z == 0 || z == 15) && (x == 8 || x == 7))

		                    				blockArray[index] = null;
	                    				else
	                    				{
		                    					
	                    					if(y == Configs.labyrinthBottom + 2)
		    	            					blockArray[index] = Configs.labyrinthLamp;
		    	                    		else
		    	                    		{
		    	            					if(this.rand.nextInt(3) < 2)
		    	            						blockArray[index] = Elysium.blockQuartzBlock;
		    	            					else
		    	            						blockArray[index] = Configs.labyrinthWall;
		    	                    		}
                						}
	                    			}
	                    			else
	                    				blockArray[index] = null;
	                    			
	                    				
	                    		} else if((x % 3 == 1 || x % 3 == 2) && (z % 3 == 1 || z % 3 == 2))
	                    		{
	                    			blockArray[index] = null;

	                    		} else if((x == 0 || x == 15) && (z == 8 || z == 7) || (z == 0 || z == 15) && (x == 8 || x == 7))
	                    		{
	                    			blockArray[index] = null;
	                    		} else if(y == Configs.labyrinthBottom + 2)
	                    		{
	            					blockArray[index] = Configs.labyrinthLamp;
	                    		} else {

	            					if(this.rand.nextInt(3) < 2 && (biomegenbase instanceof ElysiumBiomeGenCorruption || biomegenbase instanceof ElysiumBiomeGenDesert))
	            						blockArray[index] = Elysium.blockQuartzBlock;
	            					else
	            						blockArray[index] = Configs.labyrinthWall;
	                    		}
	                    	} else {

            					if(this.rand.nextInt(3) < 2 && (biomegenbase instanceof ElysiumBiomeGenCorruption || biomegenbase instanceof ElysiumBiomeGenDesert))
            						blockArray[index] = Elysium.blockQuartzBlock;
            					else
            						blockArray[index] = Configs.labyrinthWall;
	                    	}
                    	}
            			
                        //maze end
                    }
                }
            }
        }
    	
    	if(genMazeInThisChunk)
    	{
    		//for y 3
    		for(int z = 0; z < 5; z++)
    		{
        		for(int x = 0; x < 5; x++)
        		{
        			//walls
        			if(x < 4)
        			{
        				if(!maze.walls[z * 4 + x])
        				{
        					for(int y = 1; y <= 3; y++)
        					{
        						setBlock(chunkX, chunkZ, (x+1) * 3, Configs.labyrinthBottom + y, z*3+1, blockArray, null);
        						setBlock(chunkX, chunkZ, (x+1) * 3, Configs.labyrinthBottom + y, z*3+2, blockArray, null);
        					}
        				}
        			}
        			
        			//bottom
        			if(z < 4)
        			{
        				if(!maze.bottom[z * 5 + x])
        				{
        					for(int y = 1; y <= 3; y++)
        					{
        						setBlock(chunkX, chunkZ, x * 3+1, Configs.labyrinthBottom + y, (z+1)*3, blockArray, null);
        						setBlock(chunkX, chunkZ, x * 3+2, Configs.labyrinthBottom + y, (z+1)*3, blockArray, null);
        					}
        				}
        			}
        		}
    		}
    		maze = null;
    	} else
        {//custom room
        	//event handler, api
        	if(rand.nextInt(3) != 0)
        	{
        		generateEntrance(chunkX, chunkZ, blockArray);
        	} else {
        		generateTreasure(chunkX, chunkZ, blockArray);
        	}
        }
    }

    private void generateEntrance(int chunkX, int chunkZ, Block[] blockArray)
    {
		setBlock(chunkX, chunkZ, 15/2, Configs.labyrinthTop, 15/2, blockArray, Blocks.trapdoor);
		setBlock(chunkX, chunkZ, 15/2+1, Configs.labyrinthTop, 15/2, blockArray, Blocks.trapdoor);
		setBlock(chunkX, chunkZ, 15/2, Configs.labyrinthTop, 15/2+1, blockArray, Blocks.trapdoor);
		setBlock(chunkX, chunkZ, 15/2+1, Configs.labyrinthTop, 15/2+1, blockArray, Blocks.trapdoor);
	}

	private void generateTreasure(int chunkX, int chunkZ, Block[] blockArray)
	{
		setBlock(chunkX, chunkZ, 15/2, Configs.labyrinthBottom+1, 15/2, blockArray, Blocks.chest);
		setBlock(chunkX, chunkZ, 15/2+1, Configs.labyrinthBottom+1, 15/2, blockArray, Blocks.chest);

	}

	private void setBlock(int chunkX, int chunkZ, int x, int y, int z, Block[] blockArray, Block block)
    {
    	 int i1 = chunkX * 16 + z & 15;
         int j1 = chunkZ * 16 + x & 15;
         int k1 = blockArray.length / 256;

         int index = (j1 * 16 + i1) * k1 + y;
         
         blockArray[index] = block;
	}

	/**
     * loads or generates the chunk at the chunk location specified
     */
    @Override
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    @Override
    public Chunk provideChunk(int chunkX, int chunkY)
    {
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkY * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
        this.generateTerrain(chunkX, chunkY, ablock);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkY * 16, 16, 16);
        this.replaceBlocksForBiome(chunkX, chunkY, ablock, abyte, this.biomesForGeneration);
        this.caveGenerator.func_151539_a(this, this.worldObj, chunkX, chunkY, ablock);
        this.ravineGenerator.func_151539_a(this, this.worldObj, chunkX, chunkY, ablock);

//        if (this.mapFeaturesEnabled)
//        {
//            this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
//            this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
//            this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, ablock);
//            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, chunkX, chunkY, ablock);
//        }

        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, chunkX, chunkY);
        byte[] abyte1 = chunk.getBiomeArray();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
    {
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
        this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < 5; ++j1)
        {
            for (int k1 = 0; k1 < 5; ++k1)
            {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.rootHeight;
                        float f4 = biomegenbase1.heightVariation;

                        if (this.field_147435_p == WorldType.AMPLIFIED && f3 > 0.0F)
                        {
                            f3 = 1.0F + f3 * 2.0F;
                            f4 = 1.0F + f4 * 4.0F;
                        }

                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

                        if (biomegenbase1.rootHeight > biomegenbase.rootHeight)
                        {
                            f5 /= 2.0F;
                        }

                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double d12 = this.field_147426_g[i1] / 8000.0D;

                if (d12 < 0.0D)
                {
                    d12 = -d12 * 0.3D;
                }

                d12 = d12 * 3.0D - 2.0D;

                if (d12 < 0.0D)
                {
                    d12 /= 2.0D;

                    if (d12 < -1.0D)
                    {
                        d12 = -1.0D;
                    }

                    d12 /= 1.4D;
                    d12 /= 2.0D;
                }
                else
                {
                    if (d12 > 1.0D)
                    {
                        d12 = 1.0D;
                    }

                    d12 /= 8.0D;
                }

                ++i1;
                double d13 = (double)f1;
                double d14 = (double)f;
                d13 += d12 * 0.2D;
                d13 = d13 * 8.5D / 8.0D;
                double d5 = 8.5D + d13 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2)
                {
                    double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / d14;

                    if (d6 < 0.0D)
                    {
                        d6 *= 4.0D;
                    }

                    double d7 = this.field_147428_e[l] / 512.0D;
                    double d8 = this.field_147425_f[l] / 512.0D;
                    double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

                    if (j2 > 29)
                    {
                        double d11 = (double)((float)(j2 - 29) / 3.0F);
                        d10 = d10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    @Override
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

	/**
	 * Populates chunk with ores etc etc
	 */
	@Override
	public void populate(IChunkProvider par1IChunkProvider, int chunkX, int chunkZ)
	{
		BlockSand.fallInstantly = true;
		int k = chunkX * 16;
		int l = chunkZ * 16;
		
		Block temp;
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				for(int y = Configs.labyrinthBottom; y <= Configs.labyrinthTop; y++)
				{
					temp = this.worldObj.getBlock(k + i, y, l + j);
//					if(temp != null && temp instanceof IHeatable)
//					{
//						this.worldObj.setBlockMetadataWithNotify(k + i, y, l + j, 7, 2);
//					} else
						if(temp == Blocks.chest)
					{
						TileEntityChest tileentitychest = (TileEntityChest)worldObj.getTileEntity(k + i, y, l + j);

                        if (tileentitychest != null)
                        {
                            ChestGenHooks loot = ChestGenHooks.getInfo(DUNGEON_CHEST);
                            WeightedRandomChestContent.generateChestContents(rand, loot.getItems(rand), tileentitychest, loot.getCount(this.rand));
                            
                        }
                        if(worldObj.blockExists(k + i, y, l + j))
                        	worldObj.setTileEntity(k + i, y, l + j, tileentitychest);
					} else if(worldObj.getBiomeGenForCoords(chunkX * 16, chunkZ * 16) instanceof ElysiumBiomeGenCorruption || worldObj.getBiomeGenForCoords(chunkX * 16, chunkZ * 16) instanceof ElysiumBiomeGenDesert)
					{
						if(temp == Elysium.blockEnergyCrystal)
						{
	                        if(worldObj.blockExists(k + i, y, l + j))
	                        	this.worldObj.setBlockMetadataWithNotify(k + i, y, l + j, 1, 2);
						}
					}
				}
			}
		}
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)chunkX * i1 + (long)chunkZ * j1 ^ this.worldObj.getSeed());
		boolean flag = false;

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(par1IChunkProvider, worldObj, rand, chunkX, chunkZ, flag));

		int i2;
		boolean doGen = TerrainGen.populate(par1IChunkProvider, worldObj, rand, chunkX, chunkZ, flag, ICE);

		//TODO structures
		if(biomegenbase != Elysium.biomeOcean && biomegenbase != Elysium.biomeDeepOcean && biomegenbase != Elysium.biomeRiver)
		{
			this.lakegenerator.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), rand.nextInt(128), l+this.rand.nextInt(16));
			this.sandgenerator.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), 0, l+this.rand.nextInt(16));
			this.riltgenerator.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), 0, l+this.rand.nextInt(16));
								
			if(biomegenbase instanceof ElysiumBiomeGenCorruption)
			{
				if(this.rand.nextInt(3) == 1)
					this.crystalCorruptGen.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), rand.nextInt(128), l+this.rand.nextInt(16));

				if(this.rand.nextInt(4) == 0)
				{
					int x = k + this.rand.nextInt(16);
					int z =	l + this.rand.nextInt(16);
	
					int generatedTrees = 0;
					int treeAmount = rand.nextInt(2)+4;
					for(int j = 0; (j < treeAmount*4) && (generatedTrees < treeAmount); j++)
					{
						int cx = x+this.rand.nextInt(15)-8;
						int cz = z+this.rand.nextInt(15)-8;
						int y = this.worldObj.getTopSolidOrLiquidBlock(cx, cz);
						
						
						if(rand.nextInt(3) == 0)
						{
							if(this.corrupttreegenerator.generate(this.worldObj, this.rand, cx, y, cz))
								generatedTrees++;
						} else if(this.darkcorruptedtreegenerator.generate(this.worldObj, this.rand, cx, y, cz))
							generatedTrees++;
					}
				}
				
			} else if(biomegenbase instanceof ElysiumBiomeGenPlain)
			{
				if(this.rand.nextInt(3) == 1)
				this.crystalEnergyGen.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), rand.nextInt(128), l+this.rand.nextInt(16));
				
				if(this.rand.nextInt(4) == 0)
				{
					int x = k + this.rand.nextInt(16);
					int z =	l + this.rand.nextInt(16);
	
					int generatedTrees = 0;
					int treeAmount = rand.nextInt(2)+4;
					for(int j = 0; (j < treeAmount*4) && (generatedTrees < treeAmount); j++)
					{
						int cx = x+this.rand.nextInt(15)-8;
						int cz = z+this.rand.nextInt(15)-8;
						int y = this.worldObj.getTopSolidOrLiquidBlock(cx, cz);
						
						if(this.treegenerator.generate(this.worldObj, this.rand, cx, y, cz))
							generatedTrees++;
					}
	
					if(rand.nextInt(6-generatedTrees) == 0)
					{
						this.flowergenerator.generate(this.worldObj, rand, x, 0, z);
					}
				}
			} else if(biomegenbase instanceof ElysiumBiomeGenPlain)
			{
				if(this.rand.nextInt(3) == 1)
				this.crystalEnergyGen.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), rand.nextInt(128), l+this.rand.nextInt(16));
				
				if(this.rand.nextInt(4) == 0)
				{
					int x = k + this.rand.nextInt(16);
					int z =	l + this.rand.nextInt(16);
	
					int generatedTrees = 0;
					int treeAmount = rand.nextInt(2)+4;
					for(int j = 0; (j < treeAmount*4) && (generatedTrees < treeAmount); j++)
					{
						int cx = x+this.rand.nextInt(15)-8;
						int cz = z+this.rand.nextInt(15)-8;
						int y = this.worldObj.getTopSolidOrLiquidBlock(cx, cz);
						
						if(this.treegenerator.generate(this.worldObj, this.rand, cx, y, cz))
							generatedTrees++;
					}
	
					if(rand.nextInt(6-generatedTrees) == 0)
					{
						this.flowergenerator.generate(this.worldObj, rand, x, 0, z);
					}
				}
			} else if(biomegenbase instanceof ElysiumBiomeGenForest)
			{
				if(this.rand.nextInt(3) == 1)
				this.crystalEnergyGen.generate(this.worldObj, this.rand, k+this.rand.nextInt(16), rand.nextInt(128), l+this.rand.nextInt(16));
				
				if(this.rand.nextInt(4) == 0)
				{
					int x = k + this.rand.nextInt(16);
					int z =	l + this.rand.nextInt(16);
	
					int generatedTrees = 0;
					int treeAmount = rand.nextInt(3)+6;
					for(int j = 0; (j < treeAmount*4) && (generatedTrees < treeAmount); j++)
					{
						int cx = x+this.rand.nextInt(15)-8;
						int cz = z+this.rand.nextInt(15)-8;
						int y = this.worldObj.getTopSolidOrLiquidBlock(cx, cz);
						
						if(this.darktreegenerator.generate(this.worldObj, this.rand, cx, y, cz))
							generatedTrees++;
					}
	
					if(rand.nextInt(9-generatedTrees) == 0)
					{
						this.flowergenerator.generate(this.worldObj, rand, x, 0, z);
					}
				}
			}
		}

		biomegenbase.decorate(this.worldObj, this.rand, k, l);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);

		//Snow
		
		k += 8;
		l += 8;

		
        for (int k1 = 0; doGen && k1 < 16; ++k1)
        {
            for (int l1 = 0; l1 < 16; ++l1)
            {
                i2 = this.worldObj.getPrecipitationHeight(k + k1, l + l1);

                if (this.worldObj.isBlockFreezable(k1 + k, i2 - 1, l1 + l))
                {
                    this.worldObj.setBlock(k1 + k, i2 - 1, l1 + l, Blocks.ice, 0, 2);
                }

                if (this.worldObj.func_147478_e(k1 + k, i2, l1 + l, true))
                {
                	this.worldObj.setBlock(k1 + k, i2, l1 + l, Blocks.snow_layer, 0, 2);
                }
            }
        }

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(par1IChunkProvider, worldObj, rand, chunkX, chunkZ, flag));

		BlockFalling.fallInstantly = false;
	}

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    @Override
    public void saveExtraData() {}

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    @Override
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    @Override
    public String makeString()
    {
        return "ChunkProviderElysium";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    @Override
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(par2, par3, par4) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    @Override
    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
    	return null;
//        return "Stronghold".equals(p_147416_2_) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }
    
    @Override
    public void recreateStructures(int par1, int par2)
    {
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
//            this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
//            this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
        }
    }

}