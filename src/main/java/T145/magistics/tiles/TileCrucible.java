package T145.magistics.tiles;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.MagisticsApi;
import T145.magistics.api.tiles.TileVisContainer;
import T145.magistics.lib.aura.AuraChunk;
import T145.magistics.lib.aura.AuraHandler;
import T145.magistics.lib.sounds.SoundHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class TileCrucible extends TileVisContainer {

	private float max;
	private float conversion;
	private float speed;
	private boolean updateNextPeriod;
	private boolean powering = false;

	public int smeltDelay;
	private int updateDelay;
	private int soundDelay = 25;

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

	@Override
	public void update() {
		super.update();

		if (hasWorldObj()) {
			Chunk chunk = worldObj.getChunkFromBlockCoords(pos);
			AuraChunk aura = AuraHandler.getAuraChunk(chunk);
			float totalVis = vis + miasma;

			--smeltDelay;
			--updateDelay;

			if (updateDelay <= 0) {
				worldObj.scheduleUpdate(pos, getBlockType(), 0);
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
					if (aura != null && miasma >= 1F) {
						miasma -= 1F;
						float auraMiasma = aura.getMiasma();
						aura.setMiasma(++auraMiasma);
						Magistics.proxy.customWispFX(worldObj, pos.getX() + worldObj.rand.nextFloat(), pos.getY() + 0.8F, pos.getZ() + worldObj.rand.nextFloat(), pos.getX() + 0.5F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()), pos.getY() + 2F + worldObj.rand.nextFloat(), pos.getZ() + 0.5F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()), 0.5F, 5);
					}
				}

				worldObj.scheduleUpdate(pos, getBlockType(), 0);
			}

			if (getBlockMetadata() == 1 || getBlockMetadata() == 2) {
				boolean oldPower = powering;

				powering = totalVis >= max * 0.9D;

				if (oldPower != powering) {
					for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							for (int k = -1; k < 2; k++) {
								worldObj.scheduleUpdate(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k), getBlockType(), 0);
								worldObj.notifyNeighborsOfStateChange(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k), getBlockType());
							}
						}
					}
				}
			}

			if (smeltDelay <= 0) {
				if (getBlockMetadata() != 3) {
					smeltDelay = 5;

					List<EntityItem> list = worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D));;

					if (list.size() > 0) {
						EntityItem item = list.get(worldObj.rand.nextInt(list.size()));
						ItemStack stack = item.getEntityItem();
						float visOutput = MagisticsApi.getCrucibleResult(stack);

						if (visOutput > 0F) {
							// check for arcane furnace below

							// boost conversion rate if above arcane furnace

							float pureCook = visOutput * conversion;
							float miasmaCook = visOutput - pureCook;

							if (getBlockMetadata() != 2 || totalVis + visOutput <= max) {
								vis += pureCook;
								miasma += miasmaCook;
								smeltDelay = 10 + Math.round(visOutput / 5F / speed);

								// decrease delay if above arcane furnace

								// discharge this chunk's aura

								--stack.stackSize;

								if (stack.stackSize <= 0) {
									item.setDead();
								}

								worldObj.scheduleUpdate(pos, getBlockType(), 0);
								worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, item.posX, item.posY, item.posZ, 0D, 0D, 0D);
								worldObj.playSound(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundHandler.bubbling, SoundCategory.BLOCKS, 0.25F, 0.9F + worldObj.rand.nextFloat() * 0.2F);
							}
						} else {
							item.motionX = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
							item.motionY = 0.2F + worldObj.rand.nextFloat() * 0.3F;
							item.motionZ = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
							worldObj.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.5F, 2F + worldObj.rand.nextFloat() * 0.45F);
							item.setPickupDelay(10);
						}
					}
				} else if (Math.round(totalVis + 1.0F) <= max) {
					smeltDelay = 20;

					List<EntityLiving> mobs = worldObj.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(pos.getX() - 4, pos.getY() - 4, pos.getZ() - 4, pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5));

					if (mobs.size() > 0) {
						for (EntityLiving mob : mobs) {
							if (mob instanceof EntitySnowman) {
								mob.spawnExplosionParticle();
								mob.setDead();
							}

							// check if above arcane furnace
							// if above arcane furance, boost conversion rate

							float mod = 1F;

							if (mob.isEntityUndead()) {
								mod = 0.5F;
							}

							float visCook = mod * conversion;
							float miasmaCook = mod - visCook;

							mob.attackEntityFrom(DamageSource.magic, 1);
							mob.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 3000, 0));

							// discharge this chunk's aura

							for (int b = 0; b < 3; b++) {
								Magistics.proxy.customWispFX(worldObj, mob.posX + worldObj.rand.nextFloat() - worldObj.rand.nextFloat(), mob.posY + mob.height / 2.0F + worldObj.rand.nextFloat() - worldObj.rand.nextFloat(), mob.posZ + worldObj.rand.nextFloat() - worldObj.rand.nextFloat(), pos.getX() + 0.5F, pos.getY() + 0.25F, pos.getZ() + 0.5F, 0.3F, 5);
							}
						}
					}
				}
			}
		}
	}
}