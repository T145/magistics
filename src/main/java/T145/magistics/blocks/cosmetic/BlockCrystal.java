package T145.magistics.blocks.cosmetic;

import T145.magistics.api.variants.blocks.EnumCrystal;
import T145.magistics.blocks.MBlock;
import T145.magistics.tiles.cosmetic.TileCrystal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

public class BlockCrystal extends MBlock<EnumCrystal> {

	public BlockCrystal() {
		super("crystal", Material.GLASS, EnumCrystal.class);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileCrystal();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
}