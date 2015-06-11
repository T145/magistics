package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandable;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileSortingChestHungry;

import com.dynious.refinedrelocation.block.BlockSortingChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSortingChestHungry extends BlockSortingChest implements IWandable {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("thaumcraft:woodplain");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileSortingChestHungry();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileSortingChestHungry) world.getTileEntity(i, j, k), this, 2, 2, world, i, j, k, true);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int i, int j, int k, int side) {
		TileSortingChestHungry tile = (TileSortingChestHungry) world.getTileEntity(i, j, k);
		return tile.onWanded(player, side);
	}
}