package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.api.blocks.BlockApparatusContainer;
import T145.magistics.client.lib.TextureHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArcaneStone extends BlockApparatusContainer {
	public static enum Types {
		base, brick;
	}

	public static IIcon base[] = new IIcon[16], brick[] = new IIcon[16];

	public BlockArcaneStone() {
		super(Material.rock);
	}

	@Override
	public void registerBlockIcons(IIconRegister r) {
		base[0] = r.registerIcon("magistics:infusion_workbench/base/0");
		base[1] = r.registerIcon("magistics:infusion_workbench/base/1_d");
		base[2] = r.registerIcon("magistics:infusion_workbench/base/1_u");
		base[3] = r.registerIcon("magistics:infusion_workbench/base/1_l");
		base[4] = r.registerIcon("magistics:infusion_workbench/base/1_r");
		base[5] = r.registerIcon("magistics:infusion_workbench/base/2_h");
		base[6] = r.registerIcon("magistics:infusion_workbench/base/2_v");
		base[7] = r.registerIcon("magistics:infusion_workbench/base/2_dl");
		base[8] = r.registerIcon("magistics:infusion_workbench/base/2_dr");
		base[9] = r.registerIcon("magistics:infusion_workbench/base/2_ul");
		base[10] = r.registerIcon("magistics:infusion_workbench/base/2_ur");
		base[11] = r.registerIcon("magistics:infusion_workbench/base/3_d");
		base[12] = r.registerIcon("magistics:infusion_workbench/base/3_u");
		base[13] = r.registerIcon("magistics:infusion_workbench/base/3_l");
		base[14] = r.registerIcon("magistics:infusion_workbench/base/3_r");
		base[15] = r.registerIcon("magistics:infusion_workbench/base/4");

		brick[0] = r.registerIcon("magistics:infusion_workbench/brick/0");
		brick[1] = r.registerIcon("magistics:infusion_workbench/brick/1_d");
		brick[2] = r.registerIcon("magistics:infusion_workbench/brick/1_u");
		brick[3] = r.registerIcon("magistics:infusion_workbench/brick/1_l");
		brick[4] = r.registerIcon("magistics:infusion_workbench/brick/1_r");
		brick[5] = r.registerIcon("magistics:infusion_workbench/brick/2_h");
		brick[6] = r.registerIcon("magistics:infusion_workbench/brick/2_v");
		brick[7] = r.registerIcon("magistics:infusion_workbench/brick/2_dl");
		brick[8] = r.registerIcon("magistics:infusion_workbench/brick/2_dr");
		brick[9] = r.registerIcon("magistics:infusion_workbench/brick/2_ul");
		brick[10] = r.registerIcon("magistics:infusion_workbench/brick/2_ur");
		brick[11] = r.registerIcon("magistics:infusion_workbench/brick/3_d");
		brick[12] = r.registerIcon("magistics:infusion_workbench/brick/3_u");
		brick[13] = r.registerIcon("magistics:infusion_workbench/brick/3_l");
		brick[14] = r.registerIcon("magistics:infusion_workbench/brick/3_r");
		brick[15] = r.registerIcon("magistics:infusion_workbench/brick/4");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs t, List l) {
		for (int i = 0; i <= 1; i++)
			l.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess ib, int i, int j, int k, int side) {
		switch (ib.getBlockMetadata(i, j, k)) {
		case 0:
			return TextureHelper.getConnectedBlockTexture(ib, i, j, k, side, base, this);
		case 1:
			return TextureHelper.getConnectedBlockTexture(ib, i, j, k, side, brick, this);
		default:
			return blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (meta) {
		case 0:
			return base[0];
		case 1:
			return brick[0];
		default:
			return blockIcon;
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess ib, int i, int j, int k, int side) {
		return ib.getBlock(i, j, k) == (Block) this ? false : super.shouldSideBeRendered(ib, i, j, k, side);
	}

	@Override
	public float getBlockHardness(World world, int i, int j, int k) {
		if (world.getBlockMetadata(i, j, k) == 1)
			return 16F;
		else
			return 4F;
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int i, int j, int k, double explosionX, double explosionY, double explosionZ) {
		if (world.getBlockMetadata(i, j, k) == 1)
			return 400F;
		else
			return 100F;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess ib, int i, int j, int k, int beaconX, int beaconY, int beaconZ) {
		return true;
	}
}