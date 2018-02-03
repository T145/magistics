package T145.magistics.common;

import T145.magistics.api.back.IProxy;
import T145.magistics.api.front.MagisticsApi;
import T145.magistics.api.research.ResearchCategory;
import T145.magistics.api.research.ResearchEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {}

	@Override
	public void init(FMLInitializationEvent event) {}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ResearchCategory info = new ResearchCategory("magistics.research_category.info", new ResourceLocation(Magistics.ID, "textures/gui/research/r_thaumaturgy.png"), new ResourceLocation(Magistics.ID, "textures/gui/research/bg.png"));
		ResearchEntry test = new ResearchEntry("magistics.research_entry.test", info, 4, 4, new ResourceLocation(Magistics.ID, "textures/gui/research/r_crucible.png"));
		MagisticsApi.registerResearch(info);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}