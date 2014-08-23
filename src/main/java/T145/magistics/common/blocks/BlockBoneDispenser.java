package T145.magistics.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBoneDispenser extends BlockApparatus {
	@SideOnly(Side.CLIENT)
	public static IIcon icon[];

	public BlockBoneDispenser() {
		super(Material.rock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("furnace_side");
		icon[0] = r.registerIcon("furnace_top");
		icon[1] = r.registerIcon("magistics:dispenser_bone_front_horizontal");
		icon[2] = r.registerIcon("magistics:dispenser_bone_front_vertical");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		int k = meta & 7;
		return side == k ? (k != 1 && k != 0 ? icon[1] : icon[2]) : (k != 1 && k != 0 ? (side != 1 && side != 0 ? blockIcon : icon[0]) : icon[0]);
	}
}