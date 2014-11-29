package T145.magistics.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import T145.magistics.common.Magistics;
import T145.magistics.common.blocks.BlockChestHungryEnder;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.common.registry.GameRegistry;

public class Settings extends Log {
	public static Configuration config;

	public static void sync() {
		try {
			config.load();
		} catch (Exception err) {
			error("An error has occurred while loading configuration properties!", err);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void preInit(File configFile) {
		config = new Configuration(configFile);
		sync();
	}

	public static CreativeTabs tabMagistics = new CreativeTabs(Magistics.modid) {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.bookshelf);
		}
	};

	public static Class tiles[] = {
		TileChestHungryEnder.class
	};

	public static Block blockChestHungryEnder = new BlockChestHungryEnder().setBlockName("hungry_ender_chest").setCreativeTab(tabMagistics).setHardness(22.5F).setResistance(1000F).setStepSound(Block.soundTypePiston).setLightLevel(0.5F);

	public static void init() {
		for (Class tile : tiles)
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
		GameRegistry.registerBlock(blockChestHungryEnder, blockChestHungryEnder.getLocalizedName());
	}

	public static void postInit() {
		// Thaumcraft stuff goes here
	}
}