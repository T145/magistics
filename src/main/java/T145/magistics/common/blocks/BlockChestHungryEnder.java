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
import T145.magistics.common.lib.MagisticsUtils;
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
		if (owner == null || owner != player || world.getBlock(i, j + 1, k).isNormalCube())
			return null;
		else
			return player.getInventoryEnderChest();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		InventoryEnderChest enderInv = getEnderInventory(owner, world, i, j, k);

		if (enderInv != null && entity instanceof EntityItem)
			MagisticsUtils.absorbToInventory(world, i, j, k, (EntityItem) entity, this, 2, 2, owner.getInventoryEnderChest(), true);
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