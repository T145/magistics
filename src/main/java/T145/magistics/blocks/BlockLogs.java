package T145.magistics.blocks;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.blocks.IBlockModeled;
import T145.magistics.api.blocks.IBlockType;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLogs extends BlockLog implements IBlockModeled {

	public static enum BlockType implements IBlockType {

		GREATWOOD, SILVERWOOD;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		@Override
		public String getClientName() {
			return "variant=" + getName();
		}

		public static BlockType byMetadata(int meta) {
			return values()[MathHelper.clamp_int(meta, 0, meta)];
		}
	}

	public static final PropertyEnum<BlockType> VARIANT = PropertyEnum.<BlockType>create("variant", BlockType.class);

	public BlockLogs(String name) {
		super();

		setDefaultState(blockState.getBaseState().withProperty(VARIANT, BlockType.GREATWOOD).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		setRegistryName(new ResourceLocation(Magistics.MODID, name));

		setCreativeTab(Magistics.tab);
		setUnlocalizedName(name);
		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		setHardness(2F);
		setResistance(5F);

		GameRegistry.register(this);
		GameRegistry.register(new BlockMagisticsItem(this, BlockType.class), getRegistryName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (BlockType type : BlockType.values()) {
			list.add(new ItemStack(item, 1, type.ordinal()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (BlockType type : BlockType.values()) {
			for (BlockLog.EnumAxis axis : BlockLog.EnumAxis.values()) {
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), "axis=" + axis.getName() + "," + type.getClientName()));
			}
		}
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		Magistics.logger.info("Meta: " + this.getMetaFromState(super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)));
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		switch (getMetaFromState(state)) {
		case 1: case 5: case 9:
			return 6;
		default:
			return 0;
		}
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, damageDropped(state));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]).withProperty(VARIANT, BlockType.byMetadata(meta & 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal() * 4 + state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS, VARIANT });
	}
}