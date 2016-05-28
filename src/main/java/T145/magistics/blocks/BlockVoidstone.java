package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import T145.magistics.Magistics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVoidstone extends Block {
	public static final Block INSTANCE = new BlockVoidstone();

	public BlockVoidstone() {
		super(Material.rock);
		setBlockName("voidstone");
		setBlockTextureName("magistics:void_stone");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}
}