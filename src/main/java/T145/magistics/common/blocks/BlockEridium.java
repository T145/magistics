package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEridium extends Block {
	public static IIcon icon[] = new IIcon[2];

	public BlockEridium() {
		super(Material.rock);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:eridium_ore");
		icon[1] = r.registerIcon("magistics:eridium_block");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune) {
		return meta == 0 ? MathHelper.getRandomIntegerInRange(new Random(), 3, 7) : 0;
	}
}