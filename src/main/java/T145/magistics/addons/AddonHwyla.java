package T145.magistics.addons;

import java.util.List;

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

@WailaPlugin
public class AddonHwyla extends HUDHandlerBase implements IWailaPlugin {

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		if (accessor.getTileEntity() instanceof TileChestHungry) {
			TileChestHungry chest = (TileChestHungry) accessor.getTileEntity();

			if (chest.isEnderChest() && chest.getOwnerProfile() != null) {
				currenttip.add("Owner: " + chest.getOwnerProfile().getName());
			}
		}

		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
		return te.writeToNBT(tag);
	}

	@Override
	public void register(IWailaRegistrar registrar) {
		registrar.registerBodyProvider(this, TileChestHungry.class);
		registrar.registerNBTProvider(this, TileChestHungry.class);
	}
}