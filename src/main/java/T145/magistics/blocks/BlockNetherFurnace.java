package T145.magistics.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.Magistics;
import T145.magistics.lib.InventoryHelper;
import T145.magistics.tiles.TileNetherFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNetherFurnace extends BlockContainer {
	public static final Block ACTIVE = new BlockNetherFurnace(true);
	public static final Block INACTIVE = new BlockNetherFurnace(false);

	private final boolean active;
	private static boolean keepInventory;

	@SideOnly(Side.CLIENT)
	private IIcon front;

	public BlockNetherFurnace(boolean isActive) {
		super(Material.rock);
		active = isActive;

		setBlockName("netherrack_furnace");
		setHardness(3.5F);
		setStepSound(soundTypePiston);

		if (active) {
			setLightLevel(0.875F);
		} else {
			setCreativeTab(Magistics.tabMagistics);
		}
	}

	@Override
	public Item getItemDropped(int fortune, Random random, int amount) {
		return Item.getItemFromBlock(INACTIVE);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		Blocks.furnace.onBlockAdded(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side != metadata ? blockIcon : front;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("netherrack");
		front = reg.registerIcon(active ? "magistics:netherrack_furnace_on" : "magistics:netherrack_furnace_off");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 3));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return Blocks.furnace.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	public static void updateFurnaceBlockState(boolean isActive, World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);
		keepInventory = true;

		if (isActive) {
			world.setBlock(x, y, z, ACTIVE);
		} else {
			world.setBlock(x, y, z, INACTIVE);
		}

		keepInventory = false;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (tile != null) {
			tile.validate();
			world.setTileEntity(x, y, z, tile);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileNetherFurnace();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		Blocks.furnace.onBlockPlacedBy(world, x, y, z, player, stack);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		if (!keepInventory) {
			InventoryHelper.emptyInventory(world, x, y, z);
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	public static void generateFurnaceParticles(World world, int x, int y, int z, Random rand, int facing) {
		float f = (float) x + 0.5F;
		float f1 = (float) y + 0F + rand.nextFloat() * 6F / 16F;
		float f2 = (float) z + 0.5F;
		float f3 = 0.52F;
		float f4 = rand.nextFloat() * 0.6F - 0.3F;

		if (facing == 4) {
			world.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0, 0, 0);
			world.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0, 0, 0);
		} else if (facing == 5) {
			world.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0, 0, 0);
			world.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0, 0, 0);
		} else if (facing == 2) {
			world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0, 0, 0);
			world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0, 0, 0);
		} else if (facing == 3) {
			world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0, 0, 0);
			world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0, 0, 0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (active) {
			generateFurnaceParticles(world, x, y, z, rand, world.getBlockMetadata(x, y, z));
		}
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int power) {
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(INACTIVE);
	}
}