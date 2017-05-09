package T145.magistics.network;

import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.init.ModBlocks;
import T145.magistics.init.ModEntities;
import T145.magistics.init.ModItems;
import T145.magistics.tiles.crafting.TileInfuser;
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

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return ((TileInfuser) tile).createContainer(player.inventory, player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
		registerCrucibleRecipes();
		registerInfuserRecipes();
	}

	private void registerCrucibleRecipes() {
		RecipeRegistry.addCrucibleRecipe(Items.STICK, 0.25F);
		RecipeRegistry.addCrucibleRecipe(Items.CLAY_BALL, 1F);
		RecipeRegistry.addCrucibleRecipe(Items.BRICK, 2F);
		RecipeRegistry.addCrucibleRecipe(Items.FLINT, 1F);
		RecipeRegistry.addCrucibleRecipe(Items.COAL, 2F);
		RecipeRegistry.addCrucibleRecipe(Items.SNOWBALL, 0.25F);
		RecipeRegistry.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 0), 4F);
		RecipeRegistry.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 2), 4F);
		RecipeRegistry.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 3), 25F);
		RecipeRegistry.addCrucibleRecipe(new ItemStack(Items.DYE, 1, 4), 9F);
		RecipeRegistry.addCrucibleRecipe(Items.IRON_INGOT, 5F);
		RecipeRegistry.addCrucibleRecipe(Items.BEETROOT_SEEDS, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.MELON_SEEDS, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.PUMPKIN_SEEDS, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.WHEAT_SEEDS, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.FEATHER, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.BONE, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.MELON, 2F);
		RecipeRegistry.addCrucibleRecipe(Items.COOKED_BEEF, 5F);
		RecipeRegistry.addCrucibleRecipe(Items.BEEF, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.COOKED_CHICKEN, 5F);
		RecipeRegistry.addCrucibleRecipe(Items.CHICKEN, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.COOKED_PORKCHOP, 5F);
		RecipeRegistry.addCrucibleRecipe(Items.PORKCHOP, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.COOKED_FISH, 5F);
		RecipeRegistry.addCrucibleRecipe(Items.FISH, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.NETHER_WART, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.ROTTEN_FLESH, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.STRING, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.LEATHER, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.WHEAT, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.REEDS, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.GUNPOWDER, 10F);
		RecipeRegistry.addCrucibleRecipe(Items.GLOWSTONE_DUST, 9F);
		RecipeRegistry.addCrucibleRecipe(Items.REDSTONE, 6F);
		RecipeRegistry.addCrucibleRecipe(Items.MILK_BUCKET, 18F);
		RecipeRegistry.addCrucibleRecipe(Items.WATER_BUCKET, 16F);
		RecipeRegistry.addCrucibleRecipe(Items.LAVA_BUCKET, 17F);
		RecipeRegistry.addCrucibleRecipe(Items.EGG, 5F);
		RecipeRegistry.addCrucibleRecipe(Items.GOLD_INGOT, 27F);
		RecipeRegistry.addCrucibleRecipe(Items.GOLD_NUGGET, 3F);
		RecipeRegistry.addCrucibleRecipe(Items.SLIME_BALL, 25F);
		RecipeRegistry.addCrucibleRecipe(Items.APPLE, 4F);
		RecipeRegistry.addCrucibleRecipe(Items.DIAMOND, 64F);
		RecipeRegistry.addCrucibleRecipe(Items.ENDER_PEARL, 64F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_13, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_CAT, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_11, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_CHIRP, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_FAR, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_MALL, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_MELLOHI, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_STAL, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_STRAD, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.RECORD_WARD, 100F);
		RecipeRegistry.addCrucibleRecipe(Items.BLAZE_ROD, 36F);
		RecipeRegistry.addCrucibleRecipe(Items.GHAST_TEAR, 64F);
		RecipeRegistry.addCrucibleRecipe(Items.SPIDER_EYE, 9F);
		RecipeRegistry.addCrucibleRecipe(Items.SADDLE, 64F);

		RecipeRegistry.addCrucibleRecipe(Blocks.COBBLESTONE, 0.1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.PLANKS, 0.5F);
		RecipeRegistry.addCrucibleRecipe(Blocks.MOSSY_COBBLESTONE, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.SAND, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.SANDSTONE, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.RED_SANDSTONE, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.DIRT, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.GRASS, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.GLASS, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.ICE, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.GRAVEL, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.STONE, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.WATERLILY, 3F);
		RecipeRegistry.addCrucibleRecipe(Blocks.WEB, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.NETHER_BRICK, 2F);
		RecipeRegistry.addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 1), 1F);
		RecipeRegistry.addCrucibleRecipe(new ItemStack(Blocks.STONEBRICK, 1, 2), 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.NETHERRACK, 1F);
		RecipeRegistry.addCrucibleRecipe(Blocks.SOUL_SAND, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.COAL_ORE, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.WOOL, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.LOG, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.LEAVES, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.TALLGRASS, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.DEADBUSH, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.CACTUS, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.SAPLING, 2F);
		RecipeRegistry.addCrucibleRecipe(Blocks.MYCELIUM, 3F);
		RecipeRegistry.addCrucibleRecipe(Blocks.IRON_ORE, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.YELLOW_FLOWER, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.RED_FLOWER, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.BROWN_MUSHROOM_BLOCK, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.RED_MUSHROOM_BLOCK, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.VINE, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.PUMPKIN, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.REEDS, 4F);
		RecipeRegistry.addCrucibleRecipe(Blocks.REDSTONE_ORE, 16F);
		RecipeRegistry.addCrucibleRecipe(Blocks.LAPIS_ORE, 9F);
		RecipeRegistry.addCrucibleRecipe(Blocks.GOLD_ORE, 25F);
		RecipeRegistry.addCrucibleRecipe(Blocks.OBSIDIAN, 16F);
		RecipeRegistry.addCrucibleRecipe(Blocks.DIAMOND_ORE, 64F);
	}

	private void registerInfuserRecipes() {
		RecipeRegistry.addInfuserRecipe(new ItemStack(Blocks.STONE), new ItemStack[] { new ItemStack(Blocks.DIRT), new ItemStack(Blocks.SAND) }, 20F, false);
	}
}