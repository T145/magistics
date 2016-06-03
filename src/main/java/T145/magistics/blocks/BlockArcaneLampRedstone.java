package T145.magistics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.Magistics;
import T145.magistics.tiles.TileArcaneLampRedstone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArcaneLampRedstone extends BlockContainer {
	public static final Block ACTIVE = new BlockArcaneLampRedstone(true);
	public static final Block INACTIVE = new BlockArcaneLampRedstone(false).setCreativeTab(Magistics.tabMagistics);

	private boolean active;

	public IIcon[] icon = new IIcon[4];

	public BlockArcaneLampRedstone(boolean isActive) {
		super(Material.iron);
		active = isActive;

		setHardness(3F);
		setResistance(17F);
		setStepSound(soundTypeMetal);
		setBlockName("arcane_redstone_lamp");
		setBlockBounds(BlockRenderer.W4, BlockRenderer.W2, BlockRenderer.W4, BlockRenderer.W12, BlockRenderer.W14, BlockRenderer.W12);
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icon[0] = register.registerIcon("magistics:lamp_redstone_side_on");
		icon[1] = register.registerIcon("magistics:lamp_redstone_top_on");
		icon[2] = register.registerIcon("magistics:lamp_redstone_side_off");
		icon[3] = register.registerIcon("magistics:lamp_redstone_top_off");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (active) {
			if (side <= 1) {
				return icon[1];
			} else {
				return icon[0];
			}
		} else {
			if (side <= 1) {
				return icon[3];
			} else {
				return icon[2];
			}
		}
	}

	public void removeLights(TileEntity tile) {
		if (tile != null && tile instanceof TileArcaneLampRedstone) {
			TileArcaneLampRedstone lamp = (TileArcaneLampRedstone) tile;
			lamp.removeLights();
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int side) {
		removeLights(world.getTileEntity(x, y, z));
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return active ? 15 : 0;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			if (active && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.scheduleBlockUpdate(x, y, z, this, 4);
			} else if (!active && world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, ACTIVE, 0, 2);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			if (active && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.scheduleBlockUpdate(x, y, z, this, 4);
			} else if (!active && world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, ACTIVE, 0, 2);
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote && active && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
			world.setBlock(x, y, z, INACTIVE, 0, 2);
			removeLights(world.getTileEntity(x, y, z));
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileArcaneLampRedstone();
	}
}