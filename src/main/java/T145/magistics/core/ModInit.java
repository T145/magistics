package T145.magistics.core;

import T145.magistics.client.render.RenderCrucible;
import T145.magistics.client.render.RenderQuintTank;
import T145.magistics.common.blocks.BlockConduit;
import T145.magistics.common.blocks.BlockCrucible;
import T145.magistics.common.blocks.BlockQuintTank;
import T145.magistics.common.blocks.base.BlockItemBase;
import T145.magistics.common.tiles.TileConduit;
import T145.magistics.common.tiles.TileCrucible;
import T145.magistics.common.tiles.TileQuintTank;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Magistics.ID)
public class ModInit {

	public static final BlockCrucible CRUCIBLE = new BlockCrucible();
	public static final BlockConduit CONDUIT = new BlockConduit();
	public static final BlockQuintTank TANK = new BlockQuintTank();

	public static final SoundEvent SOUND_ATTACH = getSoundEvent("attach");
	public static final SoundEvent SOUND_BEAMLOOP = getSoundEvent("beamloop");
	public static final SoundEvent SOUND_BUBBLING = getSoundEvent("bubbling");
	public static final SoundEvent SOUND_CREAKING1 = getSoundEvent("creaking1");
	public static final SoundEvent SOUND_CREAKING2 = getSoundEvent("creaking2");
	public static final SoundEvent SOUND_ELECLOOP = getSoundEvent("elecloop");
	public static final SoundEvent SOUND_FIRELOOP = getSoundEvent("fireloop");
	public static final SoundEvent SOUND_GORE1 = getSoundEvent("gore1");
	public static final SoundEvent SOUND_GORE2 = getSoundEvent("gore2");
	public static final SoundEvent SOUND_HEAL = getSoundEvent("heal");
	public static final SoundEvent SOUND_INFUSER = getSoundEvent("infuser");
	public static final SoundEvent SOUND_INFUSER_DARK = getSoundEvent("infuserdark");
	public static final SoundEvent SOUND_LEARN = getSoundEvent("learn");
	public static final SoundEvent SOUND_MONOLITH = getSoundEvent("monolith");
	public static final SoundEvent SOUND_MONOLITH_FOUND = getSoundEvent("monolithfound");
	public static final SoundEvent SOUND_PAGE1 = getSoundEvent("page1");
	public static final SoundEvent SOUND_PAGE2 = getSoundEvent("page2");
	public static final SoundEvent SOUND_PORTAL_CLOSE = getSoundEvent("pclose");
	public static final SoundEvent SOUND_PLACE = getSoundEvent("place");
	public static final SoundEvent SOUND_POD_BURST = getSoundEvent("podburst");
	public static final SoundEvent SOUND_PORTAL_OPEN = getSoundEvent("popen");
	public static final SoundEvent SOUND_RECOVER = getSoundEvent("recover");
	public static final SoundEvent SOUND_ROOTS = getSoundEvent("roots");
	public static final SoundEvent SOUND_RUMBLE = getSoundEvent("rumble");
	public static final SoundEvent SOUND_RUNE_SET = getSoundEvent("runeset");
	public static final SoundEvent SOUND_SCRIBBLE = getSoundEvent("scribble");
	public static final SoundEvent SOUND_SHOCK1 = getSoundEvent("shock1");
	public static final SoundEvent SOUND_SHOCK2 = getSoundEvent("shock2");
	public static final SoundEvent SOUND_SINGULARITY = getSoundEvent("singularity");
	public static final SoundEvent SOUND_STOMP1 = getSoundEvent("stomp1");
	public static final SoundEvent SOUND_STOMP2 = getSoundEvent("stomp2");
	public static final SoundEvent SOUND_STONE_CLOSE = getSoundEvent("stoneclose");
	public static final SoundEvent SOUND_STONE_OPEN = getSoundEvent("stoneopen");
	public static final SoundEvent SOUND_SUCK = getSoundEvent("suck");
	public static final SoundEvent SOUND_SWING1 = getSoundEvent("swing1");
	public static final SoundEvent SOUND_SWING2 = getSoundEvent("swing2");
	public static final SoundEvent SOUND_TINKERING = getSoundEvent("tinkering");
	public static final SoundEvent SOUND_TOOL1 = getSoundEvent("tool1");
	public static final SoundEvent SOUND_TOOL2 = getSoundEvent("tool2");
	public static final SoundEvent SOUND_UPGRADE = getSoundEvent("upgrade");
	public static final SoundEvent SOUND_WHISPER = getSoundEvent("whisper");
	public static final SoundEvent SOUND_WIND1 = getSoundEvent("wind1");
	public static final SoundEvent SOUND_WIND2 = getSoundEvent("wind2");
	public static final SoundEvent SOUND_ZAP1 = getSoundEvent("zap1");
	public static final SoundEvent SOUND_ZAP2 = getSoundEvent("zap2");

