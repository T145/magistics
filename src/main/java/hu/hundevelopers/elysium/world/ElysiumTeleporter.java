package hu.hundevelopers.elysium.world;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenCorruption;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;

public class ElysiumTeleporter extends Teleporter
{
	private final WorldServer worldServer;
    private final Random random;
    public static ArrayList<ElysiumPortalPosition> portals = new ArrayList<ElysiumPortalPosition>();

	public ElysiumTeleporter(WorldServer worldServer)
	{
		super(worldServer);
		this.worldServer = worldServer;
		this.random = new Random(worldServer.getSeed());
	}

	@Override
	public void placeInPortal(Entity entity, double par2, double par4, double par6, float par8)
	{
		worldServer.updateEntities();
		if(!this.placeInExistingPortal(entity, par2, par4, par6, par8))
		{
			this.makePortal(entity);
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double par2, double par4, double par6, float par8)
	{
		int x = MathHelper.floor_double(entity.posX);
		int z = MathHelper.floor_double(entity.posZ);
		int i;
		for(i=0; i<portals.size(); i++)
		{
			if((portals.get(i).dim == entity.dimension) && (Math.abs(portals.get(i).posX-x) < Configs.maxportaldistance) && (Math.abs(portals.get(i).posZ-z) < Configs.maxportaldistance))
			{
				entity.motionX = entity.motionY = entity.motionZ = 0.0D;
				int dx = random.nextInt(2);
				if(dx == 0) dx = -1;
				int dz = random.nextInt(2);
				if(dz == 0) dz = -1;
				entity.setPosition(portals.get(i).posX+0.5D+dx, portals.get(i).posY, portals.get(i).posZ+0.5D+dz);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean makePortal(Entity entity)
	{
		int x, y, z;

		boolean canBuild = false;

		while(true){
			x = MathHelper.floor_double(entity.posX) + random.nextInt(Configs.maxportaldistance+1) - Configs.maxportaldistance/2;
			z = MathHelper.floor_double(entity.posZ) + random.nextInt(Configs.maxportaldistance+1) - Configs.maxportaldistance/2;
			y = worldServer.getTopSolidOrLiquidBlock(x, z)-1;

			//check if can build
			int waterBellow = 0;
			for(int a=-2; a <= 2; a++)
			{
				for(int b=-2; b <= 2; b++)
				{
					Block block = worldServer.getBlock(x+a, y, z+b);
					if(block.isAir(worldServer, x+a, y, z+b) || block == Blocks.water || /*id == Elysium.waterStill.blockID || id == Elysium.waterMoving.blockID ||*/ block == Blocks.quartz_block)
						waterBellow++;

				}
			}

			if(waterBellow > 25/2)//More then the half is water bellow
				continue;
			else
				canBuild = true;
			//check end

			if(y<63)
				y=63-1;

			//get lowest air block underneath
			int lowest = -1;
			for (int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++) {
					if(lowest == -1 || worldServer.getTopSolidOrLiquidBlock(x+i, z+j) < lowest)
						lowest = worldServer.getTopSolidOrLiquidBlock(x+i, z+j);
				}
			}
			boolean isCorrupt = false;	
			BiomeGenBase biomegenbase = worldServer.getBiomeGenForCoords(x, z);
			if(biomegenbase != null && biomegenbase instanceof ElysiumBiomeGenCorruption)
				isCorrupt = true;
			
			for (int j = y; j >= lowest; j--) {
				for (int i = -2; i <= 2; i++) {
					for (int k = -2; k <= 2; k++) {
						Block block = worldServer.getBlock(x+i, j, z+k);
						if(block.isAir(worldServer, x+i, j, z+k) || block.canBeReplacedByLeaves(worldServer, x+i, j, z+k))
						{
							if(biomegenbase != null && biomegenbase.fillerBlock != null)
							{
								worldServer.setBlock(x+i, j, z+k, biomegenbase.fillerBlock);
							}
							else
							{
								if(worldServer.provider.dimensionId == Elysium.dimensionID)
									worldServer.setBlock(x+i, j, z+k, Elysium.blockDirt);
								else
									worldServer.setBlock(x+i, j, z+k, Blocks.dirt);
							}
						}
					}
				}
			}

			worldServer.setBlock(x, y+9, z, Elysium.blockPortalCore);
			worldServer.setBlockMetadataWithNotify(x, y+9, z, 1, 0);

			for(int i=-1; i <= 1; i++)
			{
				for(int j=-1; j <= 1; j++)
				{
					worldServer.setBlock(x+i, y+8, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
					worldServer.setBlockMetadataWithNotify(x+i, y+8, z+j, 1, 0);

					worldServer.setBlock(x+i, y+6, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
					worldServer.setBlockMetadataWithNotify(x+i, y+6, z+j, 2, 0);
					worldServer.setBlock(x+i, y+5, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
					worldServer.setBlockMetadataWithNotify(x+i, y+5, z+j, 2, 0);

					worldServer.setBlock(x+i, y+4, z+j, Blocks.gold_block);

					worldServer.setBlock(x+i, y+3, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
					worldServer.setBlockMetadataWithNotify(x+i, y+3, z+j, 2, 0);
					worldServer.setBlock(x+i, y+2, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
					worldServer.setBlockMetadataWithNotify(x+i, y+2, z+j, 2, 0);
				}
			}
			for(int i=-2; i <= 2; i++)
			{
				for(int j=-2; j <= 2; j++)
				{
					worldServer.setBlock(x+i, y+7, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
					worldServer.setBlock(x+i, y+1, z+j, isCorrupt && this.random.nextInt(3) == 0 ? Elysium.blockQuartzBlock : Blocks.quartz_block);
				}
			}



			if(canBuild)
				break;
		}
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		int dx = random.nextInt(2);
		if(dx == 0) dx = -1;
		int dz = random.nextInt(2);
		if(dz == 0) dz = -1;
		entity.setPosition(x+0.5D+dx, y+9, z+0.5D+dz);

		return true;
	}
}
