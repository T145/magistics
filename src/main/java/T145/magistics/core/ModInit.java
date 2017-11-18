package T145.magistics.core;

import T145.magistics.common.blocks.BlockConduit;
import T145.magistics.common.blocks.BlockCrucible;
import T145.magistics.common.blocks.base.BlockItemBase;
import T145.magistics.common.tiles.TileConduit;
import T145.magistics.common.tiles.TileCrucible;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Magistics.ID)
public class ModInit {

	public static final BlockCrucible CRUCIBLE = new BlockCrucible();
	public static final BlockConduit CONDUIT = new BlockConduit();

	@EventBusSubscriber(modid = Magistics.ID)
	public static class ServerRegistration {

		// register everything that doesn't have a specific event here
		static {
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			registry.register(CRUCIBLE);
			registry.register(CONDUIT);
			registerTileEntities();
		}

		private static void registerTileEntities() {
			registerTileEntity(TileCrucible.class);
			registerTileEntity(TileConduit.class);
		}

		private static void registerTileEntity(Class tileClass) {
			GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
		}

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			registerItemBlock(registry, CRUCIBLE);
			registerItemBlock(registry, CONDUIT);
		}

		private static void registerItemBlock(IForgeRegistry<Item> registry, Block block) {
			registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}

		private static void registerItemBlock(IForgeRegistry<Item> registry, Block block, Class types) {
			registry.register(new BlockItemBase(block, types).setRegistryName(block.getRegistryName()));
		}
	}

	@EventBusSubscriber(modid = Magistics.ID)
	public static class ClientRegistration {

		public static String getVariantName(IStringSerializable variant) {
			return "variant=" + variant.getName();
		}

		public static void registerBlockModel(Block block, int meta, String path, String variant) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Magistics.ID + ":" + path, variant));
		}

		public static void registerBlockModel(Block block, int meta, String variant) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), variant));
		}

		public static void registerBlockModel(Block block, int meta, IStringSerializable variant) {
			registerBlockModel(block, meta, "variant=" + variant.getName());
		}

		public static void registerItemModel(Item item, int meta, String path) {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.ID + ":" + path, "inventory"));
		}

		public static void registerItemModel(Item item, int meta) {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

		@SubscribeEvent
		public static void onModelRegistration(ModelRegistryEvent event) {
			registerFluidRenderers();
			registerEntityRenderers();
			registerBlockModels();
			registerItemModels();
			registerTileRenderers();
			registerItemRenderers();
		}

		private static void registerFluidRenderers() {
			// TODO Implement
		}

		private static void registerEntityRenderers() {
			// TODO Implement
		}

		private static void registerBlockModels() {
			registerBlockModel(CRUCIBLE, 0, "inventory");
			registerBlockModel(CONDUIT, 0, "inventory");
		}

		private static void registerItemModels() {
			// TODO Implement
		}

		private static void registerTileRenderers() {
			// TODO Implement
		}

		private static void registerItemRenderers() {
			// TODO Implement
		}
	}

	@EventBusSubscriber(modid = Magistics.ID)
	public static class EventRegistration {
	}
}