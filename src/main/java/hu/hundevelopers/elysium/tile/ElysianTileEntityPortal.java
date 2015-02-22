package hu.hundevelopers.elysium.tile;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.world.ElysiumPortalPosition;
import hu.hundevelopers.elysium.world.ElysiumTeleporter;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ElysianTileEntityPortal extends TileEntity
{
	public boolean canstay = true;
	public ElysiumPortalPosition coords;
	byte tick;
	public int timebeforetp = -1;
	public boolean wasCollided = false;
	public int ticksWithoutColliding = 0;
	public float rotation = 0;
	public float alpha;
	public double radius;
	public float texPos;

	@Override
	public void updateEntity()
	{
		texPos -= 0.05F;
		if(texPos >= 1F) texPos -= 1F;

		rotation += 2.5F;
		if(rotation >= 360) rotation -= 360;

		radius = Math.sin(Math.toRadians(rotation*2))/4 + 1;
		alpha = (float) Math.sin(Math.toRadians(rotation*3))/4 + 0.5F;

		if(!wasCollided) ticksWithoutColliding++;
		if(wasCollided) ticksWithoutColliding = 0;
		if(ticksWithoutColliding > 5)
		{
			timebeforetp = -1;
		}
		if(timebeforetp > 0)
		{
			timebeforetp--;
		}
		wasCollided = false;

		if(coords == null)
		{
			coords = new ElysiumPortalPosition(worldObj.provider.dimensionId, xCoord, yCoord, zCoord);
		}

		tick++;
		if(tick >= Configs.ticksbeforeportalcheck)
		{
			tick = 0;
			canstay = canStayPortal();
		}

		if(canstay)
		{
			if(!ElysiumTeleporter.portals.contains(coords))
			{
				ElysiumTeleporter.portals.add(coords);
			}
		}
		else
		{
			if(ElysiumTeleporter.portals.contains(coords))
			{
				ElysiumTeleporter.portals.remove(coords);
			}

			if(!worldObj.isRemote)
			{
				EntityItem item = new EntityItem(worldObj, xCoord, yCoord, zCoord);
				item.setEntityItemStack(new ItemStack(Items.diamond));
				
				worldObj.spawnEntityInWorld(item);
			}
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.dragon_egg);
		}
	}

	public boolean canStayPortal()
	{
		boolean ret = true;

		for(int i=-2; i<=2; i++)
		{
			for(int j=-2; j<=2; j++)
			{
				if(worldObj.getBlock(xCoord+i, yCoord-2, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-2, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-2, zCoord+j) != 0) ret = false;
				if(worldObj.getBlock(xCoord+i, yCoord-8, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-8, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-8, zCoord+j) != 0) ret = false;
			}
		}
		for(int i=-1; i<=1; i++)
		{
			for(int j=-1; j<=1; j++)
			{
				if(worldObj.getBlock(xCoord+i, yCoord-1, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-1, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-1, zCoord+j) != 1) ret = false;

				if(worldObj.getBlock(xCoord+i, yCoord-3, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-3, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-3, zCoord+j) != 2) ret = false;
				if(worldObj.getBlock(xCoord+i, yCoord-4, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-4, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-4, zCoord+j) != 2) ret = false;

				if(worldObj.getBlock(xCoord+i, yCoord-5, zCoord+j) != Blocks.gold_block) ret = false;

				if(worldObj.getBlock(xCoord+i, yCoord-6, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-6, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-6, zCoord+j) != 2) ret = false;
				if(worldObj.getBlock(xCoord+i, yCoord-7, zCoord+j) != Blocks.quartz_block && worldObj.getBlock(xCoord+i, yCoord-7, zCoord+j) != Elysium.blockQuartzBlock) ret = false;
				if(worldObj.getBlockMetadata(xCoord+i, yCoord-7, zCoord+j) != 2) ret = false;
			}
		}

		return ret;
	}
}