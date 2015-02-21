package T145.magistics.common;

import java.io.File;
import java.util.Calendar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import T145.magistics.api.FreezerRecipes;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.config.Log;
import T145.magistics.common.lib.ResearchRecipe;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.server.FMLServerHandler;

public class CommonProxy extends Log implements IGuiHandler {
	public static Configuration config;
	public static boolean debug, colored_names, low_gfx, hungry_chest_override;
	public static final String category[] = {
		"Graphics", "Blocks", "Items"
	};

	public static boolean winter;

	public static boolean isWinterTime() {
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.DECEMBER);
		b.set(Calendar.DAY_OF_MONTH, 1);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		e.set(Calendar.MONTH, Calendar.JANUARY);
		e.set(Calendar.DAY_OF_MONTH, 15);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}

	public void sync() {
		debug = config.getBoolean(config.CATEGORY_GENERAL, "Debug", true, "Toggles advanced log output.");
		hungry_chest_override = config.getBoolean(config.CATEGORY_GENERAL, "Hungry Chest Override", true, "Replaces Thaumcraft's Hungry Chest w/ a modified version.");
		colored_names = config.getBoolean(category[0], "Colored Names", false, "Toggles name coloring for some things.");
		low_gfx = config.getBoolean(category[0], "Low Graphics", false, "Determines some graphically intensive features are enabled.");
		winter = (isWinterTime() && config.get("default", "enableWinter", true).getBoolean()) || config.get("default", "forceWinter", false).getBoolean();
	}

	public void changeConfig(OnConfigChangedEvent e, String modid) {
		if (e.modID.equals(modid)) {
			sync();
			if (config != null && config.hasChanged())
				config.save();
		}
	}

	public void preInit(File configFile) {
		try {
			config = new Configuration(configFile);
			config.load();
			sync();
			config.save();
		} catch (Exception err) {
			error("An error has occurred while loading configuration properties!", err);
		} finally {
			if (config != null)
				config.save();
		}
	}

	public void init() {
		ConfigObjects.registerObjects();
	}

	public void postInit() {
		ConfigObjects.registerRenderers();
		ConfigObjects.initAPI();

		FreezerRecipes.addRecipe("water", new ItemStack(Blocks.ice));
		FreezerRecipes.addRecipe("lava", new ItemStack(Blocks.obsidian));

		ResearchCategories.registerCategory(Magistics.modid, new ResourceLocation("magistics", "textures/gui/tab.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		ConfigResearch.recipes.put("HungryEnderChest", ThaumcraftApi.addArcaneCraftingRecipe("HUNGRYENDERCHEST", new ItemStack(ConfigObjects.blockChestHungryEnder), new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 3).add(Aspect.ENTROPY, 3), "ABA", "ACA", "AAA", 'A', Blocks.obsidian, 'B', new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5), 'C', Items.ender_eye));
		new ResearchItem("HUNGRYENDERCHEST", Magistics.modid, new AspectList().add(Aspect.HUNGER, 3).add(Aspect.VOID, 3), -1, 0, 1, new ItemStack(ConfigObjects.blockChestHungryEnder)).setPages(new ResearchPage("tc.research_page.HUNGRYENDERCHEST.1"), ResearchRecipe.arcane("HungryEnderChest")).setSecondary().setParents("HUNGRYCHEST").registerResearchItem();
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int i, int j, int k) {
		return null;
	}

	public void sendToPlayer(EntityPlayerMP player, Packet packet) {
		player.playerNetServerHandler.sendPacket(packet);
	}

	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance) {
		if (world == null) {
			WorldServer worlds[] = FMLServerHandler.instance().getServer().worldServers;
			for (int i = 0; i < worlds.length; i++)
				for (int j = 0; j < worlds[i].playerEntities.size(); j++)
					sendToPlayer((EntityPlayerMP) worlds[i].playerEntities.get(j), packet);
		} else
			for (int i = 0; i < world.playerEntities.size(); i++) {
				EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(i);
				if (((int) player.posX - x) * ((int) player.posX - x) + ((int) player.posY - y) * ((int) player.posY - y) + ((int) player.posY - y) * ((int) player.posY - y) <= maxDistance * maxDistance)
					sendToPlayer(player, packet);
			}
	}

	public void sendToServer(Packet packet) {}
}