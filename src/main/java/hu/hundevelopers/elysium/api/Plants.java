package hu.hundevelopers.elysium.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public class Plants
{
    private static final List<PlantEntry> plantList = new ArrayList<PlantEntry>();
    private static final List<SeedEntry> seedList = new ArrayList<SeedEntry>();


	static class PlantEntry extends WeightedRandom.Item
	{
		public final Block block;
		public final int metadata;

		public PlantEntry(Block block, int meta, int weight)
		{
			super(weight);
			this.block = block;
			this.metadata = meta;
		}
	}

	static class SeedEntry extends WeightedRandom.Item
	{
		public final ItemStack seed;

		public SeedEntry(ItemStack seed, int weight)
		{
			super(weight);
			this.seed = seed;
		}
	}

	/**
	 * Register a new plant to be planted when bonemeal is used on Elysium grass.
	 * 
	 * @param block - The block to place.
	 * @param metadata - The metadata to set for the block when being placed.
	 * @param weight - The weight of the plant, where red flowers are 10 and yellow flowers are 20.
	 */
	public static void addGrassPlant(Block block, int metadata, int weight)
	{
		plantList.add(new PlantEntry(block, metadata, weight));
	}

	/**
	 * Register a new seed to be dropped when breaking tall grass.
	 * 
	 * @param seed - The item to drop as a seed.
	 * @param weight - The relative probability of the seeds, where wheat seeds are 10.
	 */
	public static void addGrassSeed(ItemStack seed, int weight)
	{
		seedList.add(new SeedEntry(seed, weight));
	}

	/**
	 * Plant a random plant from {@link Plants#getPlantItem(World)} at the given location if possible
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void plantPlant(World world, int x, int y, int z)
    {
        ItemStack plant = getPlantItem(world);
        if (plant == null || !Block.getBlockFromItem(plant.getItem()).canBlockStay(world, x, y, z))
        {
            return;
        }
    	world.setBlock(x, y, z, Block.getBlockFromItem(plant.getItem()), plant.getItemDamage(), 3);
    }
	
	/**
	 * Return a random seed that the Curlgrass can drop in The Elysium e.g. Pepper Seed
	 * 
	 * @param world
	 * @return random seed
	 */
	public static ItemStack getSeedItem(World world)
    {
        SeedEntry entry = (SeedEntry)WeightedRandom.getRandomItem(world.rand, seedList);
        if (entry == null || entry.seed == null)
        {
            return null;
        }
        return entry.seed.copy();
    }
	
	/**
	 * Return a random plant which generates in The Elysium e.g. Curlgrass, Asphodel Flower
	 * 
	 * @param world
	 * @return random plant
	 */
	public static ItemStack getPlantItem(World world)
    {
		PlantEntry entry = (PlantEntry)WeightedRandom.getRandomItem(world.rand, plantList);
        if (entry == null || entry.block == null)
        {
            return null;
        }
        return new ItemStack(entry.block, entry.metadata);
    }
}