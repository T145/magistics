package T145.magistics.client.lib;

import T145.magistics.load.ModBlocks;
import T145.magistics.load.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColorHandler {

	public static void registerColorHandlers() {
		BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
		ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
		registerBlockColorHandlers(blockColors);
		registerItemColorHandlers(blockColors, itemColors);
	}

	private static void registerBlockColorHandlers(BlockColors blockColors) {
		blockColors.registerBlockColorHandler(ModBlocks.blockOre, new Block[] {
				ModBlocks.blockOre, ModBlocks.blockNetherOre, ModBlocks.blockEndOre
		});
	}

	private static void registerItemColorHandlers(BlockColors blockColors, ItemColors itemColors) {
		itemColors.registerItemColorHandler(ModItems.itemShard, new Item[] { ModItems.itemShard });
		itemColors.registerItemColorHandler(ModItems.itemShardFragment, new Item[] { ModItems.itemShardFragment });

		itemColors.registerItemColorHandler(ModItems.itemShardFragment, new Block[] {
				ModBlocks.blockOre, ModBlocks.blockNetherOre, ModBlocks.blockEndOre
		});
	}
}