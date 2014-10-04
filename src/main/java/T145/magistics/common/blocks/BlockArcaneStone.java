package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import T145.magistics.api.blocks.BlockApparatusContainer;
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

	// TODO: CLEAN THIS UP!
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

	public boolean shouldConnectToBlock(IBlockAccess ib, int i, int j, int k, Block block, int meta) {
		return block == (Block) this && meta == ib.getBlockMetadata(i, j, k);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess ib, int i, int j, int k, int side) {
		switch (ib.getBlockMetadata(i, j, k)) {
		case 0:
			return getConnectedBlockTexture(ib, i, j, k, side, base);
		case 1:
			return getConnectedBlockTexture(ib, i, j, k, side, brick);
		default:
			return blockIcon;
		}
		//return ib.getBlockMetadata(i, j, k) == 15 ? base[0] : getConnectedBlockTexture(ib, i, j, k, side, base);
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

	public IIcon getConnectedBlockTexture(IBlockAccess ib, int i, int j, int k, int side, IIcon[] icon) {
		boolean isOpenUp = false, isOpenDown = false, isOpenLeft = false, isOpenRight = false;

		switch (side) {
		case 0:
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i - 1, j, k), ib.getBlockMetadata(i - 1, j, k)))
				isOpenDown = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i + 1, j, k), ib.getBlockMetadata(i + 1, j, k)))
				isOpenUp = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k - 1), ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k + 1), ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[11];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[12];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[13];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[14];
			else if (isOpenDown && isOpenUp)
				return icon[5];
			else if (isOpenLeft && isOpenRight)
				return icon[6];
			else if (isOpenDown && isOpenLeft)
				return icon[8];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[9];
			else if (isOpenDown)
				return icon[3];
			else if (isOpenUp)
				return icon[4];
			else if (isOpenLeft)
				return icon[2];
			else if (isOpenRight)
				return icon[1];
			break;
		case 1:
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i - 1, j, k), ib.getBlockMetadata(i - 1, j, k)))
				isOpenDown = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i + 1, j, k), ib.getBlockMetadata(i + 1, j, k)))
				isOpenUp = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k - 1), ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k + 1), ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight) {
				return icon[15];
			} else if (isOpenUp && isOpenDown && isOpenLeft) {
				return icon[11];
			} else if (isOpenUp && isOpenDown && isOpenRight) {
				return icon[12];
			} else if (isOpenUp && isOpenLeft && isOpenRight) {
				return icon[13];
			} else if (isOpenDown && isOpenLeft && isOpenRight) {
				return icon[14];
			} else if (isOpenDown && isOpenUp) {
				return icon[5];
			} else if (isOpenLeft && isOpenRight) {
				return icon[6];
			} else if (isOpenDown && isOpenLeft) {
				return icon[8];
			} else if (isOpenDown && isOpenRight) {
				return icon[10];
			} else if (isOpenUp && isOpenLeft) {
				return icon[7];
			} else if (isOpenUp && isOpenRight) {
				return icon[9];
			} else if (isOpenDown) {
				return icon[3];
			} else if (isOpenUp) {
				return icon[4];
			} else if (isOpenLeft) {
				return icon[2];
			} else if (isOpenRight) {
				return icon[1];
			}
			break;
		case 2:
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j - 1, k), ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j + 1, k), ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i - 1, j, k), ib.getBlockMetadata(i - 1, j, k)))
				isOpenLeft = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i + 1, j, k), ib.getBlockMetadata(i + 1, j, k)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[13];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[14];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[9];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[8];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[4];
			else if (isOpenRight)
				return icon[3];
			break;
		case 3:
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j - 1, k), ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j + 1, k), ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i - 1, j, k), ib.getBlockMetadata(i - 1, j, k)))
				isOpenLeft = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i + 1, j, k), ib.getBlockMetadata(i + 1, j, k)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[14];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[13];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[10];
			else if (isOpenDown && isOpenRight)
				return icon[9];
			else if (isOpenUp && isOpenLeft)
				return icon[8];
			else if (isOpenUp && isOpenRight)
				return icon[7];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[3];
			else if (isOpenRight)
				return icon[4];
			break;
		case 4:
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j - 1, k), ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j + 1, k), ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k - 1), ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k + 1), ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[14];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[13];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[10];
			else if (isOpenDown && isOpenRight)
				return icon[9];
			else if (isOpenUp && isOpenLeft)
				return icon[8];
			else if (isOpenUp && isOpenRight)
				return icon[7];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[3];
			else if (isOpenRight)
				return icon[4];
			break;
		case 5:
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j - 1, k), ib.getBlockMetadata(i, j - 1, k)))
				isOpenDown = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j + 1, k), ib.getBlockMetadata(i, j + 1, k)))
				isOpenUp = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k - 1), ib.getBlockMetadata(i, j, k - 1)))
				isOpenLeft = true;
			if (shouldConnectToBlock(ib, i, j, k, ib.getBlock(i, j, k + 1), ib.getBlockMetadata(i, j, k + 1)))
				isOpenRight = true;

			if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
				return icon[15];
			else if (isOpenUp && isOpenDown && isOpenLeft)
				return icon[13];
			else if (isOpenUp && isOpenDown && isOpenRight)
				return icon[14];
			else if (isOpenUp && isOpenLeft && isOpenRight)
				return icon[11];
			else if (isOpenDown && isOpenLeft && isOpenRight)
				return icon[12];
			else if (isOpenDown && isOpenUp)
				return icon[6];
			else if (isOpenLeft && isOpenRight)
				return icon[5];
			else if (isOpenDown && isOpenLeft)
				return icon[9];
			else if (isOpenDown && isOpenRight)
				return icon[10];
			else if (isOpenUp && isOpenLeft)
				return icon[7];
			else if (isOpenUp && isOpenRight)
				return icon[8];
			else if (isOpenDown)
				return icon[1];
			else if (isOpenUp)
				return icon[2];
			else if (isOpenLeft)
				return icon[4];
			else if (isOpenRight)
				return icon[3];
			break;
		}

		return icon[0];
	}
}