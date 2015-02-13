package T145.magistics.common.blocks.mithril;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilDecoBlock extends Block {
	private static String[] types = new String[] { "normal", "brick", "carved" };
	private IIcon icons[], icon_top;

	public MithrilDecoBlock(Material mat) {
		super(mat);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (meta) {
		case 2:
			if (side == 0 || side == 1)
				return icon_top;
			break;
		}
		return icons[meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icons = new IIcon[MithrilDecoBlock.types.length];
		for (int i = 0; i < icons.length; i++)
			icons[i] = r.registerIcon(getTextureName() + "_" + MithrilDecoBlock.types[i]);
		icon_top = r.registerIcon(getTextureName() + "_top");
	}
}