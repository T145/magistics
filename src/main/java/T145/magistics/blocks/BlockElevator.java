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
import T145.magistics.tiles.TileElevator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElevator extends BlockContainer {
	public static final Block INSTANCE = new BlockElevator();

	public IIcon bottom;

	public BlockElevator() {
		super(Material.rock);
		this.setBlockName("elevator");
		this.setCreativeTab(Magistics.tabMagistics);
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
			return Blocks.wool.getIcon(side, metadata);
		default:
			return blockIcon;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileElevator();
	}
}