package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalStorageReinforced extends BlockCrystalStorage {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[2];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
		icon[0] = r.registerIcon("magistics:decor/basic");
		icon[1] = r.registerIcon("magistics:decor/brick");
	}

	@Override
	public int getRenderType() {
		return renderID;
	}
}