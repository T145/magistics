package T145.magistics.common.blocks.base;

import T145.magistics.core.Magistics;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block {

	protected final boolean isNormalized;

	public BlockBase(String name, Material material, MapColor color, boolean isNormalized) {
		super(material, color);
		this.isNormalized = isNormalized;
		setRegistryName(new ResourceLocation(Magistics.ID, name));
		setUnlocalizedName(Magistics.ID + '.' + name);
		setCreativeTab(Magistics.TAB);
	}

	public BlockBase(String name, Material material, boolean isNormalized) {
		this(name, material, material.getMaterialMapColor(), isNormalized);
	}

	public BlockBase(String name, Material material, MapColor color) {
		this(name, material, color, true);
	}

	public BlockBase(String name, Material material) {
		this(name, material, true);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return isNormalized;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return isNormalized;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return isNormalized;
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return 0;
	}
}