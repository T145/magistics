package T145.magistics.common.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UNKNOWN;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import static thaumcraft.client.renderers.block.BlockRenderer.W1;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.Magistics;
import T145.magistics.common.tiles.TileInfuser;
import T145.magistics.common.tiles.TileInfuserDark;
import T145.magistics.common.tiles.TileSeal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneApparatus extends BlockApparatus {
	public static enum Types {
		arcane_seal(0),
		infuser(1),
		infuser_dark(2),
		enchanter_occultic(3),
		enchanter_thaumic(4),
		eldritch_stone(5),
		everfull_urn(6);

		private Types(int metadata) {}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	public BlockStoneApparatus() {
		super(Material.rock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon = new IIcon[] {
				r.registerIcon("magistics:infuser_bottom"),
				r.registerIcon("magistics:infuser_side_connected"),
				r.registerIcon("magistics:infuser_side"),
				r.registerIcon("magistics:infuser_top_clear"),
				r.registerIcon("magistics:infuser_top"),

				r.registerIcon("magistics:dark_infuser_bottom"),
				r.registerIcon("magistics:dark_infuser_side_connected"),
				r.registerIcon("magistics:dark_infuser_side"),
				r.registerIcon("magistics:dark_infuser_top_clear"),
				r.registerIcon("magistics:dark_infuser_top"),

				r.registerIcon("magistics:enchanter_bottom"),
				r.registerIcon("magistics:enchanter_occultic_side"),
				r.registerIcon("magistics:enchanter_occultic_tank_side"),
				r.registerIcon("magistics:enchanter_occultic_tank_top"),
				r.registerIcon("magistics:enchanter_occultic_top"),
				r.registerIcon("magistics:enchanter_thaumic_side"),
				r.registerIcon("magistics:enchanter_thaumic_top"),

				r.registerIcon("magistics:eldritch_stone"),

				r.registerIcon("magistics:urn_everfull_bottom"),
				r.registerIcon("magistics:urn_everfull_side"),
				r.registerIcon("magistics:urn_everfull_top")
		};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess ib, int i, int j, int k, int side) {
		switch (ib.getBlockMetadata(i, j, k)) {
		case 1:
			switch (side) {
			case 0:
				return icon[0];
			case 1:
				return icon[3];
			case 2: case 3: case 4: case 5:
				return ((TileInfuser) ib.getTileEntity(i, j, k)).isConnectable(UNKNOWN) ? icon[1] : icon[2];
			}
		case 2:
			switch (side) {
			case 0:
				return icon[5];
			case 1:
				return icon[8];
			case 2: case 3: case 4: case 5:
				return ((TileInfuserDark) ib.getTileEntity(i, j, k)).isConnectable(UNKNOWN) ? icon[6] : icon[7];
			}
		case 3:
			switch (side) {
			case 0:
				return icon[10];
			case 1:
				return icon[16];
			case 2: case 3: case 4: case 5:
				return icon[15];
			}
		case 4:
			switch (side) {
			case 0:
				return icon[10];
			case 1:
				return icon[14];
			case 2: case 3: case 4: case 5:
				return icon[11];
			}
		case 5:
			return icon[17];
		case 6:
			switch (side) {
			case 0:
				return icon[18];
			case 1:
				return icon[20];
			case 2: case 3: case 4: case 5:
				return icon[19];
			}
		default:
			return blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs t, List l) {
		for (Types type : Types.values())
			l.add(new ItemStack(i, 1, type.ordinal()));
	}

	@Override
	public int getRenderType() {
		return Magistics.proxy.renderID[3];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		setBlockBoundsBasedOnState(world, i, j, k);
		return super.getCollisionBoundingBoxFromPool(world, i, j, k);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess ib, int i, int j, int k) {
		TileEntity tile = ib.getTileEntity(i, j, k);

		switch (ib.getBlockMetadata(i, j, k)) {
		case 0:
			if (tile != null)
				switch (((TileSeal) tile).orientation.ordinal()) {
				case 0:
					setBlockBounds(0.3F, 1.0F - W1, 0.3F, 0.7F, 1.0F, 0.7F);
					break;
				case 1:
					setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, W1, 0.7F);
					break;
				case 2:
					setBlockBounds(0.3F, 0.3F, 1.0F - W1, 0.7F, 0.7F, 1.0F);
					break;
				case 3:
					setBlockBounds(0.3F, 0.3F, 0.0F, 0.7F, 0.7F, W1);
					break;
				case 4:
					setBlockBounds(1.0F - W1, 0.3F, 0.3F, 1.0F, 0.7F, 0.7F);
					break;
				case 5:
					setBlockBounds(0.0F, 0.3F, 0.3F, W1, 0.7F, 0.7F);
					break;
				}
			break;
		case 1: case 2:
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - W1, 1.0F);
			break;
		case 4:
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
			break;
		case 6:
			setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5625F, 0.875F);
			break;
		default:
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			break;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
		setBlockBoundsBasedOnState(world, i, j, k);
		return super.getSelectedBoundingBoxFromPool(world, i, j, k);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		if (world.getBlockMetadata(i, j, k) == 0)
			return world.isSideSolid(i - 1, j, k, EAST) || world.isSideSolid(i + 1, j, k, WEST) || world.isSideSolid(i, j, k - 1, SOUTH) || world.isSideSolid(i, j, k + 1, NORTH);
		else
			return world.getBlock(i, j, k).isReplaceable(world, i, j, k);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block b) {
		if (world.getBlockMetadata(i, j, k) == 0 && !super.canPlaceBlockAt(world, i, j, k)) {
			dropBlockAsItem(world, i, j, k, 0, 0);
			world.setBlockToAir(i, j, k);
		}
		super.onNeighborBlockChange(world, i, j, k, b);
	}
}