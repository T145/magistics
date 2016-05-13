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
import thaumcraft.common.blocks.BlockArcaneFurnace;
import thaumcraft.common.tiles.TileArcaneFurnace;
import T145.magistics.plugins.PluginHandler.Plugin;
import T145.magistics.tiles.TileChestHungryEnder;
import cpw.mods.fml.common.event.FMLInterModComms;

public class PluginWaila extends Plugin implements IWailaDataProvider {
	private static final PluginWaila INSTANCE = new PluginWaila();

	public PluginWaila() {
		super("Waila");
	}

	@Override
	public void preInit() {}

	@Override
	public void init() {
		FMLInterModComms.sendMessage("Waila", "register", getClass().getCanonicalName() + ".callRegistrar");
	}

	public static void callRegistrar(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(INSTANCE, TileChestHungryEnder.class);
		registrar.registerBodyProvider(INSTANCE, TileArcaneFurnace.class);
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

		if (block instanceof BlockArcaneFurnace) {
		}

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