package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMetalApparatus extends BlockApparatus {
	public static enum Types {
		crucible_eyes(0),
		crucible_thaumium(1),
		crucible_souls(2),
		arcane_retort(3),
		arcane_furnace(4),
		crystallizer(5),
		soul_brazier(6);

		private Types(int metadata) {}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	public BlockMetalApparatus() {
		super(Material.iron);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (meta) {
		default:
			return blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs t, List l) {
		for (Types type : Types.values())
			l.add(new ItemStack(i, 1, type.ordinal()));
	}
}