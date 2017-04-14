package T145.magistics.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class MBlockTile extends MBlock implements ITileEntityProvider {

	protected static boolean keepInventory = false;
	protected static boolean spillQuintessence = true;

	public MBlockTile(String name, Material material) {
		super(name, material);
	}

	public MBlockTile(String name, Material material, Class variants) {
		super(name, material, variants);
		this.isBlockContainer = true;
	}

	@Override
	public abstract TileEntity createNewTileEntity(World world, int meta);

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile.hasWorld()) {
			if (tile instanceof IInventory && !keepInventory) {
				IInventory inv = (IInventory) tile;
				InventoryHelper.dropInventoryItems(world, pos, inv);
			}

			// if tile instanceof quintessencehandler && spillQuint then spill quints
		}

		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
		world.updateComparatorOutputLevel(pos, this);
	}

	@Override
	public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
		super.eventReceived(state, world, pos, id, param);
		TileEntity tile = world.getTileEntity(pos);
		return tile == null ? false : tile.receiveClientEvent(id, param);
	}
}