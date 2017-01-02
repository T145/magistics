package T145.magistics.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import T145.magistics.Magistics;
import T145.magistics.api.enums.EnumShard;
import T145.magistics.api.objects.IModel;
import T145.magistics.api.objects.ModItems;
import T145.magistics.items.ItemShard;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfusedOre extends MBlock<EnumShard> implements IModel, IBlockColor {

	private Random rand = new Random();

	public BlockInfusedOre(String name) {
		super(Material.ROCK, EnumShard.class);

		setRegistryName(new ResourceLocation(Magistics.MODID, name));
		setUnlocalizedName(name);
		setSoundType(SoundType.STONE);
		setResistance(5F);
		setHardness(1.5F);
		setTickRandomly(true);

		GameRegistry.register(this);
		GameRegistry.register(new MBlockItem(this, EnumShard.class), getRegistryName());
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
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (EnumShard type : EnumShard.values()) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), type.getClientName()));
		}
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
		if (tintIndex == 1) {
			return ItemShard.COLORS[getMetaFromState(state)];
		}

		return -1;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> drops = new ArrayList<ItemStack>();

		for (int i = 0; i < 1 + rand.nextInt(2 + fortune); ++i) {
			drops.add(new ItemStack(ModItems.shardFragment, 1, getMetaFromState(state)));
		}

		return drops;
	}

	@Override
	public boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return MathHelper.getRandomIntegerInRange(rand, 0, 3);
	}
}