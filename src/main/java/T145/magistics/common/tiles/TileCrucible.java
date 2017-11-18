package T145.magistics.common.tiles;

import java.util.List;

import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.magic.FillPriority;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.network.PacketHandler;
import T145.magistics.common.network.client.MessageUpdateQuintLevel;
import T145.magistics.common.tiles.base.TileBase;
import T145.magistics.core.ModInit;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;

public class TileCrucible extends TileBase implements ITickable, IQuintContainer {

	private int quints;
	private int smeltTicks;
	private int soundTicks;
	private short updateTicks;

	public boolean isOverflowing() {
		return quints > getCapacity();
	}

	public boolean isFull() {
		return quints == getCapacity();
	}

	public boolean hasQuints() {
		return quints > 0;
	}

	public boolean isEmpty() {
		return quints == 0;
	}

	@Override
	public FillPriority getPriority() {
		return FillPriority.LOW;
	}

	@Override
	public boolean canConnectAtSide(EnumFacing side) {
		return side.getAxis() != EnumFacing.Axis.Y;
	}

	@Override
	public int fill(int amount, boolean doFill) {
		return 0; // can't fill this
	}

	@Override
	public int drain(int amount, boolean doDrain) {
		if (isEmpty()) {
			return 0;
		}

		int drainAmount = Math.min(amount, quints);

		if (doDrain) {
			quints -= drainAmount;
		}

		return drainAmount;
	}

	@Override
	public int getQuints() {
		return quints;
	}

	@Override
	public void setQuints(int quints) {
		this.quints = quints;
	}

	@Override
	public int getCapacity() {
		return 20;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setInteger("Quints", quints);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		quints = tag.getInteger("Quints");
	}

	public void updateQuintLevel() {
		PacketHandler.sendToAllAround(new MessageUpdateQuintLevel(this), world, pos);
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		--smeltTicks;

		if (++updateTicks % 10 == 0) {
			updateTicks = 0;
			updateQuintLevel();
		}

		--soundTicks;

		if (soundTicks <= 0) { // may be used for ambient dripping or boiling noise
			soundTicks = 15 + world.rand.nextInt(15);
		}

		if (isOverflowing()) {
			int spiltQuints = Math.min((quints - getCapacity()) / 2, 1);

			if (quints >= spiltQuints) {
				quints -= spiltQuints;
			}

			if (spiltQuints >= 1) {
				// discharge chunk aura
				// generate fx
			}

			updateQuintLevel();
		}

		if (smeltTicks <= 0) {
			smeltContentsWithin();
		}
	}

	private void smeltContentsWithin() {
		List<EntityItem> items = getItemsWithin();
		smeltTicks = 5;

		if (!items.isEmpty()) {
			EntityItem item = items.get(world.rand.nextInt(items.size()));
			ItemStack stack = item.getItem();
			int quintYield = RecipeRegistry.getCrucibleOutput(stack);

			if (quintYield > 0) {
				// boost conversion rate iff above arcane furnace

				if (quints + quintYield <= getCapacity()) {
					quints += quintYield /** 0.5F*/;
					smeltTicks = 10 + Math.round(quintYield / 5F / 0.25F);

					// decrease smeltTicks iff above arcane furnace
					// discharge chunk aura

					stack.shrink(1);

					if (stack.isEmpty()) {
						item.setDead();
					}

					updateQuintLevel();
					((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, false, item.posX, item.posY, item.posZ, 1, 0D, 0D, 0D, 0D);
					world.playSound(null, pos, ModInit.SOUND_BUBBLING, SoundCategory.MASTER, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
				}
			} else {
				//ejectItem(item);
				burnItem(item);
			}
		}
	}

	public List<EntityItem> getItemsWithin() {
		return world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1D, pos.getY() + 1D, pos.getZ() + 1D));
	}

	public void burnItem(EntityItem item) {
		ItemStack stack = item.getItem();
		stack.shrink(1);

		if (stack.isEmpty()) {
			item.setDead();
		}

		// discharge chunk aura
		((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, false, item.posX, item.posY, item.posZ, 1, 0D, 0D, 0D, 0D);
		world.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.MASTER, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
	}

	public void ejectItem(EntityItem item) {
		item.motionX = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F;
		item.motionY = 0.2F + world.rand.nextFloat() * 0.3F;
		item.motionZ = (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F;
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.5F, 2F + world.rand.nextFloat() * 0.45F);
	}
}