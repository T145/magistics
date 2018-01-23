package T145.magistics.common.blocks;

import javax.annotation.Nonnull;

import T145.magistics.api.front.IModelProvider;
import T145.magistics.client.render.block.RenderPedestal;
import T145.magistics.common.blocks.base.BlockBase;
import T145.magistics.common.lib.InventoryIO;
import T145.magistics.common.tiles.TilePedestal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPedestal extends BlockBase implements IModelProvider, ITileEntityProvider {

	public BlockPedestal() {
		super("pedestal", Material.ROCK, MapColor.STONE, false);
		setHardness(2F);
		setResistance(20F);
		setSoundType(SoundType.STONE);
		setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TilePedestal();
	}

	@Override
	public void initModel() {
		IModelProvider.registerBlockModel(this, 0, "inventory");
		IModelProvider.registerTileRenderer(TilePedestal.class, new RenderPedestal());
	}

	private void dropItem(World world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);

		if (te instanceof TilePedestal) {
			TilePedestal tile = (TilePedestal) te;
			ItemStack stack = tile.getInventory().getStackInSlot(0);

			if (!stack.isEmpty()) {
				InventoryIO.spawnEntityItem(world, stack, pos.getX(), pos.getY() + 0.8, pos.getZ());
				tile.getInventory().setStackInSlot(0, ItemStack.EMPTY);
			}
		}
	}

	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		dropItem(world, pos);
		super.breakBlock(world, pos, state);
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if (!world.isRemote) {
			dropItem(world, pos);
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 8);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);

			if (!(te instanceof TilePedestal)) {
				return true;
			}

			TilePedestal tile = (TilePedestal) te;
			ItemStack item = tile.getObservableStack();
			ItemStack stack = player.getHeldItem(hand);

			if (stack.isEmpty() && !item.isEmpty()) {
				world.notifyBlockUpdate(pos, state, state, 8);
			} else if (!stack.isEmpty() && item.isEmpty()) {
				tile.setObservableStack(stack.splitStack(1));

				if (stack.getCount() <= 0) {
					player.setHeldItem(hand, ItemStack.EMPTY);
				}

				world.notifyBlockUpdate(pos, state, state, 8);
			}
		}

		return true;
	}
}