package T145.magistics.blocks.devices;

import T145.magistics.api.logic.IFacing;
import T145.magistics.blocks.MBlock;
import T145.magistics.config.ConfigMain;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.world.teleporters.TeleporterVoidWorld;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChestVoid extends MBlock {

	public BlockChestVoid() {
		super("chest_void", Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(2000.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestVoid();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return Blocks.ENDER_CHEST.getBoundingBox(state, source, pos);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasCustomBreakingProgress(IBlockState state) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof TileChestVoid) {
			TileChestVoid chest = (TileChestVoid) tile;

			if (chest.isOpen()) {
				chest.closeChest();
			} else {
				chest.openChest();
			}
		}

		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);

		if (tile instanceof IFacing) {
			((IFacing) tile).setFacing(state, EnumFacing.getDirectionFromEntityLiving(pos, entity));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		TileEntity tile = world.getTileEntity(pos);

		if (!world.isRemote && tile instanceof TileChestVoid && entity instanceof EntityPlayer && entity.getEntityBoundingBox().minY <= pos.getY() + 1D) {
			TileChestVoid chest = (TileChestVoid) tile;

			if (chest.isOpen()) {
				if (world.provider.getDimension() != ConfigMain.voidDimensionId) {
					FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, ConfigMain.voidDimensionId, new TeleporterVoidWorld(entity.getServer().worldServerForDimension(ConfigMain.voidDimensionId), pos));
				} else {
					FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, 0, new TeleporterVoidWorld(entity.getServer().worldServerForDimension(0), pos));
				}
			}
		}
	}
}