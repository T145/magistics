package T145.magistics.blocks.devices;

import T145.magistics.blocks.MBlock;
import T145.magistics.tiles.devices.MTileChunkLoader;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MBlockChunkLoader<T extends Enum<T> & IStringSerializable> extends MBlock<T> {

	public MBlockChunkLoader(String name, Material material, Class variants) {
		super(name, material, variants);
	}

	public MBlockChunkLoader(String name, Material material) {
		super(name, material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new MTileChunkLoader();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tile = worldIn.getTileEntity(pos);

		if (tile instanceof MTileChunkLoader) {
			MTileChunkLoader chunkLoader = (MTileChunkLoader) tile;
			chunkLoader.onBlockBreak();
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof MTileChunkLoader) {
			MTileChunkLoader chunkLoader = (MTileChunkLoader) tile;
			chunkLoader.forceLoad();
			return true;
		}

		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof MTileChunkLoader) {
			MTileChunkLoader chunkLoader = (MTileChunkLoader) tile;
			chunkLoader.onBlockPlaced();

			if (placer instanceof EntityPlayer) {
				chunkLoader.setPlayer((EntityPlayer) placer);
			}
		}
	}
}