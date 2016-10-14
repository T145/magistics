package T145.magistics.load;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import T145.magistics.Magistics;
import T145.magistics.api.blocks.IBlockTypes;
import T145.magistics.blocks.BlockCrucible;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.blocks.BlockMagistics;
import T145.magistics.blocks.BlockMagisticsItem;
import T145.magistics.tiles.TileCrucible;
import T145.magistics.tiles.TileInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public class ModBlocks {

	public static Block blockInfuser;
	public static Block blockCrucible;

	private static List<Block> blocks = new ArrayList<Block>();

	public static void preInit() {
		GameRegistry.registerTileEntity(TileInfuser.class, TileInfuser.class.getSimpleName());
		GameRegistry.registerTileEntity(TileCrucible.class, TileCrucible.class.getSimpleName());

		blockInfuser = initBlock(new BlockInfuser(), "infuser");
		blockCrucible = initBlock(new BlockCrucible(), "crucible");
	}

	public static void init() {
	}

	public static void postInit() {
		// load recipes and what not
	}

	public static void initModelsAndVariants() {
		for (Block block : blocks) {
			Item blockItem = Item.getItemFromBlock(block);
			ResourceLocation loc = block.getRegistryName();
			String location = loc.toString();

			if (block instanceof IBlockTypes && ((IBlockTypes) block).hasTypes()) {
				IBlockTypes blockWithTypes = (IBlockTypes) block;
				IBlockState state = block.getBlockState().getBaseState();

				for (IProperty prop : blockWithTypes.getTypes()) {
					for (Object value : prop.getAllowedValues()) {
						int meta = block.damageDropped(state);
						String stateName = blockWithTypes.getTypeName(state);
						String type = "type=" + stateName.toLowerCase(Locale.US);

						ModelLoader.setCustomModelResourceLocation(blockItem, meta, new ModelResourceLocation(location, type));

						state = state.cycleProperty(prop);
					}
				}
			} else {
				ModelLoader.setCustomModelResourceLocation(blockItem, 0, new ModelResourceLocation(loc, "inventory"));
			}
		}
	}

	private static Block initBlock(BlockMagistics block, String name) {
		return initBlock(block, name, null);
	}

	private static Block initBlock(Block block, String name, Class ib) {
		blocks.add(block);
		block.setUnlocalizedName(name);

		if (ib != null) {
			registerBlock(block, ib, name);
		} else if (block instanceof BlockMagistics && ((BlockMagistics) block).hasTypes()) {
			registerBlock(block, BlockMagisticsItem.class, name);
		} else {
			registerBlock(block, ItemBlock.class, name);
		}

		return block;
	}

	public static Block registerBlock(Block block, ItemBlock itemBlock, String name) {
		block = register(block, name);
		register(itemBlock, name);
		return block;
	}

	public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlock, String name) {
		try {
			return registerBlock(block, itemBlock.getConstructor(new Class[] { Block.class }).newInstance(new Object[] { block }), name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends IForgeRegistryEntry<?>> T register(T object, String name) {
		object.setRegistryName(new ResourceLocation(Magistics.MODID, name));
		return GameRegistry.register(object);
	}
}