package T145.magistics.common.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.Magistics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTintedNitor extends Item {
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 12; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int par2) {
		return ItemDye.field_150922_c[is.getItemDamage()];
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return super.getUnlocalizedName() + "." + ItemDye.field_150922_c[is.getItemDamage()];
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		Block var11 = world.getBlock(x, y, z);
		if (var11 == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 0x7) < 1) {
			par7 = 1;
		} else if (var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush && !var11.isReplaceable((IBlockAccess) world, x, y, z)) {
			if (par7 == 0) {
				--y;
			}
			if (par7 == 1) {
				++y;
			}
			if (par7 == 2) {
				--z;
			}
			if (par7 == 3) {
				++z;
			}
			if (par7 == 4) {
				--x;
			}
			if (par7 == 5) {
				++x;
			}
		}
		if (itemstack.stackSize == 0 || !player.canPlayerEdit(x, y, z, par7, itemstack) || !world.canPlaceEntityOnSide(Magistics.proxy.tintedNitor, x, y, z, false, par7, player, itemstack))
			return false;
		if (placeBlockAt(itemstack, player, world, x, y, z, par7, par8, par9, par10, Magistics.proxy.tintedNitor, itemstack.getItemDamage())) {
			world.playSoundEffect((double) (x + 0.5f), (double) (y + 0.5f), (double) (z + 0.5f), Magistics.proxy.tintedNitor.stepSound.getStepResourcePath(), (Magistics.proxy.tintedNitor.stepSound.getVolume() + 1.0f) / 2.0f, Magistics.proxy.tintedNitor.stepSound.getPitch() * 0.8f);
			--itemstack.stackSize;
			return true;
		}
		return false;
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, Block bid, int meta) {
		if (!world.setBlock(x, y, z, bid, meta, 3))
			return false;
		if (world.getBlock(x, y, z) == bid) {
			bid.onBlockPlacedBy(world, x, y, z, (EntityLivingBase) player, stack);
			bid.onPostBlockPlaced(world, x, y, z, meta);
		}
		return true;
	}
}