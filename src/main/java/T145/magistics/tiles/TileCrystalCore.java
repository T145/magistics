package T145.magistics.tiles;

import T145.magistics.Magistics;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockCosmeticSolid;

public class TileCrystalCore extends TileThaumcraft {
	private boolean active = false;
	public float speed = 0F;
	public float rotation = 0F;
	private int count = -1;

	public boolean isActive() {
		return active;
	}

	public void active() {
		active = true;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public boolean isObsidianTotem(int x, int y, int z) {
		Block block = worldObj.getBlock(x, y, z);
		return block instanceof BlockCosmeticSolid && this.worldObj.getBlockMetadata(x, y, z) == 0;
	}

	public boolean isDawnTotem(int x, int y, int z) {
		return false;
	}

	public boolean isDuskTotem(int x, int y, int z) {
		return false;
	}

	private boolean hasAltar() {
		if (isObsidianTotem(xCoord - 1, yCoord - 1, zCoord - 1) &&
			isObsidianTotem(xCoord + 1, yCoord - 1, zCoord + 1) &&
			isObsidianTotem(xCoord - 1, yCoord - 1, zCoord + 1) &&
			isObsidianTotem(xCoord + 1, yCoord - 1, zCoord - 1) &&
			isObsidianTotem(xCoord - 1, yCoord - 2, zCoord - 1) &&
			isObsidianTotem(xCoord + 1, yCoord - 2, zCoord + 1) &&
			isObsidianTotem(xCoord - 1, yCoord - 2, zCoord + 1) &&
			isObsidianTotem(xCoord + 1, yCoord - 2, zCoord - 1)) {
			return true;
		}

		return false;
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		active = tag.getBoolean("active");
		speed = tag.getFloat("speed");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setBoolean("active", active);
		tag.setFloat("speed", speed);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (hasWorldObj()) {
			++count;
			rotation += speed;

			if (active) {
				if (speed < 1F) {
					speed += 0.01F + speed / 100F;
				} else {
					speed = 1F;
				}
			} else if (speed > 0F) {
				speed -= 01f;
			} else {
				speed = 0F;
			}

			if (active && speed > 0.9 && count % 2 == 0) {
				switch (count % 8 / 2) {
				case 0:
					Thaumcraft.proxy.blockRunes(worldObj, xCoord - 1, yCoord - 1, zCoord - 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 1F);
					break;
				case 1:
					Thaumcraft.proxy.blockRunes(worldObj, xCoord + 1, yCoord - 1, zCoord + 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 1F);
					break;
				case 2:
					Thaumcraft.proxy.blockRunes(worldObj, xCoord - 1, yCoord - 1, zCoord + 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 1F);
					break;
				case 3:
					Thaumcraft.proxy.blockRunes(worldObj, xCoord + 1, yCoord - 1, zCoord - 1, 0.3F + worldObj.rand.nextFloat() * 0.7F, 0F, 0.3F + worldObj.rand.nextFloat() * 0.7F, 20, 1F);
					break;
				}
			}

			if (this.hasAltar()) {
				
			}
		}
	}
}