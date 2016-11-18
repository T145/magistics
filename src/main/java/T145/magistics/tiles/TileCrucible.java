package T145.magistics.tiles;

import java.util.List;

import T145.magistics.api.MagisticsApi;
import T145.magistics.api.tiles.TileVisManager;
import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import T145.magistics.lib.sounds.SoundHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class TileCrucible extends TileVisManager {

	private int type;
	public int smeltDelay;
	private int updateDelay;
	private int soundDelay = 25;
	private float max;
	private float speed;
	private float conversion;
	private boolean powering = false;

	public int getType() {
		return type;
	}

	public void setType(int meta) {
		type = meta;
	}

	public float getMax() {
		return max;
	}

	public boolean isPowering() {
		return powering;
	}

	public void setTier(int meta) {
		switch (meta) {
		case 3:
			max = 750F;
			conversion = 0.4F;
			speed = 0.75F;
			break;
		case 2:
			max = 750F;
			conversion = 0.7F;
			speed = 0.75F;
			break;
		case 1:
			max = 600F;
			conversion = 0.6F;
			speed = 0.5F;
			break;
		default:
			max = 500F;
			conversion = 0.5F;
			speed = 0.25F;
			break;
		}
	}

	@Override
	public boolean getConnectable(EnumFacing facing) {
		return facing != EnumFacing.UP && facing != EnumFacing.DOWN;
	}

	public List<EntityItem> getContents() {
		return worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX() + 1.0D, getPos().getY() + 1.0D, getPos().getZ() + 1.0D));
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		type = tag.getInteger("Type");
		this.setTier(type);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("Type", type);
		return tag;
	}

	@Override
	public void update() {
		super.update();

		if (this.hasWorldObj()) {
			float totalVis = vis + miasma;

			--smeltDelay;
			--updateDelay;

			if (updateDelay <= 0) {
				worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
				updateDelay = 10;
			}

			--soundDelay;

			if (soundDelay <= 0) {
				soundDelay = 15 + worldObj.rand.nextInt(15);
			}

			if (totalVis > max) {
				float overflowSplit = Math.min((vis + miasma - max) / 2F, 1F);

				if (vis >= overflowSplit) {
					vis -= overflowSplit;
				}

				if (overflowSplit >= 1F) {
					Chunk chunk = worldObj.getChunkFromBlockCoords(getPos());
					AuraChunk aura = AuraHandler.getAuraChunk(chunk);

					if (aura != null && miasma >= 0.1F) {
						miasma -= 1F;
						float auraMiasma = aura.getMiasma();
						aura.setMiasma(++auraMiasma);
						// wispy fx
					}
				}

				worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
			}

			if (getBlockMetadata() == 1 || getBlockMetadata() == 2) {
				boolean oldPower = powering;

				powering = totalVis >= max * 0.9D;

				if (oldPower != powering) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							for (int k = -1; k < 2; k++) {
								worldObj.scheduleUpdate(new BlockPos(getPos().getX() + i, getPos().getY() + j, getPos().getZ() + k), getBlockType(), 0);
								worldObj.notifyNeighborsOfStateChange(new BlockPos(getPos().getX() + i, getPos().getY() + j, getPos().getZ() + k), getBlockType());
							}
						}
					}
				}
			}

			if (smeltDelay <= 0 && getBlockMetadata() != 3) {
				smeltDelay = 5;

				List<EntityItem> list = getContents();

				if (list.size() > 0) {
					EntityItem entity = list.get(worldObj.rand.nextInt(list.size()));
					ItemStack stack = entity.getEntityItem();
					float visOutput = MagisticsApi.getCrucibleResult(stack);

					if (visOutput > 0F) {
						// check for arcane furnace below

						// boost conversion rate if above arcane furnace

						float pureCook = visOutput * conversion;
						float taintCook = visOutput - pureCook;

						if (getBlockMetadata() != 2 || totalVis + visOutput <= max) {
							vis += pureCook;
							miasma += taintCook;
							smeltDelay = 10 + Math.round(visOutput / 5F / speed);

							// decrease delay if above arcane furnace

							// discharge this chunk's aura

							--stack.stackSize;

							if (stack.stackSize <= 0) {
								entity.setDead();
							}

							worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
							worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, entity.posX, entity.posY, entity.posZ, 0D, 0D, 0D);
							worldObj.playSound(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundHandler.bubbling, SoundCategory.BLOCKS, 0.25F, 0.9F + worldObj.rand.nextFloat() * 0.2F);
						}
					} else {
						entity.motionX = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
						entity.motionY = 0.2F + worldObj.rand.nextFloat() * 0.3F;
						entity.motionZ = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
						worldObj.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.5F, 2F + worldObj.rand.nextFloat() * 0.45F);
						entity.setPickupDelay(10);
					}
				}
			}
		}
	}
}