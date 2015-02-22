package hu.hundevelopers.elysium.world.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.block.ElysiumBlockFlower;
import hu.hundevelopers.elysium.world.gen.structures.ElysiumGenDefenceTower;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ElysiumBiomeGenPlain extends ElysiumBiomeBase
{

	public ElysiumBiomeGenPlain(int id)
	{
		super(id);
		this.theBiomeDecorator.flowersPerChunk = -1;
	}
	
	@Override
	//get flower with chance
	public String func_150572_a(Random rand, int x, int y, int z)
	{
		return rand.nextInt(3) > 0 ? ElysiumBlockFlower.names[0] : ElysiumBlockFlower.names[1];
	}

	 
    @Override
    public void decorate(World world, Random rand, int chunk_X, int chunk_Z)
    {
		super.decorate(world, rand, chunk_X, chunk_Z);
		
		if (rand.nextInt(7) == 0)
        {
            int k = chunk_X + rand.nextInt(16) + 8;
            int l = chunk_Z + rand.nextInt(16) + 8;
            ElysiumGenDefenceTower worldgenpyramid = new ElysiumGenDefenceTower();
            worldgenpyramid.generate(world, rand, k, world.getHeightValue(k, l)-1, l);
        }
		
		int flowersPerChunk = 5;
		
		boolean doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, FLOWERS);
		for (int j = 0; doGen && j < flowersPerChunk; ++j)
		{
			int k = chunk_X + rand.nextInt(16) + 8;
            int l = chunk_Z + rand.nextInt(16) + 8;
            int i1 = rand.nextInt(world.getHeightValue(k, l) + 32);
            
            String s = this.func_150572_a(rand, k, i1, l);

            this.theBiomeDecorator.yellowFlowerGen.func_150550_a(Elysium.blockFlower, ElysiumBlockFlower.getMetaFromName(s));
            this.theBiomeDecorator.yellowFlowerGen.generate(world, rand, k, i1, l);
        }
		
    }
}
