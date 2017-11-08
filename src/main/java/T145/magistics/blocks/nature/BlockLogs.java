package T145.magistics.blocks.nature;

import T145.magistics.Magistics;
import T145.magistics.blocks.MBlockItem;
import T145.magistics.blocks.nature.BlockPlanks.WoodType;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLogs extends BlockLog {

	public static final PropertyEnum<WoodType> VARIANT = PropertyEnum.<WoodType>create("variant", WoodType.class);

	public BlockLogs() {
		super();
		String name = "log";
		setDefaultState(blockState.getBaseState().withProperty(VARIANT, WoodType.GREATWOOD).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		setRegistryName(new ResourceLocation(Magistics.MODID, name));
		setCreativeTab(Magistics.TAB);
		setUnlocalizedName(name);
		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		setHardness(2F);
		setResistance(5F);

		// delete this in 1.12
		GameRegistry.register(this);
		GameRegistry.register(new MBlockItem(this, WoodType.class), getRegistryName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (WoodType type : WoodType.values()) {
			list.add(new ItemStack(item, 1, type.ordinal()));
		}
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
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]).withProperty(VARIANT, WoodType.byMetadata(meta & 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LOG_AXIS).ordinal() * 4 + state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS, VARIANT });
	}
}