package T145.magistics.blocks.devices;

import java.util.ArrayList;
import java.util.List;

import T145.magistics.blocks.MBlock;
import T145.magistics.init.ModBlocks;
import T145.magistics.lib.managers.TeleportationManager;
import T145.magistics.tiles.devices.TileChestVoid;
import T145.magistics.world.data.WorldDataVoidChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("id")) {
			int id = stack.getTagCompound().getInteger("id");

			if (id > -1) {
				tooltip.add(colorKeyValue(I18n.format("tooltip.magistics.chest.id", "#" + id)));
			}
		}
	}

	private String colorKeyValue(String input) {
		return colorKeyValue(input, TextFormatting.LIGHT_PURPLE, TextFormatting.AQUA);
	}

	private String colorKeyValue(String input, TextFormatting key, TextFormatting value) {
		if(!input.contains(":")) {
			return input;
		}

		String[] parts = input.split(":", 2);
		return key + parts[0] + ":" + TextFormatting.RESET + value + parts[1] + TextFormatting.RESET;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<>();
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (world.isRemote) {
			super.breakBlock(world, pos, state);
			return;
		}

		if (!(world.getTileEntity(pos) instanceof TileChestVoid)) {
			return;
		}

		TileChestVoid chest = (TileChestVoid) world.getTileEntity(pos);
		WorldDataVoidChest.INSTANCE.removeVoidChestPosition(chest.id);

		spawnItemWithNBT(world, pos, chest);

		world.removeTileEntity(pos);
		super.breakBlock(world, pos, state);
	}

	public static void spawnItemWithNBT(World world, BlockPos pos, TileChestVoid te) {
		if (world.isRemote) {
			return;
		}

		ItemStack stack = new ItemStack(ModBlocks.chestVoid, 1);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("id", te.id);
		stack.setTagCompound(compound);

		EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY() + 0.5, pos.getZ(), stack);
		entityItem.lifespan = 1200;
		entityItem.setPickupDelay(10);

		float motionMultiplier = 0.02F;
		entityItem.motionX = world.rand.nextGaussian() * motionMultiplier;
		entityItem.motionY = world.rand.nextGaussian() * motionMultiplier + 0.1F;
		entityItem.motionZ = world.rand.nextGaussian() * motionMultiplier;

		world.spawnEntity(entityItem);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (!(world.getTileEntity(pos) instanceof TileChestVoid)) {
			return;
		}

		TileChestVoid chest = (TileChestVoid) world.getTileEntity(pos);
		chest.setFacing(EnumFacing.getDirectionFromEntityLiving(pos, placer));

		if (chest.id != -1) {
			return;
		}

		if (stack.hasTagCompound()) {
			if (stack.getTagCompound().hasKey("id")) {
				int id = stack.getTagCompound().getInteger("id");

				if (id != -1) {
					chest.id = id;
				}
			}
		}

		chest.markDirty();
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
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		TileEntity tile = world.getTileEntity(pos);

		if (!world.isRemote && tile instanceof TileChestVoid && entity instanceof EntityPlayer && entity.getEntityBoundingBox().minY <= pos.getY() + 1D) {
			TileChestVoid chest = (TileChestVoid) tile;
			EntityPlayer player = (EntityPlayer) entity;

			if (chest.isOpen()) {
				TeleportationManager.teleportPlayerToVoidChest((EntityPlayerMP) player, chest);
				WorldDataVoidChest.INSTANCE.addVoidChestPosition(chest.id, pos, world.provider.getDimension());
				chest.closeChest();
			}
		}
	}
}