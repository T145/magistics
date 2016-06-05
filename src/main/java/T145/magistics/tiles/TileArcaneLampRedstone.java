package T145.magistics.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileArcaneLamp;
import T145.magistics.blocks.BlockArcaneLampRedstone;

public class TileArcaneLampRedstone extends TileArcaneLamp {
	private boolean conflictingLamp = false;

	public boolean hasNeighborLamp() {
		return conflictingLamp;
	}

	public boolean isActive() {
		return getBlockType() == BlockArcaneLampRedstone.ACTIVE;
	}

	@Override
	public void updateEntity() {
		if (isActive()) {
			super.updateEntity();
			conflictingLamp = false;
		} else {
			removeLights();
		}
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		super.readCustomNBT(tag);
		conflictingLamp = tag.getBoolean("conflictingLamp");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		super.writeCustomNBT(tag);
		tag.setBoolean("conflictingLamp", conflictingLamp);
	}

	@Override
	public void removeLights() {
		for (int x = -15; x <= 15; x++) {
			for (int y = -15; y <= 15; y++) {
				for (int z = -15; z <= 15; z++) {
					int xx = xCoord + x;
					int yy = yCoord + y;
					int zz = zCoord + z;
					Block dest = worldObj.getBlock(xx, yy, zz);
					TileEntity tile = worldObj.getTileEntity(xx, yy, zz);

					if (tile != null && tile instanceof TileArcaneLamp) {
						if (tile instanceof TileArcaneLampRedstone) {
							TileArcaneLampRedstone lamp = (TileArcaneLampRedstone) tile;
							conflictingLamp = lamp.isActive();
						} else {
							conflictingLamp = true;
						}
					}

					if (!conflictingLamp) {
						if (worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z) == ConfigBlocks.blockAiry && worldObj.getBlockMetadata(xCoord + x, yCoord + y, zCoord + z) == 3) {
							worldObj.setBlockToAir(xCoord + x, yCoord + y, zCoord + z);
						}
					}
				}
			}
		}
	}
}