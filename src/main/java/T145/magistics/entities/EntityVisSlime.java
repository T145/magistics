package T145.magistics.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import T145.magistics.api.objects.ModItems;
import T145.magistics.load.LootTables;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityVisSlime extends EntitySlime {

	public EntityVisSlime(World world) {
		super(world);
	}

	@Override
	protected EntitySlime createInstance() {
		return new EntityVisSlime(worldObj);
	}

	@Nonnull
	@Override
	public EntityItem dropItemWithOffset(@Nonnull Item itemIn, int size, float offsetY) {
		ItemStack stack = new ItemStack(ModItems.shardFragment, size, 4);
		return entityDropItem(stack, offsetY);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return getSlimeSize() == 1 ? LootTables.ENTITIES_VIS_SLIME : LootTableList.EMPTY;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		setSlimeSize(1);
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	protected boolean spawnCustomParticles() { // TODO: Add custom particles
		int i = getSlimeSize();

		for (int j = 0; j < i * 8; ++j) {
			float f = rand.nextFloat() * ((float) Math.PI * 2F);
			float f1 = rand.nextFloat() * 0.5F + 0.5F;
			float f2 = MathHelper.sin(f) * i * 0.5F * f1;
			float f3 = MathHelper.cos(f) * i * 0.5F * f1;
			double d0 = posX + f2;
			double d1 = posZ + f3;
			worldObj.spawnParticle(getParticleType(), d0, getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
		}

		return true;
	}
}