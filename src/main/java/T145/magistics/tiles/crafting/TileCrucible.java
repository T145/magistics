package T145.magistics.tiles.crafting;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.MagisticsApi;
import T145.magistics.api.magic.IQuintessenceContainer;
import T145.magistics.client.fx.FXCreator;
import T145.magistics.lib.events.SoundHandler;
import T145.magistics.tiles.MTile;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class TileCrucible extends MTile implements IQuintessenceContainer {

	public int smeltDelay;

	private float quints;
	private float maxQuints;
	private float conversion;
	private float speed;
	private int updateDelay;
	private int soundDelay = 25;
	private boolean powering;

	public void setTier(int meta) {
		switch (meta) {
		case 1:
			maxQuints = 600F;
			conversion = 0.6F;
			speed = 0.5F;
			break;
		case 2:
			maxQuints = 750F;
			conversion = 0.7F;
			speed = 0.75F;
			break;
		case 3:
			maxQuints = 750F;
			conversion = 0.4F;
			speed = 0.75F;
			break;
		default:
			maxQuints = 500F;
			conversion = 0.5F;
			speed = 0.25F;
			break;
		}
	}

	public boolean isNormal() {
		return conversion != 0.4F;
	}

	public boolean isPowering() {
		return powering;
	}

	public boolean isFull() {
		return quints == maxQuints;
	}

	public boolean isOverflowing() {
		return quints > maxQuints;
	}

	@Override
	public boolean canConnect(EnumFacing facing) {
		return facing.getAxis() != EnumFacing.Axis.Y;
	}

	@Override
	public int getSuction() {
		return 0;
	}

	@Override
	public void setSuction(int suction) {}

	@Override
	public float getMaxQuintessence() {
		return maxQuints;
	}

	@Override
	public float getQuintessence() {
		return quints;
	}

	@Override
	public void setQuintessence(float amount) {
		quints = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setFloat("Quints", quints);
		compound.setFloat("MaxQuints", maxQuints);
		compound.setFloat("ConversionRate", conversion);
		compound.setFloat("Speed", speed);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		quints = compound.getFloat("Quints");
		maxQuints = compound.getFloat("MaxQuints");
		conversion = compound.getFloat("ConversionRate");
		speed = compound.getFloat("Speed");
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			--smeltDelay;
			--updateDelay;

			if (updateDelay <= 0) {
				refresh();
				updateDelay = 10;
			}

			--soundDelay;

			if (soundDelay <= 0) {
				soundDelay = 15 + world.rand.nextInt(15);
			}

			if (quints > maxQuints) {
				float overflowSplit = Math.min((quints - maxQuints) / 2F, 1F);

				if (quints >= overflowSplit) {
					quints -= overflowSplit;
				}

				if (overflowSplit >= 1F) {
					// pollute aura
				}

				world.scheduleUpdate(pos, getBlockType(), 0);
			}

			powering = conversion >= 0.6F && quints >= maxQuints * 0.9D;

			if (powering) {
				refresh();
			}

			if (smeltDelay <= 0) {
				// check if above arcane furnace

				if (isNormal()) {
					smeltDelay = 5;

					List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D));;

					if (list.size() > 0) {
						EntityItem item = list.get(world.rand.nextInt(list.size()));
						ItemStack stack = item.getEntityItem();
						float quintOutput = MagisticsApi.getCrucibleResult(stack);

						if (quintOutput > 0F) {
							// boost conversion rate if above arcane furnace

							float pureCook = quintOutput * conversion;
							float miasmaCook = quintOutput - pureCook;

							if (getBlockMetadata() != 2 || quints + quintOutput <= maxQuints) {
								quints += pureCook;
								smeltDelay = 10 + Math.round(quintOutput / 5F / speed);

								// decrease delay if above arcane furnace
								// slightly discharge this chunk's aura

								stack.shrink(1);

								if (stack.isEmpty()) {
									item.setDead();
								}

								refresh();
								world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, item.posX, item.posY, item.posZ, 0D, 0D, 0D);
								world.playSound(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundHandler.BUBBLING, SoundCategory.BLOCKS, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
							}
						} else {
							item.motionX = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F;
							item.motionY = 0.2F + world.rand.nextFloat() * 0.3F;
							item.motionZ = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F;
							world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), net.minecraft.init.SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.5F, 2F + world.rand.nextFloat() * 0.45F);
							item.setPickupDelay(10);
						}
					}
				} else if (Math.round(quints + 1F) <= maxQuints) {
					smeltDelay = 20;

					List<EntityLiving> mobs = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(pos.getX() - 4, pos.getY() - 4, pos.getZ() - 4, pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5));

					if (mobs.size() > 0) {
						for (EntityLiving mob : mobs) {
							if (mob instanceof EntitySnowman) {
								mob.spawnExplosionParticle();
								mob.setDead();
							}

							// decrease delay if above arcane furnace

							float mod = 1F;

							if (mob.isEntityUndead()) {
								mod = 0.5F;
							}

							quints += mod * conversion;

							mob.attackEntityFrom(DamageSource.MAGIC, 1);
							mob.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 3000, 0));

							// slightly discharge this chunk's aura

							for (int b = 0; b < 3; b++) {
								FXCreator.INSTANCE.customWispFX(world, mob.posX + world.rand.nextFloat() - world.rand.nextFloat(), mob.posY + mob.height / 2.0F + world.rand.nextFloat() - world.rand.nextFloat(), mob.posZ + world.rand.nextFloat() - world.rand.nextFloat(), pos.getX() + 0.5F, pos.getY() + 0.25F, pos.getZ() + 0.5F, 0.3F, 5);
							}
						}
					}
				}
			}
		}
	}
}