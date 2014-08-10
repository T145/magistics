package T145.magistics.common.blocks;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.lib.InventoryHelper;
import T145.magistics.common.Magistics;
import T145.magistics.common.tiles.TileChestHungryMod;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryMod extends BlockApparatus {
	private static boolean isTrapped, isClear, isExplosionResistant;

	public static enum Types {
		trapped(0),
		ender(1),
		ender_mod(2),
		iron(3, 9),
		gold(4, 9),
		diamond(5, 12),
		copper(6, 9),
		silver(7, 9),
		crystal(8, 12),
		obsidian(9, 12),
		dirt(10, 1);

		public int size = new TileChestHungryMod().getSizeInventory(), rowLength;

		public int getRowCount() {
			return size / rowLength;
		}

		private Types(int metadata) {
			switch (metadata) {
			case 0:
				isTrapped = true;
				break;
			case 8:
				isClear = true;
				break;
			case 9:
				isExplosionResistant = true;
				break;
			}
		}

		private Types(int metadata, int rowSize) {
			rowLength = rowSize;
		}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[] = new IIcon[Types.values().length];

	public BlockChestHungryMod() {
		super(Material.circuits);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		for (Types type : Types.values())
			icon[type.ordinal()] = r.registerIcon("magistics:chest_hungry_" + type.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item apparatus, CreativeTabs t, List l) {
		for (Types type : Types.values()) {
			for (int i = type.trapped.ordinal(); i <= type.ender.ordinal(); i++)
				l.add(new ItemStack(apparatus, 1, i));
			if (Loader.isModLoaded("EnderStorage"))
				l.add(new ItemStack(apparatus, 1, type.ender_mod.ordinal()));
			if (Loader.isModLoaded("IronChest"))
				for (int j = type.iron.ordinal(); j <= type.dirt.ordinal(); j++)
					l.add(new ItemStack(apparatus, 1, j));
		}
	}

	@Override
	public int getRenderType() {
		return Magistics.proxy.renderID[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return isClear ? 1 : 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryMod();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileEntity tile = world.getTileEntity(i, j, k);

		if (tile != null || !world.isRemote)
			if (entity instanceof EntityItem && !entity.isDead) {
				EntityItem item = ((EntityItem) entity);
				ItemStack leftovers = InventoryHelper.placeItemStackIntoInventory(item.getEntityItem(), (IInventory) tile, 1, true);

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
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int i1, float f1, float f2, float f3) {
		if (world.isRemote)
			return true;
		else {
			IInventory inventory = getInventory(world, i, j, k);

			if (inventory != null)
				player.openGui(Magistics.instance, world.getBlockMetadata(i, j, k), world, i, j, k);

			return true;
		}
	}

	public IInventory getInventory(World world, int i, int j, int k) {
		TileEntity tile = (TileChestHungryMod) world.getTileEntity(i, j, k);

		if (tile == null || world.isSideSolid(i, j + 1, k, DOWN) || isOcelotMounted(world, i, j, k) || world.getBlock(i - 1, j, k) == this && (world.isSideSolid(i - 1, j + 1, k, DOWN) || isOcelotMounted(world, i - 1, j, k)) || world.getBlock(i + 1, j, k) == this && (world.isSideSolid(i + 1, j + 1, k, DOWN) || isOcelotMounted(world, i + 1, j, k)) || world.getBlock(i, j, k - 1) == this && (world.isSideSolid(i, j + 1, k - 1, DOWN) || isOcelotMounted(world, i, j, k - 1)) || world.getBlock(i, j, k + 1) == this && (world.isSideSolid(i, j + 1, k + 1, DOWN) || isOcelotMounted(world, i, j, k + 1)))
			return null;
		else
			return (IInventory) tile;
	}

	public static boolean isOcelotMounted(World world, int i, int j, int k) {
		Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double) i, (double) (j + 1), (double) k, (double) (i + 1), (double) (j + 2), (double) (k + 1))).iterator();
		EntityOcelot ocelot;

		do {
			if (!iterator.hasNext())
				return false;
			ocelot = (EntityOcelot) iterator.next();
		} while (!ocelot.isSitting());

		return true;
	}

	@Override
	public float getExplosionResistance(Entity e, World world, int i, int j, int k, double explosionX, double explosionY, double explosionZ) {
		if (isExplosionResistant)
			return 10000F;
		else
			return super.getExplosionResistance(e, world, i, j, k, explosionX, explosionY, explosionZ);
	}

	@Override
	public boolean canProvidePower() {
		return isTrapped;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess ib, int i, int j, int k, int l) {
		return super.canProvidePower() ? MathHelper.clamp_int(((TileChestHungryMod) ib.getTileEntity(i, j, k)).numPlayersUsing, 0, 15) : 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess ib, int i, int j, int k, int meta) {
		return meta == Types.trapped.ordinal() ? isProvidingWeakPower(ib, i, j, k, meta) : 0;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int i, int j, int k, int l) {
		return Container.calcRedstoneFromInventory(getInventory(world, i, j, k));
	}
}