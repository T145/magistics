package T145.magistics.proxies;

import java.util.Set;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import T145.magistics.Magistics;
import T145.magistics.api.IMagisticsPlugin;
import T145.magistics.api.MagisticsPlugin;
import T145.magistics.containers.ContainerChestHungry;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.init.ModBiomes;
import T145.magistics.init.ModBlocks;
import T145.magistics.init.ModDimensions;
import T145.magistics.init.ModEntities;
import T145.magistics.init.ModItems;
import T145.magistics.init.ModRecipes;
import T145.magistics.init.ModSounds;
import T145.magistics.network.PacketHandler;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import T145.magistics.world.generators.WorldGeneratorAura;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLStateEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	public static Set<ASMData> plugins;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return new ContainerInfuser(player.inventory, (TileInfuser) tile);
		case 1:
			return new ContainerChestHungry(player.inventory, (TileChestHungry) tile);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public void preInit(FMLPreInitializationEvent event) {
		plugins = event.getAsmData().getAll(MagisticsPlugin.class.getCanonicalName());

		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		ModBiomes.init();
		ModDimensions.init();

		PacketHandler.registerMessages();
		GameRegistry.registerWorldGenerator(WorldGeneratorAura.INSTANCE, 0);

		registerPlugins(event, false);
	}

	public void init(FMLInitializationEvent event) {
		ModSounds.registerSounds();
		registerPlugins(event, false);
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModRecipes.registerRecipes();
		registerPlugins(event, false);
	}

	protected void registerPlugins(FMLStateEvent event, boolean clientSide) {
		for (ASMData data : plugins) {
			try {
				String modid = (String) data.getAnnotationInfo().get("modid");

				if (!Strings.isNullOrEmpty(modid) && Loader.isModLoaded(modid)) {
					Stopwatch stopwatch = Stopwatch.createStarted();
					Class<?> asmClass = Class.forName(data.getClassName());

					if (IMagisticsPlugin.class.isAssignableFrom(asmClass)) {
						IMagisticsPlugin plugin = (IMagisticsPlugin) asmClass.newInstance();

						if (event instanceof FMLPreInitializationEvent) {
							if (clientSide) {
								plugin.initClient();
								Magistics.LOGGER.info("Initialized client plugin for {} from {} in {}", modid, data.getClassName(), stopwatch.stop());
							} else {
								plugin.preInit((FMLPreInitializationEvent) event);
								Magistics.LOGGER.info("PreInitialized plugin for {} from {} in {}", modid, data.getClassName(), stopwatch.stop());
							}
						} else if (event instanceof FMLInitializationEvent) {
							plugin.init((FMLInitializationEvent) event);
							Magistics.LOGGER.info("Initialized plugin for {} from {} in {}", modid, data.getClassName(), stopwatch.stop());
						} else if (event instanceof FMLPostInitializationEvent) {
							plugin.postInit((FMLPostInitializationEvent) event);
							Magistics.LOGGER.info("PostInitialized plugin for {} from {} in {}", modid, data.getClassName(), stopwatch.stop());
						}
					} else {
						Magistics.LOGGER.error("{} attempted to register a plugin for {} that did not implement IMagisticsPlugin", data.getClassName(), modid);
					}

					if (stopwatch.isRunning()) {
						stopwatch.stop();
					}
				}
			} catch (Exception err) {
				Magistics.LOGGER.error("Error registering plugin for class {}", data.getClassName());
			}
		}
	}
}