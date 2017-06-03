package T145.magistics.tiles.devices;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileVoidBorder extends TileEntity {

	@SideOnly(Side.CLIENT)
	public boolean shouldRenderFace(EnumFacing facing) {
		return getBlockType().getDefaultState().shouldSideBeRendered(world, pos, facing);
	}
}