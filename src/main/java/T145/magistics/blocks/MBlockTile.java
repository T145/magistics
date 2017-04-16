package T145.magistics.blocks;

import T145.magistics.api.magic.IQuintessenceHandler;
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

public abstract class MBlockTile<T extends Enum<T> & IVariant> extends MBlock<T> implements ITileEntityProvider {

	protected boolean keepInventory;
	protected boolean spillQuintessence = true;

	public MBlockTile(String name, Material material, Class variants, Class... tileClasses) {
		super(name, material, variants);
		this.isBlockContainer = true;

		for (Class tileClass : tileClasses) {
			GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
		}
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

		if (tile != null) {
			if (tile instanceof IInventory && !keepInventory) {
				InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
			}

			if (tile instanceof IQuintessenceHandler && spillQuintessence && !world.isRemote) {
				// spill quints
			}
		}

		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
		world.updateComparatorOutputLevel(pos, this);
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		super.eventReceived(state, worldIn, pos, id, param);
		TileEntity tile = worldIn.getTileEntity(pos);
		return tile == null ? false : tile.receiveClientEvent(id, param);
	}
}