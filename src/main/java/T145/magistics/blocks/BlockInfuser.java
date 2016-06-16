package T145.magistics.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.Magistics;
import T145.magistics.lib.InventoryHelper;
import T145.magistics.tiles.TileInfuser;
import T145.magistics.tiles.TileInfuserDark;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfuser extends BlockContainer {
	public static final Block INSTANCE = new BlockInfuser();

	private int renderID = RenderingRegistry.getNextAvailableRenderId();
	private float maxRenderY = 1F - BlockRenderer.W1;

	public IIcon[] light_icon = new IIcon[5];
	public IIcon[] dark_icon = new IIcon[5];

	public BlockInfuser() {
		super(Material.rock);
		setBlockName("infuser");
		setCreativeTab(Magistics.tabMagistics);

		setHardness(2F);
		setResistance(15F);
		setStepSound(soundTypeStone);
	}

	public boolean isDark(int metadata) {
		if (metadata == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		light_icon[0] = r.registerIcon("magistics:infuser_bottom");
		light_icon[1] = r.registerIcon("magistics:infuser_top_clear");
		light_icon[2] = r.registerIcon("magistics:infuser_side");
		light_icon[3] = r.registerIcon("magistics:infuser_top");
		light_icon[4] = r.registerIcon("magistics:infuser_side_connected");

		dark_icon[0] = r.registerIcon("magistics:dark_infuser_bottom");
		dark_icon[1] = r.registerIcon("magistics:dark_infuser_top_clear");
		dark_icon[2] = r.registerIcon("magistics:dark_infuser_side");
		dark_icon[3] = r.registerIcon("magistics:dark_infuser_top");
		dark_icon[4] = r.registerIcon("magistics:dark_infuser_side_connected");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (isDark(metadata)) {
			if (side > 1) {
				return dark_icon[2];
			} else if (side == 1) {
				return dark_icon[3];
			} else {
				return dark_icon[side];
			}
		} else {
			if (side > 1) {
				return light_icon[2];
			} else if (side == 1) {
				return light_icon[3];
			} else {
				return light_icon[side];
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile instanceof TileInfuser) {
			TileInfuser infuser = (TileInfuser) tile;

			if (isDark(metadata)) {
				if (side > 1) {
					if (infuser.hasConnectedSide(side)) {
						return dark_icon[4];
					} else {
						return dark_icon[2];
					}
				} else {
					return dark_icon[side];
				}
			} else {
				if (side > 1) {
					if (infuser.hasConnectedSide(side)) {
						return light_icon[4];
					} else {
						return light_icon[2];
					}
				} else {
					return light_icon[side];
				}
			}
		} else {
			return blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 2; i++) {
			list.add(new ItemStack(item, 1, i));
		}
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

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBounds(0F, 0F, 0F, 1F, maxRenderY, 1F);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBounds(0F, 0F, 0F, 1F, maxRenderY, 1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB.getBoundingBox(0D, 0D, 0D, 1D, maxRenderY, 1D);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if (isDark(metadata)) {
			return new TileInfuserDark();
		} else {
			return new TileInfuser();
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof TileInfuser) {
			((TileInfuser) tile).facing = BlockPistonBase.determineOrientation(world, x, y, z, player);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Magistics.instance, world.getBlockMetadata(x, y, z), world, x, y, z);
		}

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof IInventory) {
			InventoryHelper.emptyInventory(world, x, y, z);
		}
	}
}