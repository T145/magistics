package T145.magistics.common.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryIron;
import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;

public class BlockChestHungryIron extends BlockIronChest {
	public static enum Types {
		iron, gold, diamond, copper, silver, crystal, obsidian, dirt;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileChestHungryIron(IronChestType.values()[metadata]);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileChestHungryIron tile = (TileChestHungryIron) world.getTileEntity(i, j, k);
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (IInventory) tile, this, 2, 2, world, i, j, k, true);
	}
}