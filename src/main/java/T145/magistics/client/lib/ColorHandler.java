package T145.magistics.client.lib;

import T145.magistics.api.objects.ModBlocks;
import T145.magistics.api.objects.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
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
		blockColors.registerBlockColorHandler((IBlockColor) ModBlocks.infusedOre, new Block[] {
				ModBlocks.infusedOre, ModBlocks.infusedOreNether, ModBlocks.infusedOreEnd
		});
		blockColors.registerBlockColorHandler((IBlockColor) ModBlocks.leaves, new Block[] { ModBlocks.leaves });
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

		itemColors.registerItemColorHandler(blockColorHandler, new Block[] { ModBlocks.leaves });

		itemColors.registerItemColorHandler((IItemColor) ModItems.shard, new Item[] { ModItems.shard });
		itemColors.registerItemColorHandler((IItemColor) ModItems.shardFragment, new Item[] { ModItems.shardFragment });

		itemColors.registerItemColorHandler((IItemColor) ModItems.shardFragment, new Block[] {
				ModBlocks.infusedOre, ModBlocks.infusedOreNether, ModBlocks.infusedOreEnd
		});
	}
}