package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSortingChestHungryAlchemicalItem extends ItemBlock {
	public String[] size = { "small", "medium", "large" };

	public BlockSortingChestHungryAlchemicalItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + ".hungry";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer entityPlayer, List list, boolean flag) {
		for (int i = 0; i < size.length; i++)
			if (is.getItemDamage() == i)
				list.add(StatCollector.translateToLocal("tooltip.ee3:alchemicalChestPrefix." + size[i]));
	}
}