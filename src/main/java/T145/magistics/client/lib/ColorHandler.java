package T145.magistics.client.lib;

import T145.magistics.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorHandler {

	public static void init() {
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		registerBlockColors(blockColors);
		registerItemColors(blockColors, itemColors);
	}

	private static void registerBlockColors(BlockColors blockColors) {
		IBlockColor leafColourHandler = (state, blockAccess, pos, tintIndex) -> {
			if (state.getBlock().damageDropped(state) != 0) {
				return 16777215;
			}

			if (blockAccess != null && pos != null) {
				return BiomeColorHelper.getFoliageColorAtPos((IBlockAccess) blockAccess, (BlockPos) pos);
			}

			return ColorizerFoliage.getFoliageColorBasic();
		};

		blockColors.registerBlockColorHandler(leafColourHandler, new Block[] { ModBlocks.leaves });
	}

	private static void registerItemColors(BlockColors blockColors, ItemColors itemColors) {
		itemColors.registerItemColorHandler((stack, tintIndex) -> {
			IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
			return blockColors.colorMultiplier(state, null, null, tintIndex);
		}, new Block[] { ModBlocks.leaves });
	}
}