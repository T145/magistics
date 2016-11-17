package T145.magistics.tiles;

import java.util.List;

import T145.magistics.api.MagisticsApi;
import T145.magistics.api.tiles.TileVisManager;
import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import T145.magistics.lib.events.WorldEventHandler;
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

	public float vis;
	public float miasma;
	private float pureBuffer;
	private float taintedBuffer;

	private float maxVis;
	private float conversion;
	private float speed;
	private float wait;
	private boolean updateNextPeriod;
	private boolean powering = false;

	public int smeltDelay;
	private int soundDelay = 25;

	public boolean isPowering() {
		return powering;
	}

	public void setTier(int meta) {
		switch (meta) {
		case 3:
			maxVis = 750F;
			conversion = 0.4F;
			speed = 0.75F;
			break;
		case 2:
			maxVis = 750F;
			conversion = 0.7F;
			speed = 0.75F;
			break;
		case 1:
			maxVis = 600F;
			conversion = 0.6F;
			speed = 0.5F;
			break;
		default:
			maxVis = 500F;
			conversion = 0.5F;
			speed = 0.25F;
			break;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		vis = tag.getFloat(WorldEventHandler.KEY_VIS);
		miasma = tag.getFloat(WorldEventHandler.KEY_MIASMA);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setFloat(WorldEventHandler.KEY_VIS, vis);
		tag.setFloat(WorldEventHandler.KEY_MIASMA, miasma);
		return tag;
	}

	@Override
	public void update() {
		super.update();

		if (hasWorldObj()) {
			float totalVis = vis + miasma;

			--smeltDelay;
			--wait;

			if (pureBuffer != vis || taintedBuffer != miasma) {
				taintedBuffer = miasma;
				pureBuffer = vis;
				updateNextPeriod = true;
			}

			if (wait <= 0 && updateNextPeriod) {
				worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
				updateNextPeriod = false;
				wait = 10;
			}

			--soundDelay;

			if (soundDelay <= 0) {
				soundDelay = 15 + worldObj.rand.nextInt(15);
			}

			if (totalVis > maxVis) {
				float overflowSplit = Math.min((vis + miasma - maxVis) / 2F, 1F);

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

				powering = totalVis >= maxVis * 0.9D;

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

				List list = getContents();

				if (list.size() > 0) {
					EntityItem entity = (EntityItem) list.get(worldObj.rand.nextInt(list.size()));
					ItemStack stack = entity.getEntityItem();
					float visOutput = MagisticsApi.getCrucibleResult(stack);

					if (visOutput > 0F) {
						// check for arcane furnace below

						// boost conversion rate if above arcane furnace

						float pureCook = visOutput * conversion;
						float taintCook = visOutput - pureCook;

						if (getBlockMetadata() != 2 || totalVis + visOutput <= maxVis) {
							vis += pureCook;
							miasma += taintCook;
							smeltDelay = (10 + Math.round(visOutput / 5F / speed));

							// decrease delay if above arcane furnace

							--stack.stackSize;

							if (stack.stackSize <= 0) {
								entity.setDead();
							}

							worldObj.scheduleUpdate(getPos(), getBlockType(), 0);
							worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D);
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

	private List getContents() {
		return worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX() + 1.0D, getPos().getY() + 1.0D, getPos().getZ() + 1.0D));
	}

	@Override
	public boolean getConnectable(EnumFacing face) {
		switch (face) {
		case UP: case DOWN:
			return false;
		default:
			return true;
		}
	}

	@Override
	public boolean isVisSource() {
		return true;
	}

	@Override
	public boolean isVisConduit() {
		return false;
	}

	@Override
	public float getPureVis() {
		return vis;
	}

	@Override
	public void setPureVis(float amount) {
		vis = amount;
	}

	@Override
	public float getTaintedVis() {
		return miasma;
	}

	@Override
	public void setTaintedVis(float amount) {
		miasma = amount;
	}

	@Override
	public float getMaxVis() {
		return maxVis;
	}

	@Override
	public float[] subtractVis(float amount) {
		float pureAmount = amount / 2F;
		float taintAmount = amount / 2F;
		float[] result = { 0F, 0F };

		if (amount < 0.001F) {
			return result;
		}

		if (vis < pureAmount) {
			pureAmount = vis;
		}

		if (miasma < taintAmount) {
			taintAmount = miasma;
		}

		if (pureAmount < amount / 2F && taintAmount == amount / 2F) {
			taintAmount = Math.min(amount - pureAmount, miasma);
		} else if (taintAmount < amount / 2F && pureAmount == amount / 2F) {
			pureAmount = Math.min(amount - taintAmount, vis);
		}

		vis -= pureAmount;
		miasma -= taintAmount;

		result[0] = pureAmount;
		result[1] = taintAmount;

		return result;
	}
}