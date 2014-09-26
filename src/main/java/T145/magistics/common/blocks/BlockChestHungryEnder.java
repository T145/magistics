package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.api.blocks.BlockApparatus;
import T145.magistics.common.Magistics;
import T145.magistics.common.lib.MagisticsUtils;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnder extends BlockApparatus {
	public EntityPlayer owner;

	public BlockChestHungryEnder() {
		super(Material.rock);
		setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public int getRenderType() {
		return Magistics.proxy.renderID[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("magistics:chest_hungry/ender");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random rand) {
		for (int l = 0; l < 3; ++l) {
			double d6 = (double) ((float) i + rand.nextFloat());
			double d1 = (double) ((float) j + rand.nextFloat());
			d6 = (double) ((float) k + rand.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			int j1 = rand.nextInt(2) * 2 - 1;
			d3 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			d5 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
			double d2 = (double) k + 0.5D + 0.25D * (double) j1;
			d5 = (double) (rand.nextFloat() * 1.0F * (float) j1);
			double d0 = (double) i + 0.5D + 0.25D * (double) i1;
			d3 = (double) (rand.nextFloat() * 1.0F * (float) i1);
			world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
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
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		if (tile != null && tile instanceof TileChestHungryEnder) {
			switch (MathHelper.floor_double((double) (user.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) {
			case 0:
				tile.orientation = ForgeDirection.getOrientation(2);
				break;
			case 1:
				tile.orientation = ForgeDirection.getOrientation(5);
				break;
			case 2:
				tile.orientation = ForgeDirection.getOrientation(3);
				break;
			case 3:
				tile.orientation = ForgeDirection.getOrientation(4);
				break;
			}
			world.markBlockForUpdate(i, j, k);
		}
	}

	public InventoryEnderChest getEnderInventory(EntityPlayer player, World world, int i, int j, int k) {
		if (owner == null || owner != player || world.getBlock(i, j + 1, k).isNormalCube())
			return null;
		else
			return player.getInventoryEnderChest();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		MagisticsUtils.absorbCollidingItemStackIntoInventory(entity, (TileChestHungryEnder) world.getTileEntity(i, j, k), getEnderInventory(owner, world, i, j, k), this, 2, 2, world, i, j, k, true);
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