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
	public void func_149957_e(World world, int i, int j, int k) {
		int darknessLevel = 15 - (world.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) - world.skylightSubtracted);
		float f = world.getCelestialAngleRadians(1F);

		if (f < Math.PI)
			f += (0.0F - f) * 0.2F;
		else
			f += ((Math.PI * 2F) - f) * 0.2F;

		darknessLevel = Math.round(darknessLevel * MathHelper.cos(f));

		if (darknessLevel < 0)
			darknessLevel = 0;
		if (darknessLevel > 15)
			darknessLevel = 15;

		if (world.getBlockMetadata(i, j, k) != darknessLevel)
			world.setBlockMetadataWithNotify(i, j, k, darknessLevel, 3);
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