package T145.magistics.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import T145.magistics.Magistics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEntropicDispenser extends BlockDispenser {
	public static final Block INSTANCE = new BlockEntropicDispenser();

	public BlockEntropicDispenser() {
		super();

		setBlockName("entropic_dispenser");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("furnace_side");
		field_149944_M = r.registerIcon("furnace_top");
		field_149945_N = r.registerIcon("magistics:entropic_dispenser_front_horizontal");
		field_149946_O = r.registerIcon("magistics:entropic_dispenser_front_vertical");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 3));
	}
}