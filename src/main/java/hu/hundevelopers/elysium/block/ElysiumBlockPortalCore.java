package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.Configs;
import hu.hundevelopers.elysium.tile.ElysianTileEntityPortal;
import hu.hundevelopers.elysium.world.ElysiumTeleporter;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ElysiumBlockPortalCore extends ElysiumBlockContainer
{
	public ElysiumBlockPortalCore(Material mat)
	{
		super(mat);
		this.setBlockBounds(0.5F, 1F, 0.5F, 0.5F, 1F, 0.5F);
		this.setTickRandomly(true);
		this.setCreativeTab(null);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World wolrd, int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		world.setBlock(x, y, z, this);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if((entity.riddenByEntity == null) && (entity.ridingEntity == null))
		{
			if(entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) entity;
				ElysianTileEntityPortal tile = (ElysianTileEntityPortal)world.getTileEntity(x, y, z);

				if((!world.isRemote) && (tile.timebeforetp == Configs.ticksbeforeportalteleport-1))
				{
					player.addChatMessage(new ChatComponentText(getWorldMessage(player)));
				}

				if(tile.timebeforetp == 0)
				{
					tile.timebeforetp = -1;

					if(player.dimension == Elysium.dimensionID)
					{
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new ElysiumTeleporter(player.mcServer.worldServerForDimension(0)));
					}
					else
					{
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Elysium.dimensionID, new ElysiumTeleporter(player.mcServer.worldServerForDimension(Elysium.dimensionID)));
					}
				}
				else if(player.prevPosY == player.posY)
				{
					tile.wasCollided = true;
					if(tile.timebeforetp == -1) tile.timebeforetp = Configs.ticksbeforeportalteleport;
				}
			}
		}
	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	public void randomDisplayTick(World world, int x, int y, int z, Random random)
//	{
//		double rad = ((ElysianTileEntityPortal)world.getBlockTileEntity(x, y, z)).radius;
//
//		int part = random.nextInt(20);
//		for(int i = 0; i < part; i++)
//		{
//			int deg = random.nextInt(360);
//			ElysianEntityFX entityfx = new ElysianEntityFX(world, x+rad*Math.cos(Math.toRadians(deg))+0.5D, y+random.nextInt(100), z+rad*Math.sin(Math.toRadians(deg))+0.5D, 0, 0.1D, 0);
//			entityfx.setRBGColorF(1F, 1F, 0F);
//			entityfx.setBrightness(125);
//			entityfx.setTextureFile("elysium:textures/misc/particles/beam.png");
//			ModLoader.getMinecraftInstance().effectRenderer.addEffect(entityfx);
//		}
//	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2)
	{
		return new ElysianTileEntityPortal();
	}

	/**
	 * Returns the correct warping message for elysium depending on where the player is.
	 * @param player
	 * @return Message to where the player is warping
	 */
	public String getWorldMessage(EntityPlayer player)
	{
		if(player.dimension == 0)
		{
			return "Teleporting to the " + EnumChatFormatting.GRAY + "Elysium";
		}
		else
		{
			return "Teleporting to the " + EnumChatFormatting.GRAY + "Overworld";
		}
	}
}