package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.common.tiles.TileInfuser;
import T145.magistics.common.tiles.TileInfuserDark;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneApparatus extends BlockApparatus {
	public static boolean connected;

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
				return icon[4];
			case 2: case 3: case 4: case 5:
				return ((TileInfuser) ib.getTileEntity(i, j, k)).isConnectable(ForgeDirection.UNKNOWN) ? icon[1] : icon[2];
			}
		case 2:
			switch (side) {
			case 0:
				return icon[5];
			case 1:
				return icon[9];
			case 2: case 3: case 4: case 5:
				return ((TileInfuserDark) ib.getTileEntity(i, j, k)).isConnectable(ForgeDirection.UNKNOWN) ? icon[6] : icon[7];
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		setBlockBoundsBasedOnState(world, i, j, k);
		return super.getCollisionBoundingBoxFromPool(world, i, j, k);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess ib, int i, int j, int k) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
		setBlockBoundsBasedOnState(world, i, j, k);
		return super.getSelectedBoundingBoxFromPool(world, i, j, k);
	}
}