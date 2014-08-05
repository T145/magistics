package T145.magistics.common.items.relics;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.config.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSeal extends Item {
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:arcane_seal");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int i, int j, int k, int side, float par8, float par9, float par10) {
		Block obstruction = world.getBlock(i, j, k);
		if (obstruction == Blocks.snow_layer && (world.getBlockMetadata(i, j, k) & 0x7) < 1)
			side = 1;
		else if (obstruction != Blocks.vine && obstruction != Blocks.tallgrass && obstruction != Blocks.deadbush && !obstruction.isReplaceable((IBlockAccess) world, i, j, k)) {
			switch (side) {
			case 0:
				j--;
				break;
			case 1:
				j++;
				break;
			case 2:
				k--;
				break;
			case 3:
				k++;
				break;
			case 4:
				i--;
				break;
			case 5:
				i++;
				break;
			}
		}
		if (is.stackSize == 0 || !player.canPlayerEdit(i, j, k, side, is) || !world.canPlaceEntityOnSide(Config.block[0], i, j, k, false, side, (Entity) player, is))
			return false;
		if (canPlaceBlockAt(is, player, world, i, j, k, side, par8, par9, par10, Config.block[0], is.getItemDamage())) {
			world.playSoundEffect((double) i + 0.5F, (double) j + 0.5F, (double) k + 0.5F, obstruction.stepSound.getStepResourcePath(), (obstruction.stepSound.getVolume() + 1.0F) / 2.0F, obstruction.stepSound.getPitch() * 0.8F);
			--is.stackSize;
			return true;
		}
		return false;
	}

	public boolean canPlaceBlockAt(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ, Block b, int meta) {
		if (!world.setBlock(i, j, k, b, meta, 3))
			return false;
		if (world.getBlock(i, j, k) == b) {
			b.onBlockPlacedBy(world, i, j, k, (EntityLivingBase) player, stack);
			b.onPostBlockPlaced(world, i, j, k, meta);
		}
		return true;
	}
}