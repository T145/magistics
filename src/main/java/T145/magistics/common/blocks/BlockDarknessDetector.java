package T145.magistics.common.blocks;

import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarknessDetector extends BlockDaylightDetector {
	private IIcon icon[] = new IIcon[2];

	@Override
	public void func_149957_e(World world, int x, int y, int z) {
		if (!world.provider.hasNoSky) {
			int light = world.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) - world.skylightSubtracted;
			float f = world.getCelestialAngleRadians(1F);

			if (f < Math.PI)
				f += (0.0F - f) * 0.2F;
			else
				f += ((Math.PI * 2F) - f) * 0.2F;

			light = Math.round(light * MathHelper.cos(f));

			if (light < 0)
				light = 0;
			if (light > 15)
				light = 15;

			int dark = 15 - light;

			if (world.getBlockMetadata(x, y, z) != dark)
				world.setBlockMetadataWithNotify(x, y, z, dark, 3);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? icon[0] : icon[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:moonlight_detector_top");
		icon[1] = r.registerIcon("daylight_detector_side");
	}
}