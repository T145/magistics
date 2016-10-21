package T145.magistics.load;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

	public static void preInit(FMLPreInitializationEvent event) {}

	@SideOnly(Side.CLIENT)
	public static void initClient() {}

	public static void init(FMLInitializationEvent event) {}

	public static void postInit(FMLPostInitializationEvent event) {}
}