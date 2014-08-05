package T145.magistics.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneApparatus extends BlockApparatus {
	public static enum Types {
		arcane_seal(0);

		private Types(int metadata) {}
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	public BlockStoneApparatus() {
		super(Material.rock);
	}
}