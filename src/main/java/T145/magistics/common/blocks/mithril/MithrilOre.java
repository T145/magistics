package T145.magistics.common.blocks.mithril;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilOre extends Block {
	private Random rand = new Random();

	public MithrilOre(Material mat) {
		super(mat);
		setHarvestLevel("pickaxe", 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.blockIcon = r.registerIcon("mithril:" + getUnlocalizedName().substring(5));
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return false;
	}

	@Override
	public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_) {
		return MathHelper.getRandomIntegerInRange(rand, 3, 7);
	}
}