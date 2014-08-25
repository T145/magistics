package T145.magistics.common.blocks;

import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnder extends BlockEnderChest {
	public EntityPlayer owner;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("magistics:chest_hungry_ender");
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase user, ItemStack is) {
		owner = (EntityPlayer) user;
		world.setBlock(i, j, k, this, BlockPistonBase.determineOrientation(world, i, j, k, (EntityLivingBase) user), 3);
	}

	public InventoryEnderChest getEnderInventory(EntityPlayer player, World world, int i, int j, int k) {
		if (owner != null && player == owner && !world.isRemote && !world.getBlock(i, j + 1, k).isNormalCube())
			return player.getInventoryEnderChest();
		else
			return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileEntity tile = world.getTileEntity(i, j, k);
		if (tile == null || world.isRemote)
			return;
		if (entity instanceof EntityItem && !entity.isDead) {
			EntityItem item = (EntityItem) entity;
			ItemStack leftovers = InventoryHelper.placeItemStackIntoInventory(item.getEntityItem(), getEnderInventory(owner, world, i, j, k), 1, true);

			if (leftovers == null || leftovers.stackSize != item.getEntityItem().stackSize) {
				world.playSoundAtEntity(entity, "random.eat", 0.25F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
				world.addBlockEvent(i, j, k, this, 2, 2);
			}
			if (leftovers != null)
				item.setEntityItemStack(leftovers);
			else
				entity.setDead();
		}
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		InventoryEnderChest enderInv = getEnderInventory(player, world, i, j, k);
		TileChestHungryEnder enderChest = (TileChestHungryEnder) world.getTileEntity(i, j, k);

		if (enderInv != null && enderChest != null) {
			enderInv.func_146031_a(enderChest);
			owner.displayGUIChest(enderInv);
			return true;
		} else
			return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryEnder();
	}
}