package T145.magistics.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandable;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryMetals;
import T145.magistics.common.tiles.TileChestHungryVoid;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryRailcraft extends BlockContainer implements IWandable {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[2];

	public BlockChestHungryRailcraft() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileChestHungryMetals();
		case 1:
			return new TileChestHungryVoid();
		default:
			return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("railcraft:chest.metals");
		icon[1] = r.registerIcon("railcraft:chest.void");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileEntity tile = world.getTileEntity(i, j, k);

		switch (world.getBlockMetadata(i, j, k)) {
		case 0:
			InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileChestHungryMetals) tile, this, 2, 2, world, i, j, k, true);
			break;
		case 1:
			InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileChestHungryVoid) tile, this, 2, 2, world, i, j, k, true);
			break;
		}
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int i, int j, int k, int side) {
		TileEntity tile = world.getTileEntity(i, j, k);

		switch (world.getBlockMetadata(i, j, k)) {
		case 0:
			return ((TileChestHungryMetals) tile).onWanded(player, side);
		case 1:
			return ((TileChestHungryVoid) tile).onWanded(player, side);
		default:
			return false;
		}
	}
}