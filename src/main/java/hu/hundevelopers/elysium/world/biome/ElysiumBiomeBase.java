package hu.hundevelopers.elysium.world.biome;

import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDoublePlant;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import T145.magistics.common.config.ConfigObjects;

public class ElysiumBiomeBase extends BiomeGenBase
{   

	public ElysiumBiomeBase(int id)
	{
		super(id);
        this.flowers.add(new FlowerEntry(ConfigObjects.blockFlower, 0, 20));
        
        this.setDisableRain();
        
        this.topBlock = ConfigObjects.blockGrass;
		this.fillerBlock = ConfigObjects.blockDirt;
		
        this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}
	
	/**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand)
    {
		int r = rand.nextInt(10);
		if(r == 0)
		{
			return new WorldGenTallGrass(ConfigObjects.blockRaspberryBush, 0);
		} else if(r == 1)
		{
			return new ElysiumGenDoublePlant(ConfigObjects.blockGrapesBush, 0, 1);
		} else 
		{
			return new WorldGenTallGrass(ConfigObjects.blockTallGrass, 0);
		}
    }
}
