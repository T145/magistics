package T145.magistics.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.codechicken.lib.vec.BlockCoord;
import thaumcraft.common.config.Config;

public class TileEverfullUrn extends TileThaumcraft {
	private List<BlockCoord> blacklist = new ArrayList<BlockCoord>();
	private int[] targetQuards = new int[3];
	private boolean filling = false;
	private int soundDelay = 33;

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		blacklist = getBlackListNBT(tag.getIntArray("blacklist"));
		targetQuards = tag.getIntArray("targetQuards");
		filling = tag.getBoolean("filling");
	}

	private List<BlockCoord> getBlackListNBT(int[] coords) {
		List<BlockCoord> filled = new ArrayList<BlockCoord>();

		for (int i = coords.length; i > 0; i -= 3) {
			int xcount = i - 2;
			int ycount = i - 1;
			int zcount = i;

			filled.add(new BlockCoord(xcount, ycount, zcount));
		}

		return filled;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setIntArray("blacklist", setBlackListNBT());
		tag.setIntArray("targetQuards", targetQuards);
		tag.setBoolean("filling", filling);
	}

	private int[] setBlackListNBT() {
		int[] coords = new int[blacklist.size() * 3];
		int xcount = -1;
		int ycount = 0;
		int zcount = 1;

		for (BlockCoord coord : blacklist) {
			coords[++xcount] = coord.x;
			coords[++ycount] = coord.y;
			coords[++zcount] = coord.z;
		}

		return coords;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public boolean isActive() {
		Block block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
		return block.getMaterial() == Material.air || block.getMaterial() == Config.airyMaterial;
	}

	public boolean isFilling() {
		return filling;
	}

	public int getTargetX() {
		return targetQuards[0];
	}

	public int getTargetY() {
		return targetQuards[1];
	}

	public int getTargetZ() {
		return targetQuards[2];
	}

	private void setTarget(int x, int y, int z) {
		targetQuards[0] = x;
		targetQuards[1] = y;
		targetQuards[2] = z;
	}

	@Override
	public void updateEntity() {
		if (hasWorldObj() && isActive()) {
			--soundDelay;

			if (soundDelay == 0) {
				worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "liquid.water", worldObj.rand.nextFloat() * 0.2F + 0.2F, worldObj.rand.nextFloat() * 1.0F + 0.5F);
				soundDelay = 33;
			}

			int range = 6;

			for (int x = -range; x <= range; ++x) {
				for (int y = -range; y <= range; ++y) {
					for (int z = -range; z <= range; ++z) {
						BlockCoord coord = new BlockCoord(xCoord + x, yCoord + y, zCoord + z);
						TileEntity tile = worldObj.getTileEntity(coord.x, coord.y, coord.z);

						if (isWhiteListed(coord) && tile != null) {
							if (tile instanceof IFluidHandler) {
								IFluidHandler tank = (IFluidHandler) tile;
								FluidStack water = new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);

								if (tank.fill(ForgeDirection.UNKNOWN, water, false) == FluidContainerRegistry.BUCKET_VOLUME) {
									filling = true;
									tank.fill(ForgeDirection.UNKNOWN, water, true);
									setTarget(coord.x, coord.y, coord.z);
								} else {
									blacklist.add(coord);
								}
								return;
							} else if (tile instanceof IFluidTank) {
								IFluidTank tank = (IFluidTank) tile;
								FluidStack water = new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);

								if (tank.fill(water, false) == FluidContainerRegistry.BUCKET_VOLUME) {
									filling = true;
									tank.fill(water, true);
									setTarget(coord.x, coord.y, coord.z);
								} else {
									blacklist.add(coord);
								}
								return;
							}
						} else {
							blacklist.remove(coord);
						}
					}
				}
			}
		}
	}

	private boolean isWhiteListed(BlockCoord coord) {
		if (!blacklist.isEmpty() && blacklist.contains(coord)) {
			filling = false;
			return false;
		}
		return true;
	}
}