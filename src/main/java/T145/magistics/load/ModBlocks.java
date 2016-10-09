package T145.magistics.load;

import T145.magistics.blocks.BlockCrucible;
import T145.magistics.blocks.BlockCrucibleItem;
import T145.magistics.blocks.BlockInfuser;
import T145.magistics.blocks.BlockInfuserItem;
import T145.magistics.blocks.BlockMagistics;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static BlockMagistics blockCrucible = new BlockCrucible();
	public static BlockMagistics blockInfuser = new BlockInfuser();

	private static ItemBlock blockCrucibleItem;
	private static ItemBlock blockInfuserItem;

	/*private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if (block instanceof BlockMagistics) {
			((BlockMagistics) block).registerItemModel(itemBlock);
		}

		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}*/

	public static void init() {
		GameRegistry.register(blockCrucible);
		GameRegistry.register(blockCrucibleItem = new BlockCrucibleItem(blockCrucible), blockCrucible.getRegistryName());
		blockCrucible.registerModel();

		GameRegistry.register(blockInfuser);
		GameRegistry.register(blockInfuserItem = new BlockInfuserItem(blockInfuser), blockInfuser.getRegistryName());
		blockInfuser.registerModel();
	}
}