package T145.magistics.client.lib;

import T145.magistics.blocks.cosmetic.BlockCandle;
import T145.magistics.blocks.cosmetic.BlockNitor;
import T145.magistics.core.Init;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorHandler {

	public static final int[] CRYSTAL_COLORS = { 16777086, 16727041, 37119, 40960, 16711935, 9699539 };
	public static final int AMBIENT_GRASS = 16777215;

	public static void init() {
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		registerBlockColors(blockColors);
		registerItemColors(blockColors, itemColors);
	}

	private static void registerBlockColors(BlockColors blockColors) {
		IBlockColor basicColorHandler = (state, world, pos, tintIndex) -> {
			if (state.getBlock() instanceof BlockNitor) {
				return ((EnumDyeColor) state.getValue(((BlockNitor) state.getBlock()).variant)).getMapColor().colorValue;
			}

			if (state.getBlock() instanceof BlockCandle) {
				return ((EnumDyeColor) state.getValue(((BlockCandle) state.getBlock()).variant)).getMapColor().colorValue;
			}

			return AMBIENT_GRASS;
		};

		blockColors.registerBlockColorHandler(basicColorHandler, Init.NITOR, Init.CANDLE, Init.FLOATING_CANDLE);

		IBlockColor leafColorHandler = (state, world, pos, tintIndex) -> {
			if (state.getBlock().damageDropped(state) != 0) {
				return AMBIENT_GRASS;
			}

			if (world != null && pos != null) {
				return BiomeColorHelper.getFoliageColorAtPos(world, pos);
			}

			return ColorizerFoliage.getFoliageColorBasic();
		};

		blockColors.registerBlockColorHandler(leafColorHandler, Init.LEAVES);

		IBlockColor crystalOreColorHandler = (state, world, pos, tintIndex) -> {
			if (tintIndex == 1) {
				return CRYSTAL_COLORS[state.getBlock().getMetaFromState(state)];
			}

			return -1;
		};

		blockColors.registerBlockColorHandler(crystalOreColorHandler, Init.OVERWORLD_CRYSTAL_ORE, Init.NETHER_CRYSTAL_ORE, Init.END_CRYSTAL_ORE);
	}

	private static void registerItemColors(BlockColors blockColors, ItemColors itemColors) {
		itemColors.registerItemColorHandler((stack, tintIndex) -> {
			IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
			return blockColors.colorMultiplier(state, null, null, tintIndex);
		}, Init.LEAVES, Init.NITOR, Init.CANDLE, Init.FLOATING_CANDLE);

		IItemColor crystalColorHandler = (ItemStack stack, int tintIndex) -> {
			if (stack.getItemDamage() > 0) {
				return CRYSTAL_COLORS[stack.getItemDamage() - 1];
			}

			return -1;
		};

		IItemColor crystalOreColorHandler = (ItemStack stack, int tintIndex) -> {
			return CRYSTAL_COLORS[stack.getItemDamage()];
		};

		itemColors.registerItemColorHandler(crystalColorHandler, Init.CRYSTAL_SHARD, Init.CRYSTAL_SHARD_FRAGMENT);
		itemColors.registerItemColorHandler(crystalOreColorHandler, Init.OVERWORLD_CRYSTAL_ORE, Init.NETHER_CRYSTAL_ORE, Init.END_CRYSTAL_ORE);
	}
}