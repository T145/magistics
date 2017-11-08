package T145.magistics.tiles.crafting;

import java.util.List;

import T145.magistics.api.MagisticsApi;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.blocks.crafting.BlockCrucible;
import T145.magistics.blocks.crafting.BlockCrucible.CrucibleType;
import T145.magistics.core.ModInit;
import T145.magistics.network.PacketHandler;
import T145.magistics.network.messages.client.MessageSendWispFX;
import T145.magistics.tiles.base.TileSynchronized;
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
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;

public class TileCrucible extends TileSynchronized implements ITickable, IQuintContainer {

	private CrucibleType type;
	private float quints;
	private int smeltTicks;
	private int soundTicks;
	private short updateTicks;
	private boolean working;

	public TileCrucible(CrucibleType type) {
		this.type = type;
	}

	public TileCrucible() {
		this(CrucibleType.BASIC);
	}

	public CrucibleType getCrucibleType() {
		return type;
	}

	public boolean isNormal() {
		return type != CrucibleType.SOULS;
	}

	public boolean isWorking() {
		return working;
	}

	public boolean isOverflowing() {
		return quints > getCapacity();
	}

	public boolean isFull() {
		return quints == getCapacity();
	}

	public boolean isEmpty() {
		return quints == 0F;
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
	public boolean canConnectAtSide(EnumFacing side) {
		return side != EnumFacing.UP && side != EnumFacing.DOWN;
	}

	@Override
	public float getQuints() {
		return quints;
	}

	@Override
	public void setQuints(float quints) {
		this.quints = quints;
	}

	@Override
	public float getCapacity() {
		return type.getCapacity();
	}

	@Override
	public int getSuction() {
		return 0;
	}

	@Override
	public void setSuction(int suction) {}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt) {
		nbt.setString("Type", type.toString());
		nbt.setFloat("Quints", quints);
		nbt.setBoolean("Working", working);
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt) {
		type = CrucibleType.valueOf(nbt.getString("Type"));
		quints = nbt.getFloat("Quints");
		working = nbt.getBoolean("Working");
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		--smeltTicks;
		++updateTicks;

		if (updateTicks % 10 == 0) {
			updateTicks = 0;
			markForUpdate();
		}

		--soundTicks;

		if (soundTicks <= 0) { // may be used for ambient dripping or boiling noise
			soundTicks = 15 + world.rand.nextInt(15);
		}

		if (isOverflowing()) {
			float spiltQuints = Math.min((quints - getCapacity()) / 2F, 1F);

			if (quints >= spiltQuints) {
				quints -= spiltQuints;
			}

			if (spiltQuints >= 1F) {
				// pollute chunk aura
				PacketHandler.INSTANCE.sendToAllAround(new MessageSendWispFX(pos.getX() + world.rand.nextFloat(), pos.getY() + 0.8F, pos.getZ() + world.rand.nextFloat(), pos.getX() + 0.5F + (world.rand.nextFloat() - world.rand.nextFloat()), pos.getY() + 2F + world.rand.nextFloat(), pos.getZ() + 0.5F + (world.rand.nextFloat() - world.rand.nextFloat()), 0.5F, 5), PacketHandler.getTargetPoint(world, pos));
			}

			markForUpdate();
		}

		if (type.canProvidePower()) {
			boolean wasWorking = working;
			working = quints >= getCapacity() * 0.9D;

			if (working != wasWorking) {
				world.setBlockState(pos, getState().withProperty(BlockCrucible.WORKING, working));
			}
		}

		if (smeltTicks <= 0) {
			if (isNormal()) {
				smeltContentsWithin();
			} else if (Math.round(quints + 1F) < getCapacity()) {
				drainSurroundingMobs();
			}
		}
	}

	private void smeltContentsWithin() {
		List<EntityItem> items = getItemsWithin();
		smeltTicks = 5;

		if (!items.isEmpty()) {
			EntityItem item = items.get(world.rand.nextInt(items.size()));
			ItemStack stack = item.getItem();
			float quintYield = MagisticsApi.getCrucibleResult(stack);

			if (quintYield > 0F) {
				// boost conversion rate iff above arcane furnace

				if (type != CrucibleType.THAUMIUM || quints + quintYield <= getCapacity()) {
					quints += quintYield * type.getConversion();
					smeltTicks = 10 + Math.round(quintYield / 5F / type.getSpeed());

					// decrease smeltTicks iff above arcane furnace
					// discharge chunk aura

					stack.shrink(1);

					if (stack.isEmpty()) {
						item.setDead();
					}

					markForUpdate();
					((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, false, item.posX, item.posY, item.posZ, 1, 0D, 0D, 0D, 0D);
					world.playSound(null, pos, ModInit.SOUND_BUBBLING, SoundCategory.MASTER, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
				}
			} else {
				ejectItem(item);
			}
		}
	}

	private void drainSurroundingMobs() {
		boolean wasWorking = working;
		working = false;
		smeltTicks = 20;

		for (EntityLivingBase mob : getSurroundingMobs()) {
			if (!(mob instanceof EntityPlayer) && !(mob instanceof EntityTameable) && mob.hurtTime <= 0 && mob.deathTime <= 0) {
				if (working = !(mob instanceof EntitySnowman)) {
					// increase conversion rate iff above arcane furnace (or heated?)

					float quintYield = mob.isEntityUndead() ? 0.5F : 1F;
					quints += quintYield * type.getConversion();

					mob.attackEntityFrom(DamageSource.GENERIC, 1);
					mob.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 3000, 0));

					for (int b = 0; b < 3; ++b) {
						PacketHandler.INSTANCE.sendToAllAround(new MessageSendWispFX(mob.posX + world.rand.nextFloat() - world.rand.nextFloat(), mob.posY + mob.height / 2.0F + world.rand.nextFloat() - world.rand.nextFloat(), mob.posZ + world.rand.nextFloat() - world.rand.nextFloat(), pos.getX() + 0.5F, pos.getY() + 0.25F, pos.getZ() + 0.5F, 0.3F, 5), PacketHandler.getTargetPoint(world, pos));
					}
				} else {
					EntitySnowman snowman = (EntitySnowman) mob;
					snowman.spawnExplosionParticle();
					snowman.setDead();
				}
			}
		}

		if (working) {
			// discharge chunk aura
			//markForUpdate();
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModInit.SOUND_SUCK, SoundCategory.MASTER, 0.1F, 0.8F + world.rand.nextFloat() * 0.3F);
		}

		if (working != wasWorking) {
			world.setBlockState(pos, getState().withProperty(BlockCrucible.WORKING, working));
		}
	}
}