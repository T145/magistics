package T145.magistics.blocks;

import net.minecraft.block.Block;
import thaumcraft.common.blocks.BlockJar;
import T145.magistics.Magistics;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockJarLarge extends BlockJar {
	public static final Block INSTANCE = new BlockJarLarge();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockJarLarge() {
		super();
		setBlockName("large_jar");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
	}
}