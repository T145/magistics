package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnder extends BlockContainer {
	public EntityPlayer owner;

	public BlockChestHungryEnder() {
		super(Material.rock);
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("magistics:chest_hungry_ender");
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 22;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int amount) {
		return Item.getItemFromBlock(Blocks.obsidian);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 8;
	}

	@Override
	public boolean canSilkHarvest() {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase user, ItemStack is) {
		owner = (EntityPlayer) user;
		world.setBlock(i, j, k, this, BlockPistonBase.determineOrientation(world, i, j, k, (EntityLivingBase) user), 3);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (world.getTileEntity(i, j, k) != null || !world.isRemote)
			if (entity instanceof EntityItem && !entity.isDead) {
				EntityItem item = (EntityItem) entity;
				ItemStack leftovers = InventoryHelper.placeItemStackIntoInventory(item.getEntityItem(), owner.getInventoryEnderChest(), 1, true);

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
		InventoryEnderChest enderInv = owner.getInventoryEnderChest();
		TileChestHungryEnder enderChest = (TileChestHungryEnder) world.getTileEntity(i, j, k);

		if (enderInv != null && enderChest != null) {
			if (owner == null || player != owner || world.isRemote || world.getBlock(i, j + 1, k).isNormalCube())
				return true;
			else {
				enderInv.func_146031_a(enderChest);
				owner.displayGUIChest(enderInv);
				return true;
			}
		} else
			return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryEnder();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random rand) {
		for (int l = 0; l < 3; l++) {
			double d6 = (double) ((float) i + rand.nextFloat());
			double d1 = (double) ((float) j + rand.nextFloat());
			d6 = (double) ((float) k + rand.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			d5 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			d5 = (double) (rand.nextFloat() * 1.0F * (float) i1);
			d3 = (double) (rand.nextFloat() * 1.0F * (float) i1);
			world.spawnParticle("portal", (double) i + 0.5D + 0.25D * (double) i1, d1, (double) k + 0.5D + 0.25D * (double) i1, d3, d4, d5);
		}
	}
}