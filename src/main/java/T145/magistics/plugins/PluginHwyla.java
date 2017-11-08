package T145.magistics.plugins;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.blocks.devices.BlockChestVoid;
import T145.magistics.tiles.devices.TileChestHungry;
import mcp.mobius.waila.addons.HUDHandlerBase;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@WailaPlugin(value = Magistics.MODID)
public class PluginHwyla extends HUDHandlerBase implements IWailaPlugin {

	@Override
	public List<String> getWailaBody(ItemStack stack, List<String> tooltip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (accessor.getBlock() instanceof BlockChestVoid) {
			accessor.getBlock().addInformation(stack, accessor.getPlayer(), tooltip, true);
		}

		if (accessor.getTileEntity() instanceof TileChestHungry) {
			TileChestHungry chest = (TileChestHungry) accessor.getTileEntity();

			if (chest.isEnderChest() && chest.getOwnerProfile() != null) {
				tooltip.add("Owner: " + chest.getOwnerProfile().getName());
			}
		}

		return tooltip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world,
			BlockPos pos) {
		return tile.writeToNBT(tag);
	}

	@Override
	public void register(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(this, TileChestHungry.class);
		registrar.registerNBTProvider(this, TileChestHungry.class);
	}
}