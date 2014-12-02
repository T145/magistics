package T145.magistics.common.blocks;

import net.minecraft.block.BlockEnderChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockChestHungryEnder extends BlockEnderChest {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	private EntityPlayer master;

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int i, int j, int k, int rs) {
		TileEntity tile = world.getTileEntity(i, j, k);
		if (tile != null && tile instanceof IInventory)
			return Container.calcRedstoneFromInventory((IInventory) tile);
		else
			return 0;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase user, ItemStack is) {
		if (user instanceof EntityPlayer)
			master = (EntityPlayer) user;
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		if (tile != null)
			tile.owner = master.getCommandSenderName();
		super.onBlockPlacedBy(world, i, j, k, user, is);
	}

	public InventoryEnderChest getEnderInventory(World world, int i, int j, int k) {
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		if (master.getCommandSenderName().equals(tile.owner))
			return master.getInventoryEnderChest();
		else
			return null;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer user, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		InventoryEnderChest enderInv = getEnderInventory(world, i, j, k);
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);

		if (enderInv != null && tile != null && master == user) {
			if (world.getBlock(i, j + 1, k).isNormalCube() || world.isRemote)
				return true;
			else {
				enderInv.func_146031_a(tile);
				user.displayGUIChest(enderInv);
				return true;
			}
		} else
			return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, getEnderInventory(world, i, j, k), this, 2, 2, world, i, j, k, true);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryEnder();
	}
}