package T145.magistics.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import thaumcraft.common.items.ItemShard;
import T145.magistics.Magistics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShardFragment extends ItemShard {
	public static final Item INSTANCE = new ItemShardFragment();

	public ItemShardFragment() {
		super();
		setCreativeTab(Magistics.tabMagistics);
		setUnlocalizedName("shard_fragment");
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		icon = ir.registerIcon("magistics:shard_fragment");
		iconBalanced = ir.registerIcon("magistics:shard_fragment_balanced");
	}
}