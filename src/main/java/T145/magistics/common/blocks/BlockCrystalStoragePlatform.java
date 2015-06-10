package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.util.BlockCoord;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalStoragePlatform extends BlockCrystalStorage {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon;

	// eliminates the calculation of UNKNOWN, and also allows order control
	private static final ForgeDirection[] sides = new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN };

	public BlockCrystalStoragePlatform() {
		setBlockName("crystal_storage.platform");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.registerBlockIcons(r);
		icon = r.registerIcon("magistics:decor/platform");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon;
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		ItemStack held = player.inventory.mainInventory[player.inventory.currentItem];

		if (held == null || held.getItem() == null || Block.getBlockFromItem(held.getItem()) == null)
			return;

		Block hb = Block.getBlockFromItem(held.getItem());

		if (held != null && hb != null && hb.equals(this) && held.getItemDamage() == world.getBlockMetadata(x, y, z)) {
			while (world.getBlock(x, y, z).equals(this))
				y++;

			if (checkStay(world, x, y, z, held.getItemDamage()) && (world.isAirBlock(x, y, z))) {
				world.setBlock(x, y, z, this, held.getItemDamage(), 3);

				if (!player.capabilities.isCreativeMode) {
					held.stackSize--;

					if (held.stackSize <= 0)
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}
		}
	}

	@Override
	public boolean canReplace(World world, int x, int y, int z, int side, ItemStack stack) {
		return checkStay(world, x, y, z, stack.getItemDamage());
	}

	public int getOverhang(int meta) {
		switch (meta) {
		case 0:
			return 4;
		case 1:
			return 8;
		case 2:
			return 12;
		case 3:
			return 16;
		default:
			return 0;
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return checkStay(world, x, y, z, world.getBlockMetadata(x, y, z));
	}

	private boolean checkStay(World world, int x, int y, int z, int meta) {
		BlockCoord c = new BlockCoord(x, y, z);

		if (isRooted(world, x, y, z, meta))
			return true;

		for (ForgeDirection d : sides) {
			BlockCoord t = c.copy().offset(d.ordinal());

			if (t.getBlock(world) instanceof BlockCrystalStoragePlatform) {
				if (t.blockEquals(world, this, meta)) {
					if (isRooted(world, t.x, t.y, t.z, meta))
						return true;

					for (BlockCoord match : c.getRadiusMatches(world, getOverhang(meta), this, meta)) {
						if (isRooted(world, match.x, match.y, match.z, meta) && c.isConnected(world, match, this, meta) && c.getDistance(match) <= getOverhang(meta))
							return true;
					}
				}

				return false;
			}
		}

		return false;
	}

	private boolean isRooted(World world, int x, int y, int z, int meta) {
		for (int i = y - 1; i > 0; i--) {
			if (world.isSideSolid(x, i, z, ForgeDirection.UP)) {
				if (world.getBlock(x, i, z).equals(this)) {
					if (world.getBlockMetadata(x, i, z) == meta)
						continue;

					return false;
				}

				return true;
			}

			return false;
		}

		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
}