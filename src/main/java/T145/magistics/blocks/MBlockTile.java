package T145.magistics.blocks;

import T145.magistics.api.variants.IVariant;
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
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MBlockTile<T extends Enum<T> & IVariant> extends MBlock<T> implements ITileEntityProvider {

	protected boolean keepInventory = false;
	protected boolean spillQuintessence = true;

	protected final TileEntity[] tiles;

	public MBlockTile(String name, Material material, TileEntity... tiles) {
		this(name, material, Object.class, tiles);
	}

	public MBlockTile(String name, Material material, Class variants, TileEntity... tiles) {
		super(name, material, variants);
		this.tiles = tiles;
		this.isBlockContainer = true;

		for (TileEntity tile : tiles) {
			Class tileClass = tile.getClass();
			GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return tiles[meta];
	}

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

		if (tile != null) {
			if (tile instanceof IInventory && !keepInventory) {
				IInventory inv = (IInventory) tile;
				InventoryHelper.dropInventoryItems(world, pos, inv);
			}

			// if tile instanceof quinthandler && spillQuint && hasWorld then spill
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