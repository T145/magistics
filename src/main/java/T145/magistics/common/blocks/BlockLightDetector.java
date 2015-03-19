package T145.magistics.common.blocks;

import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightDetector extends BlockDaylightDetector {
	private IIcon icon[] = new IIcon[2];

	@Override
	public void func_149957_e(World world, int i, int j, int k) {
		if (!world.isRemote)
			world.setBlockMetadataWithNotify(i, j, k, Math.min(Math.max(world.getBlockLightValue(i, j, k), 0), 15), 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? icon[0] : icon[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:light_detector_top");
		icon[1] = r.registerIcon("daylight_detector_side");
	}
}