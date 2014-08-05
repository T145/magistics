package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneApparatus extends BlockApparatus {
	public static enum Types {
		arcane_seal(0),
		infuser(1),
		infuser_dark(2),
		enchanter_thaumic(3),
		enchanter_occultic(4),
		eldritch_stone(5),
		everfull_urn(6);

		private Types(int metadata) {}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	public BlockStoneApparatus() {
		super(Material.rock);
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
	public IIcon getIcon(IBlockAccess ib, int i, int j, int k, int side) {
		switch (ib.getBlockMetadata(i, j, k)) {
		default:
			return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs t, List l) {
		for (Types type : Types.values())
			l.add(new ItemStack(i, 1, type.ordinal()));
	}
}