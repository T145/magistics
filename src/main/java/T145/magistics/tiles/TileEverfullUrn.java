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
	private boolean filling = false;
	private int soundDelay = 33;

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		blacklist = getBlackListNBT(tag.getIntArray("blacklist"));
		filling = tag.getBoolean("filling");
	}

	private List<BlockCoord> getBlackListNBT(int[] coords) {
		List<BlockCoord> filled = new ArrayList<BlockCoord>();

		for (int i = coords.length; i > 0 && i % 3 == 0; i -= 3) {
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

	public BlockCoord getTarget() {
		return blacklist.isEmpty() ? null : blacklist.get(blacklist.size() - 1);
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
						int xx = xCoord + x;
						int yy = yCoord + y;
						int zz = zCoord + z;
						BlockCoord coord = new BlockCoord(xx, yy, zz);
						TileEntity tile = worldObj.getTileEntity(xx, yy, zz);

						if (isWhiteListed(coord) && tile != null) {
							if (tile instanceof IFluidHandler) {
								IFluidHandler tank = (IFluidHandler) tile;
								FluidStack water = new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);

								if (tank.fill(ForgeDirection.UNKNOWN, water, false) == FluidContainerRegistry.BUCKET_VOLUME) {
									filling = true;

									while (tank.fill(ForgeDirection.UNKNOWN, water, true) == FluidContainerRegistry.BUCKET_VOLUME);

									blacklist.add(coord);
								}
								return;
							} else if (tile instanceof IFluidTank) {
								IFluidTank tank = (IFluidTank) tile;
								FluidStack water = new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);

								if (tank.fill(water, false) == FluidContainerRegistry.BUCKET_VOLUME) {
									filling = true;

									while (tank.fill(water, true) == FluidContainerRegistry.BUCKET_VOLUME);

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
		if (!blacklist.isEmpty()) {
			for (BlockCoord match : blacklist) {
				if (coord.equals(match)) {
					filling = false;
					return false;
				}
			}
		}
		return true;
	}
}