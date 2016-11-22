package T145.magistics.api.tiles;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public abstract class TileMagisticsLockableLoot extends TileMagisticsLockable implements ILootContainer {

	protected ResourceLocation lootTable;
	protected long lootTableSeed;

	public TileMagisticsLockableLoot(int inventorySize) {
		super(inventorySize);
	}

	protected boolean checkLootAndRead(NBTTagCompound tag) {
		if (tag.hasKey("LootTable", 8)) {
			lootTable = new ResourceLocation(tag.getString("LootTable"));
			lootTableSeed = tag.getLong("LootTableSeed");
			return true;
		} else {
			return false;
		}
	}

	protected boolean checkLootAndWrite(NBTTagCompound tag) {
		if (lootTable != null) {
			tag.setString("LootTable", lootTable.toString());

			if (lootTableSeed != 0L) {
				tag.setLong("LootTableSeed", lootTableSeed);
			}

			return true;
		} else {
			return false;
		}
	}

	protected void fillWithLoot(@Nullable EntityPlayer player) {
		if (lootTable != null) {
			LootTable table = worldObj.getLootTableManager().getLootTableFromLocation(lootTable);
			lootTable = null;
			Random random;

			if (lootTableSeed == 0L) {
				random = new Random();
			} else {
				random = new Random(lootTableSeed);
			}

			LootContext.Builder builder = new LootContext.Builder((WorldServer) worldObj);

			if (player != null) {
				builder.withLuck(player.getLuck());
			}

			table.fillInventory(this, random, builder.build());
		}
	}

	@Override
	public ResourceLocation getLootTable() {
		return lootTable;
	}

	public void setLootTable(ResourceLocation loc, long seed) {
		lootTable = loc;
		lootTableSeed = seed;
	}
}