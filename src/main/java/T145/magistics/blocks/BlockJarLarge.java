package T145.magistics.blocks;

import T145.magistics.Magistics;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import thaumcraft.common.blocks.BlockJar;

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