package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.Magistics;
import T145.magistics.tiles.TileWarpSentinel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWarpSentinel extends BlockContainer {
	public static final Block INSTANCE = new BlockWarpSentinel();

	public IIcon bottom;

	public BlockWarpSentinel() {
		super(Material.rock);
		setBlockName("elevator");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		bottom = r.registerIcon("magistics:infusion_workbench/base/0");
		blockIcon = r.registerIcon("magistics:elevator");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		switch (side) {
		case 0:
			return bottom;
		case 1:
			return Blocks.quartz_block.getIcon(side, 1);
		default:
			return blockIcon;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileWarpSentinel();
	}
}