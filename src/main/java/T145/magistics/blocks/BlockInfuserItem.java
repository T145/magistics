package T145.magistics.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class BlockInfuserItem extends ItemBlock {
	public BlockInfuserItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		String postfix = "";

		if (is.getItemDamage() == 1) {
			postfix = ".dark";
		}

		return super.getUnlocalizedName() + postfix;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("owner")) {
			String owner = getOwner(stack);

			if (owner.length() > 0) {
				list.add(BlockInfuser.isDark(stack.getItemDamage()) ? EnumChatFormatting.LIGHT_PURPLE : EnumChatFormatting.AQUA + "Owner: " + owner);
			}
		}
	}

	public String getOwner(ItemStack stack) {
		if (stack.hasTagCompound()) {
			NBTTagCompound tag = stack.stackTagCompound;
			return tag.getString("owner");
		}
		return null;
	}

	public void setOwner(ItemStack stack, String owner) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.stackTagCompound.setString("owner", owner);
	}
}