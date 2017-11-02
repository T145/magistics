package T145.magistics.proxies;

import T145.magistics.api.MagisticsApi;
import T145.magistics.containers.ContainerChestHungry;
import T145.magistics.containers.ContainerInfuser;
import T145.magistics.network.PacketHandler;
import T145.magistics.tiles.crafting.TileInfuser;
import T145.magistics.tiles.devices.TileChestHungry;
import T145.magistics.world.features.VillageCreationHandler;
import T145.magistics.world.generators.WorldGeneratorAura;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class CommonProxy implements IGuiHandler {

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
		PacketHandler.registerMessages();
		GameRegistry.registerWorldGenerator(WorldGeneratorAura.INSTANCE, 0);
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreationHandler());
	}

	public void init(FMLInitializationEvent event) {}

	public void postInit(FMLPostInitializationEvent event) {
		registerCrucibleRecipes();
		registerInfuserRecipes();
	}

	private void registerCrucibleRecipes() {
		MagisticsApi.addCrucibleRecipe(Items.STICK, 0.25F);
		MagisticsApi.addCrucibleRecipe(Items.CLAY_BALL, 1F);
		MagisticsApi.addCrucibleRecipe(Items.BRICK, 2F);
		MagisticsApi.addCrucibleRecipe(Items.FLINT, 1F);
		MagisticsApi.addCrucibleRecipe(Items.COAL, 2F);
		MagisticsApi.addCrucibleRecipe(Items.SNOWBALL, 0.25F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 0), 4F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 2), 4F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 3), 25F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 4), 9F);
		MagisticsApi.addCrucibleRecipe(Items.IRON_INGOT, 5F);
		MagisticsApi.addCrucibleRecipe(Items.BEETROOT_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.MELON_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.PUMPKIN_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.WHEAT_SEEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.FEATHER, 4F);
		MagisticsApi.addCrucibleRecipe(Items.BONE, 4F);
		MagisticsApi.addCrucibleRecipe(Items.MELON, 2F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_BEEF, 5F);
		MagisticsApi.addCrucibleRecipe(Items.BEEF, 4F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_CHICKEN, 5F);
		MagisticsApi.addCrucibleRecipe(Items.CHICKEN, 4F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_PORKCHOP, 5F);
		MagisticsApi.addCrucibleRecipe(Items.PORKCHOP, 4F);
		MagisticsApi.addCrucibleRecipe(Items.COOKED_FISH, 5F);
		MagisticsApi.addCrucibleRecipe(Items.FISH, 4F);
		MagisticsApi.addCrucibleRecipe(Items.NETHER_WART, 4F);
		MagisticsApi.addCrucibleRecipe(Items.ROTTEN_FLESH, 4F);
		MagisticsApi.addCrucibleRecipe(Items.STRING, 4F);
		MagisticsApi.addCrucibleRecipe(Items.LEATHER, 4F);
		MagisticsApi.addCrucibleRecipe(Items.WHEAT, 4F);
		MagisticsApi.addCrucibleRecipe(Items.REEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Items.GUNPOWDER, 10F);
		MagisticsApi.addCrucibleRecipe(Items.GLOWSTONE_DUST, 9F);
		MagisticsApi.addCrucibleRecipe(Items.REDSTONE, 6F);
		MagisticsApi.addCrucibleRecipe(Items.MILK_BUCKET, 18F);
		MagisticsApi.addCrucibleRecipe(Items.WATER_BUCKET, 16F);
		MagisticsApi.addCrucibleRecipe(Items.LAVA_BUCKET, 17F);
		MagisticsApi.addCrucibleRecipe(Items.EGG, 5F);
		MagisticsApi.addCrucibleRecipe(Items.GOLD_INGOT, 27F);
		MagisticsApi.addCrucibleRecipe(Items.GOLD_NUGGET, 3F);
		MagisticsApi.addCrucibleRecipe(Items.SLIME_BALL, 25F);
		MagisticsApi.addCrucibleRecipe(Items.APPLE, 4F);
		MagisticsApi.addCrucibleRecipe(Items.DIAMOND, 64F);
		MagisticsApi.addCrucibleRecipe(Items.ENDER_PEARL, 64F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_13, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_CAT, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_11, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_CHIRP, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_FAR, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_MALL, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_MELLOHI, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_STAL, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_STRAD, 100F);
		MagisticsApi.addCrucibleRecipe(Items.RECORD_WARD, 100F);
		MagisticsApi.addCrucibleRecipe(Items.BLAZE_ROD, 36F);
		MagisticsApi.addCrucibleRecipe(Items.GHAST_TEAR, 64F);
		MagisticsApi.addCrucibleRecipe(Items.SPIDER_EYE, 9F);
		MagisticsApi.addCrucibleRecipe(Items.SADDLE, 64F);
		MagisticsApi.addCrucibleRecipe(Blocks.COBBLESTONE, 0.1F);
		MagisticsApi.addCrucibleRecipe(Blocks.PLANKS, 0.5F);
		MagisticsApi.addCrucibleRecipe(Blocks.MOSSY_COBBLESTONE, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.SAND, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.SANDSTONE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.RED_SANDSTONE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.DIRT, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.GRASS, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.GLASS, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.ICE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.GRAVEL, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.STONE, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.WATERLILY, 3F);
		MagisticsApi.addCrucibleRecipe(Blocks.WEB, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.NETHER_BRICK, 2F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 1), 1F);
		MagisticsApi.addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 2), 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.NETHERRACK, 1F);
		MagisticsApi.addCrucibleRecipe(Blocks.SOUL_SAND, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.COAL_ORE, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.WOOL, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.LOG, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.LEAVES, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.TALLGRASS, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.DEADBUSH, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.CACTUS, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.SAPLING, 2F);
		MagisticsApi.addCrucibleRecipe(Blocks.MYCELIUM, 3F);
		MagisticsApi.addCrucibleRecipe(Blocks.IRON_ORE, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.YELLOW_FLOWER, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.RED_FLOWER, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.BROWN_MUSHROOM_BLOCK, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.RED_MUSHROOM_BLOCK, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.VINE, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.PUMPKIN, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.REEDS, 4F);
		MagisticsApi.addCrucibleRecipe(Blocks.REDSTONE_ORE, 16F);
		MagisticsApi.addCrucibleRecipe(Blocks.LAPIS_ORE, 9F);
		MagisticsApi.addCrucibleRecipe(Blocks.GOLD_ORE, 25F);
		MagisticsApi.addCrucibleRecipe(Blocks.OBSIDIAN, 16F);
		MagisticsApi.addCrucibleRecipe(Blocks.DIAMOND_ORE, 64F);
	}

	private void registerInfuserRecipes() {
		MagisticsApi.addInfuserRecipe(new ItemStack(Blocks.STONE), new ItemStack[] { new ItemStack(Blocks.DIRT), new ItemStack(Blocks.SAND) }, 20F, false);
		MagisticsApi.addInfuserRecipe(new ItemStack(Blocks.RED_NETHER_BRICK), new ItemStack[] { new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.STONE) }, 20F, true);
	}
}