package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnderItem extends ItemBlock {
	private BlockChestHungryEnder enderChest;

	public BlockChestHungryEnderItem(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean mod) {
		if (enderChest.owner != null)
			l.add(StatCollector.translateToLocal("Owner: " + enderChest.owner));
	}
}