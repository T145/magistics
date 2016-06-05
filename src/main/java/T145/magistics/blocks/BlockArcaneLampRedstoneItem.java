package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.tiles.TileArcaneLamp;

public class BlockArcaneLampRedstoneItem extends ItemBlock {
	public BlockArcaneLampRedstoneItem(Block block) {
		super(block);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean placed = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		TileArcaneLamp tile = (TileArcaneLamp) world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof TileArcaneLamp) {
			tile.facing = ForgeDirection.getOrientation(side).getOpposite();
			world.markBlockForUpdate(x, y, x);
		}

		return placed;
	}
}