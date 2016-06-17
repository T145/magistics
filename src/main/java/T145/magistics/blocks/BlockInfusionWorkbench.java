package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.tiles.TileInfusionWorkbench;

public class BlockInfusionWorkbench extends BlockContainer {
	public static final Block INSTANCE = new BlockInfusionWorkbench();

	public IIcon[] icon = new IIcon[16];

	public BlockInfusionWorkbench() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileInfusionWorkbench();
	}
}