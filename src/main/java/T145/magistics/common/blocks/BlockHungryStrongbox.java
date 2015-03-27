package T145.magistics.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileHungryStrongbox;
import T145.magistics.common.tiles.TileHungryStrongboxCreative;
import cofh.core.enchantment.CoFHEnchantment;
import cofh.thermalexpansion.block.BlockTEBase;
import cofh.thermalexpansion.block.strongbox.BlockStrongbox;

public class BlockHungryStrongbox extends BlockTEBase {
	private BlockStrongbox strongbox = new BlockStrongbox();

	public BlockHungryStrongbox() {
		super(Material.iron);
		setHardness(20.0f);
		setResistance(120.0f);
		setBlockName("hungry_strongbox");
		setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int n) {
		if (n >= BlockStrongbox.Types.values().length || !BlockStrongbox.enable[BlockStrongbox.Types.CREATIVE.ordinal()])
			return null;
		if (n != BlockStrongbox.Types.CREATIVE.ordinal())
			return new TileHungryStrongbox(n);
		return new TileHungryStrongboxCreative(n);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		strongbox.getSubBlocks(item, tab, list);
	}

	@Override
	public void onBlockPlacedBy(World world, int n, int n2, int n3, EntityLivingBase entityLivingBase, ItemStack itemStack) {
		if (!BlockStrongbox.enable[world.getBlockMetadata(n, n2, n3)]) {
			world.setBlockToAir(n, n2, n3);
			return;
		}
		if (itemStack.stackTagCompound != null) {
			TileHungryStrongbox tileStrongbox = (TileHungryStrongbox) world.getTileEntity(n, n2, n3);
			tileStrongbox.enchant = (byte) EnchantmentHelper.getEnchantmentLevel(CoFHEnchantment.holding.effectId, itemStack);
			tileStrongbox.createInventory();
			if (itemStack.stackTagCompound.hasKey("Inventory")) {
				tileStrongbox.readInventoryFromNBT(itemStack.stackTagCompound);
			}
		}
		super.onBlockPlacedBy(world, n, n2, n3, entityLivingBase, itemStack);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileHungryStrongbox) world.getTileEntity(i, j, k), this, 2, 2, world, i, j, k, true);
	}

	@Override
	public float getBlockHardness(World world, int n, int n2, int n3) {
		return strongbox.getBlockHardness(world, n, n2, n3);
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int n, int n2, int n3, double n4, double n5, double n6) {
		return strongbox.getExplosionResistance(entity, world, n, n2, n3, n4, n5, n6);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public NBTTagCompound getItemStackTag(World world, int n, int n2, int n3) {
		NBTTagCompound itemStackTag = super.getItemStackTag(world, n, n2, n3);
		TileHungryStrongbox tileStrongbox = (TileHungryStrongbox) world.getTileEntity(n, n2, n3);
		if (tileStrongbox != null) {
			if (itemStackTag == null)
				itemStackTag = new NBTTagCompound();
			if (tileStrongbox.enchant > 0)
				CoFHEnchantment.addEnchantment(itemStackTag, CoFHEnchantment.holding.effectId, (int) tileStrongbox.enchant);
			tileStrongbox.writeInventoryToNBT(itemStackTag);
		}
		return itemStackTag;
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer entityPlayer, World world, int n, int n2, int n3, boolean b) {
		return strongbox.dismantleBlock(entityPlayer, world, n, n2, n3, b);
	}

	@Override
	public boolean canDismantle(EntityPlayer entityPlayer, World world, int n, int n2, int n3) {
		return strongbox.canDismantle(entityPlayer, world, n, n2, n3);
	}

	@Override
	public boolean initialize() {
		return false;
	}

	@Override
	public boolean postInit() {
		return false;
	}
}