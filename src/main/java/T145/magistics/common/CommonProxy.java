package T145.magistics.common;

import T145.magistics.api.crafting.RecipeRegistry;
import T145.magistics.api.internal.IProxy;
import T145.magistics.common.containers.ContainerInfuser;
import T145.magistics.common.network.PacketHandler;
import T145.magistics.common.tiles.TileInfuser;
import T145.magistics.core.Magistics;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy implements IProxy, IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);

		switch (ID) {
		case 0:
			return new ContainerInfuser(player.inventory, (TileInfuser) tile);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Magistics.instance, this);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		PacketHandler.registerMessages();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		registerCrucibleRecipes();
	}

	private ItemStack toStack(Block block) {
		return new ItemStack(block, 1);
	}

	private ItemStack toStack(Item item) {
		return new ItemStack(item, 1);
	}

	private void registerCrucibleRecipes() {
		RecipeRegistry.addCrucibleRecipe(toStack(Items.DIAMOND), 2);
		RecipeRegistry.addCrucibleRecipe(toStack(Blocks.DIAMOND_BLOCK), 18);
	}
}