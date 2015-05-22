package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalMachine extends BlockCrystalStorage {
	public static enum Types {
		fabricator, fire_basin, liquid_detector, liquid_void, xy_freezer, xy_soil, xy_water
	}
	
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[Types.values().length];

	public BlockCrystalMachine() {
		setBlockName("crystal_storage:machine");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.registerBlockIcons(r);
	}

	/*@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon;
	}*/

	@Override
	public int getRenderType() {
		return renderID;
	}
}