	public static final SoundEvent getSoundEvent(String name) {
		return new SoundEvent(new ResourceLocation(Magistics.ID, name)).setRegistryName(name);
	}

	@EventBusSubscriber(modid = Magistics.ID)
	public static class ServerRegistration {

		// register everything that doesn't have a specific event here
		static {
		}

		@SubscribeEvent
		public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
			final IForgeRegistry<SoundEvent> registry = event.getRegistry();
			registry.register(SOUND_ATTACH);
			registry.register(SOUND_BEAMLOOP);
			registry.register(SOUND_BUBBLING);
			registry.register(SOUND_CREAKING1);
			registry.register(SOUND_CREAKING2);
			registry.register(SOUND_ELECLOOP);
			registry.register(SOUND_FIRELOOP);
			registry.register(SOUND_GORE1);
			registry.register(SOUND_GORE2);
			registry.register(SOUND_HEAL);
			registry.register(SOUND_INFUSER);
			registry.register(SOUND_INFUSER_DARK);
			registry.register(SOUND_LEARN);
			registry.register(SOUND_MONOLITH);
			registry.register(SOUND_MONOLITH_FOUND);
			registry.register(SOUND_PAGE1);
			registry.register(SOUND_PAGE2);
			registry.register(SOUND_PORTAL_CLOSE);
			registry.register(SOUND_PLACE);
			registry.register(SOUND_POD_BURST);
			registry.register(SOUND_PORTAL_OPEN);
			registry.register(SOUND_RECOVER);
			registry.register(SOUND_ROOTS);
			registry.register(SOUND_RUMBLE);
			registry.register(SOUND_RUNE_SET);
			registry.register(SOUND_SCRIBBLE);
			registry.register(SOUND_SHOCK1);
			registry.register(SOUND_SHOCK2);
			registry.register(SOUND_SINGULARITY);
			registry.register(SOUND_STOMP1);
			registry.register(SOUND_STOMP2);
			registry.register(SOUND_STONE_CLOSE);
			registry.register(SOUND_STONE_OPEN);
			registry.register(SOUND_SUCK);
			registry.register(SOUND_SWING1);
			registry.register(SOUND_SWING2);
			registry.register(SOUND_TINKERING);
			registry.register(SOUND_TOOL1);
			registry.register(SOUND_TOOL2);
			registry.register(SOUND_UPGRADE);
			registry.register(SOUND_WHISPER);
			registry.register(SOUND_WIND1);
			registry.register(SOUND_WIND2);
			registry.register(SOUND_ZAP1);
			registry.register(SOUND_ZAP2);
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			registry.register(CRUCIBLE);
			registry.register(CONDUIT);
			registry.register(TANK);
			registerTileEntities();
		}

		private static void registerTileEntities() {
			registerTileEntity(TileCrucible.class);
			registerTileEntity(TileConduit.class);
			registerTileEntity(TileQuintTank.class);
		}

		private static void registerTileEntity(Class tileClass) {
			GameRegistry.registerTileEntity(tileClass, tileClass.getSimpleName());
		}

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			registerItemBlock(registry, CRUCIBLE);
			registerItemBlock(registry, CONDUIT);
			registerItemBlock(registry, TANK);
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
			registerBlockModel(TANK, 0, "inventory");
		}

		private static void registerItemModels() {
			// TODO Implement
		}

		private static void registerTileRenderers() {
			ClientRegistry.bindTileEntitySpecialRenderer(TileCrucible.class, new RenderCrucible());
			ClientRegistry.bindTileEntitySpecialRenderer(TileQuintTank.class, new RenderQuintTank());
		}

		private static void registerItemRenderers() {
			// TODO Implement
		}
	}

	@EventBusSubscriber(modid = Magistics.ID)
	public static class EventRegistration {
	}
}