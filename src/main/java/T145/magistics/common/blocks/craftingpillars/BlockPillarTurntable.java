package T145.magistics.common.blocks.craftingpillars;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.craftingpillars.TilePillarTurntable;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarTurntable extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarTurntable(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return renderID;
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity) {
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TilePillarTurntable tile = ((TilePillarTurntable) world.getTileEntity(x, y, z));
		if (tile.getDisk() == null) {
			if (side == 1) {
				ItemStack disk = player.getCurrentEquippedItem();
				if (disk != null && disk.getItem() instanceof ItemRecord) {
					if (disk.isItemEqual(new ItemStack(ConfigObjects.itemDiscElysium)))
						player.addStat(ConfigObjects.achievementDisc, 1);
					insertRecord(world, x, y, z, player.getCurrentEquippedItem());
					world.playAuxSFXAtEntity(null, 1005, x, y, z, Item.getIdFromItem(disk.getItem()));
					if (!player.capabilities.isCreativeMode)
						--player.getCurrentEquippedItem().stackSize;
					return true;
				}
			}
		} else if (side == 1) {
			ejectRecord(world, x, y, z);
			return true;
		}
		if (side != 1)
			tile.showNum = !tile.showNum;
		return true;
	}

	public void insertRecord(World world, int x, int y, int z, ItemStack item) {
		if (!world.isRemote) {
			TilePillarTurntable tileentityrecordplayer = (TilePillarTurntable) world.getTileEntity(x, y, z);

			if (tileentityrecordplayer != null)
				tileentityrecordplayer.setDisk(item.copy());
		}
	}

	public void ejectRecord(World world, int x, int y, int z) {
		if (!world.isRemote) {
			TilePillarTurntable tileentityrecordplayer = (TilePillarTurntable) world.getTileEntity(x, y, z);

			if (tileentityrecordplayer != null) {
				ItemStack itemstack = tileentityrecordplayer.getDisk();

				if (itemstack != null) {
					world.playAuxSFX(1005, x, y, z, 0);
					world.playRecord((String) null, x, y, z);
					tileentityrecordplayer.setDisk((ItemStack) null);
					float f = 0.7F;
					double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
					double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.2D + 0.6D;
					double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
					ItemStack itemstack1 = itemstack.copy();
					EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, itemstack1);
					entityitem.delayBeforeCanPickup = 10;
					world.spawnEntityInWorld(entityitem);
				}
			}
		}
	}

	@Override
	public void breakBlock(World world, int par2, int par3, int par4, Block block, int par6) {
		ejectRecord(world, par2, par3, par4);
		super.breakBlock(world, par2, par3, par4, block, par6);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7) {
		if (!world.isRemote)
			super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, 0);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TilePillarTurntable();
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int p_149736_4_, int z) {
		ItemStack itemstack = ((TilePillarTurntable) world.getTileEntity(x, y, z)).getDisk();
		return itemstack == null ? 0 : Item.getIdFromItem(itemstack.getItem()) + 1 - Item.getIdFromItem(Items.record_13);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("craftingpillars:craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return blockIcon;
	}
}