package T145.magistics.tiles.crafting;

import java.util.List;

import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.logic.IWorker;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.api.variants.blocks.EnumCrucible;
import T145.magistics.client.fx.FXCreator;
import T145.magistics.init.ModSounds;
import T145.magistics.tiles.MTile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;

public class TileCrucible extends MTile implements IQuintContainer, IWorker {

	private EnumCrucible type;
	private float quints;
	private float prevQuints;
	private int smeltDelay;
	private int updateDelay;
	private int soundDelay;
	private boolean working;
	private boolean update;

	public TileCrucible(EnumCrucible type) {
		this.type = type;
	}

	public TileCrucible() {
		this(EnumCrucible.BASIC);
	}

	public EnumCrucible getCrucibleType() {
		return type;
	}

	public boolean isNormal() {
		return type != EnumCrucible.SOULS;
	}

	public boolean isPowering() {
		return working && (type == EnumCrucible.EYES || type == EnumCrucible.THAUMIUM);
	}

	public boolean isDraining() {
		return working && type == EnumCrucible.SOULS;
	}

	public boolean isOverflowing() {
		return quints > getMaxQuints();
	}

	public boolean isFull() {
		return quints == getMaxQuints();
	}

	public List<EntityItem> getItemsWithin() {
		return world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1D, pos.getY() + 1D, pos.getZ() + 1D));
	}

	public List<EntityLivingBase> getSurroundingMobs() {
		return world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX() - 4D, pos.getY() - 4D, pos.getZ() - 4D, pos.getX() + 5D, pos.getY() + 5D, pos.getZ() + 5D));
	}

	public void ejectItem(EntityItem item) {
		item.motionX = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F;
		item.motionY = 0.2F + world.rand.nextFloat() * 0.3F;
		item.motionZ = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F;
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.5F, 2F + world.rand.nextFloat() * 0.45F);
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
	public void setSuction(int pressure) {}

	@Override
	public boolean isWorking() {
		return working;
	}

	@Override
	public float getMaxQuints() {
		return type.getMaxQuints();
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public float getDisplayQuints() {
		return quints > 0.1F ? quints : 0F;
	}

	@Override
	public void setQuints(float amount) {
		quints = amount;
	}

	@Override
	public void writePacketNBT(NBTTagCompound compound) {
		compound.setString("Type", type.toString());
		compound.setFloat("Quints", quints);
	}

	@Override
	public void readPacketNBT(NBTTagCompound compound) {
		type = EnumCrucible.valueOf(compound.getString("Type"));
		quints = compound.getFloat("Quints");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		--smeltDelay;
		--updateDelay;

		if (prevQuints != quints) {
			prevQuints = quints;
			update = true;
		}

		if (updateDelay <= 0 && update) {
			refresh();
			update = false;
			updateDelay = 10;
		}

		--soundDelay;

		if (soundDelay <= 0) {
			soundDelay = 15 + world.rand.nextInt(15);
		}

		// check for and handle overflow
		if (isOverflowing()) {
			float spiltQuints = Math.min((quints - getMaxQuints()) / 2F, 1F);

			if (quints >= spiltQuints) {
				quints -= spiltQuints;
			}

			if (spiltQuints >= 1F) {
				// pollute chunk aura
			}

			refresh();
		}

		// check for and handle powering
		if (type == EnumCrucible.EYES || type == EnumCrucible.THAUMIUM) {
			boolean wasWorking = isPowering();

			working = quints >= getMaxQuints() * 0.9D;

			if (working != wasWorking) {
				refresh();
			}
		}

		// check for and handle smelting items / draining mobs
		if (smeltDelay <= 0) {
			if (type == EnumCrucible.SOULS && Math.round(quints + 1F) <= getMaxQuints()) {
				boolean discharge = false;
				smeltDelay = 20;

				for (EntityLivingBase mob : getSurroundingMobs()) {
					if (!(mob instanceof EntityPlayer) && !(mob instanceof EntityTameable) && mob.hurtTime <= 0 && mob.deathTime <= 0) {
						if (mob instanceof EntitySnowman) {
							EntitySnowman snowman = (EntitySnowman) mob;
							snowman.spawnExplosionParticle();
							snowman.setDead();
						}

						// increase conversion rate iff above arcane furnace

						float quintYield = mob.isEntityUndead() ? 0.5F : 1F;

						quints += quintYield * type.getConversion();

						mob.attackEntityFrom(DamageSource.GENERIC, 1);
						mob.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 3000, 0));

						discharge = true;

						for (int b = 0; b < 3; ++b) {
							FXCreator.INSTANCE.customWispFX(world, mob.posX + world.rand.nextFloat() - world.rand.nextFloat(), mob.posY + mob.height / 2.0F + world.rand.nextFloat() - world.rand.nextFloat(), mob.posZ + world.rand.nextFloat() - world.rand.nextFloat(), pos.getX() + 0.5F, pos.getY() + 0.25F, pos.getZ() + 0.5F, 0.3F, 5);
						}
					}
				}

				if (working = discharge) {
					// discharge chunk aura
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.suck, SoundCategory.MASTER, 0.1F, 0.8F + world.rand.nextFloat() * 0.3F);
				}
			} else {
				smeltDelay = 5;

				List<EntityItem> items = getItemsWithin();

				if (!items.isEmpty()) {
					EntityItem item = items.get(world.rand.nextInt(items.size()));
					ItemStack stack = item.getEntityItem();
					float quintYield = RecipeRegistry.getCrucibleResult(stack);

					if (quintYield > 0F) {
						// boost conversion rate iff above arcane furnace

						if (type != EnumCrucible.THAUMIUM || quints + quintYield <= getMaxQuints()) {
							quints += quintYield * type.getConversion();
							smeltDelay = 10 + Math.round(quintYield / 5F / type.getSpeed());

							// decrease smeltDelay iff above arcane furnace
							// discharge chunk aura

							stack.shrink(1);

							if (stack.isEmpty()) {
								item.setDead();
							}

							((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, false, item.posX, item.posY, item.posZ, 1, 0D, 0D, 0D, 0D);
							world.playSound(null, pos, ModSounds.bubbling, SoundCategory.MASTER, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
						}
					} else {
						ejectItem(item);
					}
				}
			}
		}
	}
}