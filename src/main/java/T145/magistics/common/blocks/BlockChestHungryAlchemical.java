package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandable;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryAlchemical;

import com.pahimar.ee3.block.BlockAlchemicalChest;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryAlchemical extends BlockAlchemicalChest implements IWandable {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("EE3:alchemicalChest");
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileChestHungryAlchemical(0);
		case 1:
			return new TileChestHungryAlchemical(1);
		case 2:
			return new TileChestHungryAlchemical(2);
		default:
			return null;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileChestHungryAlchemical) world.getTileEntity(i, j, k), this, 2, 2, world, i, j, k, true);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int i, int j, int k, int side) {
		TileChestHungryAlchemical tile = (TileChestHungryAlchemical) world.getTileEntity(i, j, k);
		if (Loader.isModLoaded("Botania"))
			return tile.onWanded(player, side);
		else
			return false;
	}
}