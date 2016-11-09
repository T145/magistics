package T145.magistics.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.api.blocks.IBlockModeled;
import T145.magistics.blocks.BlockLogs.BlockType;
import T145.magistics.load.ModBlocks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeaves extends net.minecraft.block.BlockLeaves implements IBlockModeled, IBlockColor {

	public static final PropertyEnum<BlockType> VARIANT = PropertyEnum.<BlockType>create("variant", BlockType.class);

	public BlockLeaves(String name) {
		super();

		setDefaultState(blockState.getBaseState().withProperty(VARIANT, BlockType.GREATWOOD).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
		setRegistryName(new ResourceLocation(Magistics.MODID, name));

		setCreativeTab(Magistics.tab);
		setUnlocalizedName(name);

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

	public void registerPath(int meta, String path) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), meta, new ModelResourceLocation(getRegistryName(), path));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (BlockType type : BlockType.values()) {
			registerPath(type.ordinal(), type.getClientName());
			registerPath(type.ordinal(), "check_decay=false,decayable=false," + type.getClientName());
			registerPath(type.ordinal(), "check_decay=true,decayable=false," + type.getClientName());
			registerPath(type.ordinal(), "check_decay=false,decayable=true," + type.getClientName());
			registerPath(type.ordinal(), "check_decay=true,decayable=true," + type.getClientName());
		}
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
		if (state.getBlock().damageDropped(state) != 0) {
			return 16777215;
		}

		if (world != null && pos != null) {
			return BiomeColorHelper.getFoliageColorAtPos(world, pos);
		}

		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		setGraphicsLevel(!isOpaqueCube(blockState));
		return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return damageDropped(state) == 1 ? MapColor.LIGHT_BLUE : super.getMapColor(state);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getBlock() == this ? state.getValue(VARIANT).ordinal() : 0;
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, (state.getValue(VARIANT)).ordinal());
	}

	@Override
	@Deprecated
	public EnumType getWoodType(int meta) {
		return null;
	}

	public BlockType getProperWoodType(int meta) {
		return BlockType.byMetadata(meta & 3);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, getProperWoodType(meta)).withProperty(DECAYABLE, (meta & 0x4) == 0).withProperty(CHECK_DECAY, (meta & 0x8) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0 | state.getValue(VARIANT).ordinal();

		if (!state.getValue(DECAYABLE).booleanValue()) {
			i |= 0x4;
		}

		if (state.getValue(CHECK_DECAY).booleanValue()) {
			i |= 0x8;
		}

		return i;
	}

	@Override
	protected int getSaplingDropChance(IBlockState state) {
		return state.getValue(VARIANT).ordinal() == 0 ? 44 : 200;
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.blockSaplings);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, CHECK_DECAY, DECAYABLE });
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return new ArrayList(Arrays.asList(new ItemStack[] { new ItemStack(this, 1, (world.getBlockState(pos).getValue(VARIANT)).ordinal()) }));
	}
}