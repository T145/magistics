package T145.magistics.common.tiles;

import java.util.List;

import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.magic.IQuintContainer;
import T145.magistics.common.network.PacketHandler;
import T145.magistics.common.network.client.MessageUpdateContainer;
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

	private float quints;
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
	public boolean canConnectAtSide(EnumFacing side) {
		return side.getAxis() != EnumFacing.Axis.Y;
	}

	@Override
	public int getSuction() {
		return 0;
	}

	@Override
	public void setSuction(int suction) {}

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
		return 20F;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound tag) {
		tag.setFloat("Quints", quints);
	}

	@Override
	public void readCustomNBT(NBTTagCompound tag) {
		quints = tag.getFloat("Quints");
	}

	public void updateQuintLevel() {
		PacketHandler.sendToAllAround(new MessageUpdateContainer(pos, quints, 0), world, pos);
	}

	@Override
	public void update() {
		if (world.isRemote) {
			return;
		}

		if (smeltTicks > -1) {
			--smeltTicks;
		}

		if (++updateTicks % 10 == 0) {
			updateTicks = 0;
			updateQuintLevel();
		}

		--soundTicks;

		if (soundTicks <= 0) { // may be used for ambient noise
			soundTicks = 15 + world.rand.nextInt(15);
		}

		if (isOverflowing()) {
			float spiltQuints = Math.min((quints - getCapacity()) / 2F, 1F);

			if (quints >= spiltQuints) {
				quints -= spiltQuints;
			}

			if (spiltQuints >= 1F) {
				// discharge chunk aura
				// generate particles
			}

			updateQuintLevel();
		}

		if (smeltTicks <= 0) {
			smeltContentsWithin();
		}
	}

	private void smeltContentsWithin() {
		List<EntityItem> items = getItemsWithin();
		smeltTicks = 10;

		if (!items.isEmpty()) {
			EntityItem item = items.get(world.rand.nextInt(items.size()));
			ItemStack stack = item.getItem();
			float yield = RecipeRegistry.getCrucibleOutput(stack);

			if (yield > 0) {
				// boost conversion rate iff above arcane furnace
				//if (quints + yield <= getCapacity())

				quints += yield * 0.5F;
				smeltTicks = 10 + Math.round(yield / 5F / 0.25F);

				// decrease smeltTicks iff above arcane furnace
				// discharge chunk aura

				stack.shrink(1);

				if (stack.isEmpty()) {
					item.setDead();
				}

				updateQuintLevel();
				((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_LARGE, false, item.posX, item.posY, item.posZ, 1, 0D, 0D, 0D, 0D);
				world.playSound(null, pos, ModInit.SOUND_BUBBLING, SoundCategory.MASTER, 0.25F, 0.9F + world.rand.nextFloat() * 0.2F);
			} else {
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
}