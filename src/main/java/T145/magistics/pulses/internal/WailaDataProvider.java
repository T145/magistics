package T145.magistics.pulses.internal;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.blocks.BlockArcaneDoor;
import thaumcraft.common.blocks.BlockArcaneFurnace;
import thaumcraft.common.config.ConfigItems;
import T145.magistics.blocks.BlockEntropicDispenser;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.items.ItemDummy;
import T145.magistics.tiles.TileChestHungryEnder;

public class WailaDataProvider implements IWailaDataProvider {
	private static final WailaDataProvider INSTANCE = new WailaDataProvider();

	public static void registerProvider(IWailaRegistrar provider) {
		provider.registerBodyProvider(INSTANCE, TileChestHungryEnder.class);

		provider.registerStackProvider(INSTANCE, BlockArcaneFurnace.class);
		provider.registerStackProvider(INSTANCE, BlockArcaneDoor.class);

		provider.registerStackProvider(INSTANCE, BlockNetherFurnace.class);
		provider.registerStackProvider(INSTANCE, BlockInfuser.class);
		provider.registerStackProvider(INSTANCE, BlockEntropicDispenser.class);
	}

	private boolean compareByClass(Class first, Class second) {
		if (first == null || second == null) {
			return false;
		} else {
			return first.getName().equalsIgnoreCase(second.getName());
		}
	}

	private boolean compareTileEntityByClass(TileEntity tile, Class tileClass) {
		return compareByClass(tile.getClass(), tileClass);
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity tile = accessor.getTileEntity();
		Block block = accessor.getBlock();
		int metadata = accessor.getMetadata();

		if (block instanceof BlockArcaneDoor) {
			return new ItemStack(ConfigItems.itemArcaneDoor);
		}

		if (block instanceof BlockArcaneFurnace) {
			return new ItemStack(ItemDummy.INFERNAL_FURNACE);
		}

		if (block == BlockNetherFurnace.INACTIVE) {
			return new ItemStack(BlockNetherFurnace.INACTIVE, 1, 3);
		}

		if (block == BlockNetherFurnace.ACTIVE) {
			return new ItemStack(BlockNetherFurnace.ACTIVE, 1, 3);
		}

		if (block instanceof BlockInfuser) {
			return new ItemStack(BlockInfuser.INSTANCE, 1, metadata);
		}

		if (block instanceof BlockEntropicDispenser) {
			return new ItemStack(BlockEntropicDispenser.INSTANCE, 1, 3);
		}

		return accessor.getStack();
	}

	@Override
	public List<String> getWailaHead(ItemStack stack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return tooltip;
	}

	@Override
	public List<String> getWailaBody(ItemStack stack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity tile = accessor.getTileEntity();
		Block block = accessor.getBlock();
		int metadata = accessor.getMetadata();

		if (block.hasTileEntity(metadata) && tile.hasWorldObj()) {
			if (compareTileEntityByClass(tile, TileChestHungryEnder.class)) {
				TileChestHungryEnder chest = (TileChestHungryEnder) tile;
				tooltip.add("Owner: " + chest.owner);
			}
		}

		return tooltip;
	}

	@Override
	public List<String> getWailaTail(ItemStack stack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return tooltip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
		if (te.hasWorldObj()) {
			te.writeToNBT(tag);
		}

		return tag;
	}
}