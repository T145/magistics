package T145.magistics.plugins;

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
import T145.magistics.items.ItemDummy;
import T145.magistics.plugins.core.Plugin;
import T145.magistics.tiles.TileChestHungryEnder;
import cpw.mods.fml.common.event.FMLInterModComms;

public class PluginWaila extends Plugin implements IWailaDataProvider {
	private static final PluginWaila INSTANCE = new PluginWaila(active);

	public PluginWaila(boolean enable) {
		super("Waila", enable);
	}

	@Override
	public void preInit() {}

	@Override
	public void init() {
		FMLInterModComms.sendMessage("Waila", "register", getClass().getCanonicalName() + ".callRegistrar");
	}

	public static void callRegistrar(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(INSTANCE, TileChestHungryEnder.class);

		registrar.registerStackProvider(INSTANCE, BlockArcaneFurnace.class);
		registrar.registerStackProvider(INSTANCE, BlockArcaneDoor.class);

		registrar.registerStackProvider(INSTANCE, BlockInfuser.class);
		registrar.registerStackProvider(INSTANCE, BlockEntropicDispenser.class);
	}

	@Override
	public void postInit() {}

	public static boolean compareByClass(Class class1, Class class2) {
		return (class1 != null && class2 != null) ? class1.getName().equalsIgnoreCase(class2.getName()) : false;
	}

	public static boolean compareTileEntityByClass(TileEntity tile, Class tileClass) {
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

		if (block instanceof BlockInfuser) {
			return new ItemStack(BlockInfuser.INSTANCE, 1, metadata);
		}

		if (block instanceof BlockEntropicDispenser) {
			return new ItemStack(BlockEntropicDispenser.INSTANCE, 1, 3);
		}

		return accessor.getStack();
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> toolTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return toolTip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> toolTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity tile = accessor.getTileEntity();
		Block block = accessor.getBlock();
		int metadata = accessor.getMetadata();

		if (block.hasTileEntity(metadata) && tile.hasWorldObj()) {
			if (compareTileEntityByClass(tile, TileChestHungryEnder.class)) {
				TileChestHungryEnder chest = (TileChestHungryEnder) tile;
				toolTip.add("Owner: " + chest.owner);
			}
		}

		return toolTip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> toolTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return toolTip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
		if (te.hasWorldObj()) {
			te.writeToNBT(tag);
		}

		return tag;
	}
}