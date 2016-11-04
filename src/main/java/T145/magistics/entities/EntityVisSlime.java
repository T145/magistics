package T145.magistics.entities;

import javax.annotation.Nullable;

import T145.magistics.load.ModItems;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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

	@Override
	protected Item getDropItem() {
		return getSlimeSize() == 1 ? new ItemStack(ModItems.itemShardFragment, 1, getEntityWorld().rand.nextInt(5)).getItem() : null;
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return getSlimeSize() == 1 ? LootTableList.ENTITIES_SLIME : LootTableList.EMPTY;
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}
}