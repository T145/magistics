package T145.magistics.proxies;

import java.util.Set;

import org.apache.logging.log4j.core.helpers.Strings;

import com.google.common.base.Stopwatch;

import T145.magistics.Magistics;
import T145.magistics.api.IMagisticsPlugin;
import T145.magistics.api.MagisticsPlugin;
import T145.magistics.init.ModBlocks;
import T145.magistics.init.ModEntities;
import T145.magistics.init.ModItems;
import T145.magistics.init.ModRecipes;
import T145.magistics.init.ModSounds;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	public static Set<ASMData> plugins;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return ((TileInfuser) tile).createContainer(player.inventory, player);
		case 1:
			return ((TileChestHungry) tile).createContainer(player.inventory, player);
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
	}

	public void init(FMLInitializationEvent event) {
		ModSounds.registerSounds();
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModRecipes.registerRecipes();
	}

	public void loadComplete(FMLLoadCompleteEvent event) {
		for (ASMData data : plugins) {
			try {
				String pluginModid = (String) data.getAnnotationInfo().get(data);

				if (Strings.isNotEmpty(pluginModid) && Loader.isModLoaded(pluginModid)) {
					Stopwatch stopwatch = Stopwatch.createStarted();
					Class<?> asmClass = Class.forName(data.getClassName());

					if (IMagisticsPlugin.class.isAssignableFrom(asmClass)) {
						IMagisticsPlugin plugin = (IMagisticsPlugin) asmClass.newInstance();
						plugin.loadPlugin();
						Magistics.LOGGER.info("Registered plugin for {} from {} in {}", pluginModid, data.getClassName(), stopwatch.stop());
					} else {
						Magistics.LOGGER.error("{} attempted to register a plugin for {} that did not implement IMagisticsPlugin", data.getClassName(), pluginModid);
					}
				} else {
					Magistics.LOGGER.error("{} is not loaded. Passing over plugin.", pluginModid);
				}
			} catch (Exception err) {
				Magistics.LOGGER.error("Error registering plugin for class {}", data.getClassName());
			}
		}
	}
}