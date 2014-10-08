package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import T145.magistics.api.blocks.BlockApparatusContainer;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.common.tiles.TileInfusionWorkbench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfusionWorkbench extends BlockApparatusContainer {
	public static enum Types {
		base, corner1, corner2, corner3, corner4, brick;
	}

	public static IIcon iconGlow, base[] = new IIcon[16], corner[] = new IIcon[5], brick[] = new IIcon[16];

	public BlockInfusionWorkbench() {
		super(Material.rock);
	}

	@Override
	public void registerBlockIcons(IIconRegister r) {
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
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

		for (int i = 0; i <= corner.length - 1; i++)
			corner[i] = r.registerIcon("magistics:infusion_workbench/side" + i);

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
		l.add(new ItemStack(item, 1, 0));
		l.add(new ItemStack(item, 1, 5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess ib, int i, int j, int k, int side) {
		switch (ib.getBlockMetadata(i, j, k)) {
		case 0:
			return TextureHelper.getConnectedBlockTexture(ib, i, j, k, side, base, this);
		case 5:
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
			switch (side) {
			case 0: case 1:
				return corner[0];
			case 2: case 5:
				return corner[5];
			case 3: case 4:
				return corner[4];
			}
			break;
		case 2: {
			switch (side) {
			case 0: case 1:
				return corner[1];
			case 2: case 3:
				return corner[4];
			case 4: case 5:
				return corner[5];
			}
			break;
		}
		case 3:
			switch (side) {
			case 0: case 1:
				return corner[2];
			case 2: case 3:
				return corner[4];
			case 4: case 5:
				return corner[5];
			}
			break;
		case 4:
			switch (side) {
			case 0: case 1:
				return corner[3];
			case 2: case 5:
				return corner[4];
			case 3: case 4:
				return corner[5];
			}
			break;
		case 5:
			return brick[0];
		}
		return blockIcon;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess ib, int i, int j, int k, int side) {
		return ib.getBlock(i, j, k) == (Block) this ? false : super.shouldSideBeRendered(ib, i, j, k, side);
	}

	@Override
	public int damageDropped(int meta) {
		if (meta <= 4)
			return 0;
		else
			return meta;
	}

	@Override
	public float getBlockHardness(World world, int i, int j, int k) {
		if (world.getBlockMetadata(i, j, k) == 5)
			return 16F;
		else
			return 4F;
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int i, int j, int k, double explosionX, double explosionY, double explosionZ) {
		if (world.getBlockMetadata(i, j, k) == 5)
			return 400F;
		else
			return 100F;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess ib, int i, int j, int k, int beaconX, int beaconY, int beaconZ) {
		switch (ib.getBlockMetadata(i, j, k)) {
		case 0:
			return true;
		case 5:
			return true;
		default:
			return false;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if (meta == 1)
			return new TileInfusionWorkbench();
		else
			return null;
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
		switch (world.getBlockMetadata(i, j, k)) {
		case 1:
			if (world.getBlock(i + 1, j, k) != this || world.getBlockMetadata(i + 1, j, k) != 2 || world.getBlock(i, j, k + 1) != this || world.getBlockMetadata(i, j, k + 1) != 3 || world.getBlock(i + 1, j, k + 1) != this || world.getBlockMetadata(i + 1, j, k + 1) != 4) {
				world.setBlock(i, j, k, this, 0, 3);
				world.addBlockEvent(i, j, k, this, 2, 6);
				break;
			}
			break;
		case 2:
			if (world.getBlock(i - 1, j, k) != this || world.getBlockMetadata(i - 1, j, k) != 1 || world.getBlock(i - 1, j, k + 1) != this || world.getBlockMetadata(i - 1, j, k + 1) != 3 || world.getBlock(i, j, k + 1) != this || world.getBlockMetadata(i, j, k + 1) != 4) {
				world.setBlock(i, j, k, this, 0, 3);
				world.addBlockEvent(i, j, k, this, 2, 6);
				break;
			}
			break;
		case 3:
			if (world.getBlock(i, j, k - 1) != this || world.getBlockMetadata(i, j, k - 1) != 1 || world.getBlock(i + 1, j, k - 1) != this || world.getBlockMetadata(i + 1, j, k - 1) != 2 || world.getBlock(i + 1, j, k) != this || world.getBlockMetadata(i + 1, j, k) != 4) {
				world.setBlock(i, j, k, this, 0, 3);
				world.addBlockEvent(i, j, k, this, 2, 6);
				break;
			}
			break;
		case 4:
			if (world.getBlock(i - 1, j, k - 1) != this || world.getBlockMetadata(i - 1, j, k - 1) != 1 || world.getBlock(i, j, k - 1) != this || world.getBlockMetadata(i, j, k - 1) != 2 || world.getBlock(i - 1, j, k) != this || world.getBlockMetadata(i - 1, j, k) != 3) {
				world.setBlock(i, j, k, this, 0, 3);
				world.addBlockEvent(i, j, k, this, 2, 6);
				break;
			}
			break;
		}
		super.onNeighborBlockChange(world, i, j, k, block);
	}

	@Override
	public boolean onBlockEventReceived(World world, int i, int j, int k, int meta, int eventID) {
		if (meta == 1) {
			if (world.isRemote)
				Thaumcraft.proxy.blockSparkle(world, i, j, k, eventID, 5);
			return true;
		}
		if (meta == 2) {
			for (int l = 0; l < 8; l++)
				world.spawnParticle("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0, 0.0, 0.0);
			world.playSoundEffect((double) i, (double) j, (double) k, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			return true;
		}
		return super.onBlockEventReceived(world, i, j, k, meta, eventID);
	}
}