package T145.magistics.blocks;

import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.api.InventoryHelper;
import T145.magistics.tiles.TileEntropicDispenser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockEntropicDispenser extends BlockContainer {
	public static final Block INSTANCE = new BlockEntropicDispenser();
	protected Random rand = new Random();

	protected IIcon topIcon;
	protected IIcon horizontalIcon;
	protected IIcon verticalIcon;

	public BlockEntropicDispenser() {
		super(Material.rock);
		setCreativeTab(Magistics.tabMagistics);
		setHardness(3.5F);
		setStepSound(Block.soundTypePiston);
		setBlockName("entropic_dispenser");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		setMeta(world, x, y, z);

		TileEntropicDispenser tile = (TileEntropicDispenser) world.getTileEntity(x, y, z);

		if (tile != null) {
			tile.func_146018_a(tile.getInventoryName());
		}
	}

	private void setMeta(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block block = world.getBlock(x, y, z - 1);
			Block block1 = world.getBlock(x, y, z + 1);
			Block block2 = world.getBlock(x - 1, y, z);
			Block block3 = world.getBlock(x + 1, y, z);
			byte meta = 3;

			if ((block.func_149730_j()) && (!block1.func_149730_j())) {
				meta = 4;
			}

			if ((block1.func_149730_j()) && (!block.func_149730_j())) {
				meta = 3;
			}

			if ((block2.func_149730_j()) && (!block3.func_149730_j())) {
				meta = 6;
			}

			if ((block3.func_149730_j()) && (!block2.func_149730_j())) {
				meta = 5;
			}

			if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlockMetadataWithNotify(x, y, z, meta | 0x8, 4);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, meta, 2);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("furnace_side");
		topIcon = r.registerIcon("furnace_top");
		horizontalIcon = r.registerIcon("magistics:entropic_dispenser_front_horizontal");
		verticalIcon = r.registerIcon("magistics:entropic_dispenser_front_vertical");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta == 0) {
			if (side == 3) {
				return horizontalIcon;
			}

			if (side == 0 || side == 1) {
				return topIcon;
			}

			return blockIcon;
		}

		if ((meta & 0x8) != 0) {
			meta &= 0xFFFFFFF7;
		}

		meta = Math.max(meta - 1, 0);

		if (side == meta) {
			if (side == 0 || side == 1) {
				return verticalIcon;
			}

			return horizontalIcon;
		}

		if (side == 0 || side == 1 || meta == 0 || meta == 1) {
			return topIcon;
		}

		return blockIcon;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntropicDispenser tile = (TileEntropicDispenser) world.getTileEntity(x, y, z);

			if (tile != null) {
				player.func_146102_a(tile);
			}
		}

		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		int meta = world.getBlockMetadata(x, y, z);
		boolean isPoweredByRedstone = world.isBlockIndirectlyGettingPowered(x, y, z);
		boolean isPoweredByMeta = (meta & 0x8) != 0;

		if (isPoweredByRedstone && !isPoweredByMeta) {
			world.setBlockMetadataWithNotify(x, y, z, meta | 0x8, 4);
		} else if (!isPoweredByRedstone && isPoweredByMeta) {
			world.setBlockMetadataWithNotify(x, y, z, meta & 0xFFFFFFF7, 4);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par1) {
		return new TileEntropicDispenser();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int meta = BlockPistonBase.determineOrientation(world, x, y, z, player) + 1;

		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			world.setBlockMetadataWithNotify(x, y, z, meta | 0x8, 4);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}

		if (stack.hasDisplayName()) {
			((TileEntropicDispenser) world.getTileEntity(x, y, z)).func_146018_a(stack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		InventoryHelper.emptyInventory(world, x, y, z);
	}

	public static IPosition getPosition(IBlockSource block) {
		EnumFacing enumfacing = getFrontSide(Math.max(block.getBlockMetadata() - 1, 0));
		double xx = block.getX() + 0.7D * enumfacing.getFrontOffsetX();
		double yy = block.getY() + 0.7D * enumfacing.getFrontOffsetY();
		double zz = block.getZ() + 0.7D * enumfacing.getFrontOffsetZ();
		return new PositionImpl(xx, yy, zz);
	}

	public static EnumFacing getFrontSide(int meta) {
		return EnumFacing.getFront(meta & 0x7);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}
}