package T145.magistics.client.lib;

import T145.magistics.blocks.cosmetic.BlockCandle;
import T145.magistics.blocks.cosmetic.BlockNitor;
import T145.magistics.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorHandler {

	public static final int AMBIENT_GRASS = 16777215;

	public static void init() {
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		registerBlockColors(blockColors);
		registerItemColors(blockColors, itemColors);
	}

	private static void registerBlockColors(BlockColors blockColors) {
		IBlockColor basicColorHandler = (state, blockAccess, pos, tintIndex) -> {
			if (state.getBlock() instanceof BlockNitor) {
				return ((EnumDyeColor) state.getValue(((BlockNitor) state.getBlock()).variant)).getMapColor().colorValue;
			}

			if (state.getBlock() instanceof BlockCandle) {
				return ((EnumDyeColor) state.getValue(((BlockCandle) state.getBlock()).variant)).getMapColor().colorValue;
			}

			return AMBIENT_GRASS;
		};

		blockColors.registerBlockColorHandler(basicColorHandler, ModBlocks.NITOR, ModBlocks.CANDLE, ModBlocks.FLOATING_CANDLE);

		IBlockColor leafColorHandler = (state, blockAccess, pos, tintIndex) -> {
			if (state.getBlock().damageDropped(state) != 0) {
				return AMBIENT_GRASS;
			}

			if (blockAccess != null && pos != null) {
				return BiomeColorHelper.getFoliageColorAtPos((IBlockAccess) blockAccess, (BlockPos) pos);
			}

			return ColorizerFoliage.getFoliageColorBasic();
		};

		blockColors.registerBlockColorHandler(leafColorHandler, new Block[] { ModBlocks.LEAVES });
	}

	private static void registerItemColors(BlockColors blockColors, ItemColors itemColors) {
		itemColors.registerItemColorHandler((stack, tintIndex) -> {
			IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
			return blockColors.colorMultiplier(state, null, null, tintIndex);
		}, ModBlocks.LEAVES, ModBlocks.NITOR, ModBlocks.CANDLE, ModBlocks.FLOATING_CANDLE);
	}
}