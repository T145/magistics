package T145.magistics.load;

import T145.magistics.Magistics;
import T145.magistics.client.render.entities.RenderVisSlime;
import T145.magistics.entities.EntityVisSlime;
import T145.magistics.items.ItemShard;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

	private static int id;

	public static void preInit(FMLPreInitializationEvent event) {
		EntityRegistry.registerModEntity(EntityVisSlime.class, "slime.vis", id++, Magistics.instance, 64, 5, true, ItemShard.COLORS[4], 11126094);
	}

	@SideOnly(Side.CLIENT)
	public static void initClient() {
		RenderingRegistry.registerEntityRenderingHandler(EntityVisSlime.class, RenderVisSlime.RENDER_FACTORY);
	}

	public static void init(FMLInitializationEvent event) {}

	public static void postInit(FMLPostInitializationEvent event) {}
}