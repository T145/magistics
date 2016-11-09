package T145.magistics.client.lib;

import T145.magistics.load.ModBlocks;
import T145.magistics.load.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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

	private static void registerBlockColorHandlers(final BlockColors blockColors) {
		blockColors.registerBlockColorHandler(ModBlocks.blockOre, new Block[] {
				ModBlocks.blockOre, ModBlocks.blockNetherOre, ModBlocks.blockEndOre
		});
		blockColors.registerBlockColorHandler(ModBlocks.blockLeaves, new Block[] { ModBlocks.blockLeaves });
	}

	private static void registerItemColorHandlers(final BlockColors blockColors, final ItemColors itemColors) {
		IItemColor blockColorHandler = new IItemColor() {

			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				ItemBlock itemBlock = (ItemBlock) stack.getItem();
				Block block = itemBlock.getBlock();
				IBlockState state = block.getStateFromMeta(stack.getMetadata());
				return blockColors.colorMultiplier(state, null, null, tintIndex);
			}
		};

		itemColors.registerItemColorHandler(blockColorHandler, new Block[] { ModBlocks.blockLeaves });

		itemColors.registerItemColorHandler(ModItems.itemShard, new Item[] { ModItems.itemShard });
		itemColors.registerItemColorHandler(ModItems.itemShardFragment, new Item[] { ModItems.itemShardFragment });

		itemColors.registerItemColorHandler(ModItems.itemShardFragment, new Block[] {
				ModBlocks.blockOre, ModBlocks.blockNetherOre, ModBlocks.blockEndOre
		});
	}
}