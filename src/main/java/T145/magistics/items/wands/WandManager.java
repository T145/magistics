package T145.magistics.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.items.wands.ItemWandCasting;
import T145.magistics.blocks.BlockInfusionWorkbench;

public class WandManager implements IWandTriggerManager {
	public static boolean createInfusionWorkbench(ItemStack wandstack, EntityPlayer player, World world, int x, int y, int z) {
		ItemWandCasting wand = (ItemWandCasting)wandstack.getItem();

		for (int xx = x - 1; xx <= x; ++xx) {
			int zz = z - 1;

			while (zz <= z) {
				if (fitInfusionWorkbench(world, xx, y, zz) && (wand.consumeAllVisCrafting(wandstack, player, new AspectList().add(Aspect.FIRE, 25).add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.AIR, 25).add(Aspect.ENTROPY, 25).add(Aspect.WATER, 25), true))) {
					if (!world.isRemote) {
						world.setBlock(xx, y, zz, BlockInfusionWorkbench.INSTANCE, 1, 0);
						world.setBlock(xx + 1, y, zz, BlockInfusionWorkbench.INSTANCE, 2, 0);
						world.setBlock(xx, y, zz + 1, BlockInfusionWorkbench.INSTANCE, 3, 0);
						world.setBlock(xx + 1, y, zz + 1, BlockInfusionWorkbench.INSTANCE, 4, 0);
						world.addBlockEvent(xx, y, zz, BlockInfusionWorkbench.INSTANCE, 1, 0);
						world.addBlockEvent(xx + 1, y, zz, BlockInfusionWorkbench.INSTANCE, 1, 0);
						world.addBlockEvent(xx, y, zz + 1, BlockInfusionWorkbench.INSTANCE, 1, 0);
						world.addBlockEvent(xx + 1, y, zz + 1, BlockInfusionWorkbench.INSTANCE, 1, 0);
						world.markBlockForUpdate(xx, y, zz);
						world.markBlockForUpdate(xx + 1, y, zz);
						world.markBlockForUpdate(xx, y, zz + 1);
						world.markBlockForUpdate(xx + 1, y, zz + 1);
						return true;
					}

					return false;
				} else {
					++zz;
				}
			}
		}

		return false;
	}

	public static boolean fitInfusionWorkbench(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == BlockInfusionWorkbench.INSTANCE
				&& world.getBlockMetadata(x, y, z) == 0
				&& world.getBlock(x + 1, y, z) == BlockInfusionWorkbench.INSTANCE
				&& world.getBlockMetadata(x + 1, y, z) == 0
				&& world.getBlock(x, y, z + 1) == BlockInfusionWorkbench.INSTANCE
				&& world.getBlockMetadata(x, y, z + 1) == 0
				&& world.getBlock(x + 1, y, z + 1) == BlockInfusionWorkbench.INSTANCE
				&& world.getBlockMetadata(x + 1, y, z + 1) == 0;
	}

	@Override
	public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event) {
		switch (event) {
		case 1:
			return createInfusionWorkbench(wand, player, world, x, y, z);
		}

		return false;
	}
}