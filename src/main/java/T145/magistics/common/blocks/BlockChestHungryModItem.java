package T145.magistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.common.blocks.BlockChestHungryMod.Types;
import T145.magistics.common.tiles.TileChestHungryMod;

public class BlockChestHungryModItem extends BlockApparatusItem {
	public BlockChestHungryModItem(Block b) {
		super(b);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "." + Types.values()[is.getItemDamage()];
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		TileChestHungryMod tile = (TileChestHungryMod) world.getTileEntity(x, y, z);
		if (tile != null) {
			switch (MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5) & 0x3) {
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
			world.markBlockForUpdate(x, y, x);
		}
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
	}
}