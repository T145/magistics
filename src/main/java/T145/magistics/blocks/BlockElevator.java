package T145.magistics.blocks;

import T145.magistics.Magistics;
import T145.magistics.tiles.TileElevator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockElevator extends BlockContainer {
	public static final Block INSTANCE = new BlockElevator();

	public BlockElevator() {
		super(Material.rock);
		setBlockName("elevator");
		setBlockTextureName("magistics:elevator");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		switch (side) {
		case 0:
			return BlockInfusionWorkbench.base[0];
		case 1:
			return Blocks.quartz_block.getIcon(side, 1);
		default:
			return blockIcon;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileElevator();
	}
}