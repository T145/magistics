package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import T145.magistics.common.Magistics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFragileApparatus extends BlockApparatus {
	public static enum Types {
		crystal_capacitor(0),
		crystal_core(1),
		thaumic_duplicator(2),
		thaumic_repairer(3),
		essentia_pipe(4),
		essentia_pump(5),
		essentia_tank(6),
		alchemical_ultracentrifuge(7);

		private Types(int metadata) {}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	public BlockFragileApparatus() {
		super(Material.glass);
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

	@Override
	public int getRenderType() {
		return Magistics.proxy.renderID[0];
	}
}