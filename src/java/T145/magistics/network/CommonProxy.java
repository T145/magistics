package T145.magistics.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.blocks.BlockNetherFurnace;
import T145.magistics.containers.ContainerModifiedFurnace;
import T145.magistics.tiles.TileNetherFurnace;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IGuiHandler {
	public abstract void registerRenderInformation();

	public void registerObjectInformation() {
		GameRegistry.registerTileEntity(TileNetherFurnace.class, TileNetherFurnace.class.getSimpleName());
		GameRegistry.registerBlock(BlockNetherFurnace.instanceActive, BlockNetherFurnace.instanceActive.getUnlocalizedName() + "_on");
		GameRegistry.registerBlock(BlockNetherFurnace.instanceInactive, BlockNetherFurnace.instanceInactive.getUnlocalizedName() + "_off");
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			return new ContainerModifiedFurnace(player.inventory, (TileNetherFurnace) world.getTileEntity(x, y, z));
		default:
			return null;
		}
	}
}