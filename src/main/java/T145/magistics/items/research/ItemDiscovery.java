package T145.magistics.items.research;

import T145.magistics.api.research.ResearchType;
import T145.magistics.items.MItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemDiscovery extends MItem {

	public ItemDiscovery() {
		super("research_discovery", ResearchType.getTypes());
	}

	// show the research page when right-clicked; already discovered
	// craft w/ codex arcanum to add the entry
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		return super.onItemRightClick(world, player, hand);
	}
}