package T145.magistics.blocks;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.api.blocks.IBlockModeled;
import T145.magistics.items.ItemShard;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfusedOre extends Block implements IBlockModeled, IBlockColor {

	public static class BlockOreItem extends ItemBlock {

		public BlockOreItem(Block block) {
			super(block);
			setHasSubtypes(true);
		}

		@Override
		public int getMetadata(int meta) {
			return meta;
		}

		@Override
		public String getUnlocalizedName(ItemStack stack) {
			return super.getUnlocalizedName() + "." + ItemShard.ItemType.byMetadata(stack.getMetadata()).getName();
		}
	}

	public static final PropertyEnum<ItemShard.ItemType> VARIANT = PropertyEnum.<ItemShard.ItemType>create("variant", ItemShard.ItemType.class);

	public BlockInfusedOre(String name) {
		super(Material.ROCK);

		setDefaultState(blockState.getBaseState().withProperty(VARIANT, ItemShard.ItemType.AIR));
		setRegistryName(new ResourceLocation(Magistics.MODID, name));

		setCreativeTab(Magistics.tab);
		setUnlocalizedName(name);
		setSoundType(SoundType.STONE);

		GameRegistry.register(this);
		GameRegistry.register(new BlockOreItem(this), getRegistryName());
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (ItemShard.ItemType type : ItemShard.ItemType.values()) {
			list.add(new ItemStack(item, 1, type.ordinal()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (ItemShard.ItemType type : ItemShard.ItemType.values()) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), type.getClientName()));
		}
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
		return ItemShard.COLORS[getMetaFromState(state)];
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, ItemShard.ItemType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
}