package T145.magistics.common.items.curios;

import java.util.List;

import javax.annotation.Nullable;

import T145.magistics.api.front.IModelProvider;
import T145.magistics.common.items.base.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCodexArcanum extends ItemBase implements IModelProvider {

	public ItemCodexArcanum() {
		super("codex_arcanum");
		setHasSubtypes(true);
		setMaxStackSize(1);
	}

	@Override
	public void initModel() {
		IModelProvider.registerItemModel(this, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		// add items if there's a cheat sheet
		super.getSubItems(tab, items);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		// add info if there's a cheat sheet
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
}