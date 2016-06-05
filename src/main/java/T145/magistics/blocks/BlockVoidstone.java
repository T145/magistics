package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import T145.magistics.Magistics;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVoidstone extends Block {
	public static final Block INSTANCE = new BlockVoidstone();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockVoidstone() {
		super(Material.rock);
		setBlockName("voidstone");
		setBlockTextureName("magistics:void_stone");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
	}
}