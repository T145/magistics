package T145.magistics.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import T145.magistics.Magistics;
import T145.magistics.lib.InventoryHelper;
import T145.magistics.tiles.TileInfusionWorkbench;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfusionWorkbench extends BlockContainer {
	public static final Block INSTANCE = new BlockInfusionWorkbench();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public IIcon[] icon = new IIcon[7];
	public IIcon iconGlow;

	public BlockInfusionWorkbench() {
		super(Material.rock);
		setBlockName("infusion_workbench");
		setCreativeTab(Magistics.tabMagistics);

		setHardness(4F);
		setResistance(100F);
		setStepSound(Block.soundTypeStone);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:infusion_workbench/base/1");

		for (int i = 1; i < 7; ++i) {
			icon[i] = r.registerIcon("magistics:infusion_workbench/side" + i);
		}

		iconGlow = r.registerIcon("thaumcraft:animatedglow");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		switch (metadata) {
		case 1:
			switch (side) {
			case 0: case 1:
				return icon[1];
			case 2: case 5:
				return icon[6];
			case 3: case 4:
				return icon[5];
			default:
				return icon[0];
			}
		case 2:
			switch (side) {
			case 0: case 1:
				return icon[2];
			case 2: case 3:
				return icon[5];
			case 4: case 5:
				return icon[6];
			default:
				return icon[0];
			}
		case 3:
			switch (side) {
			case 0: case 1:
				return icon[3];
			case 2: case 3:
				return icon[5];
			case 4: case 5:
				return icon[6];
			default:
				return icon[0];
			}
		case 4:
			switch (side) {
			case 0: case 1:
				return icon[4];
			case 2: case 5:
				return icon[5];
			case 3: case 4:
				return icon[6];
			default:
				return icon[0];
			}
		}
		return icon[0];
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if (metadata == 1) {
			return new TileInfusionWorkbench();
		}
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof IInventory) {
			InventoryHelper.emptyInventory(world, x, y, z);
		}
	}

	@Override
	public int damageDropped(int metadata) {
		return 0;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		TileEntity tile = world.getTileEntity(x, y, z);

		switch (world.getBlockMetadata(x, y, z)) {
		case 1:
			if (world.getBlock(x + 1, y, z) != this
			|| world.getBlockMetadata(x + 1, y, z) != 2
			|| world.getBlock(x, y, z + 1) != this
			|| world.getBlockMetadata(x, y, z + 1) != 3
			|| world.getBlock(x + 1, y, z + 1) != this
			|| world.getBlockMetadata(x + 1, y, z + 1) != 4) {
				InventoryHelper.emptyInventory(world, x, y, z);
				world.setBlock(x, y, z, this, 0, 3);
				world.addBlockEvent(x, y, z, this, 2, 6);
				break;
			}
			break;
		case 2:
			if (world.getBlock(x - 1, y, z) != this
			|| world.getBlockMetadata(x - 1, y, z) != 1
			|| world.getBlock(x - 1, y, z + 1) != this
			|| world.getBlockMetadata(x - 1, y, z + 1) != 3
			|| world.getBlock(x, y, z + 1) != this
			|| world.getBlockMetadata(x, y, z + 1) != 4) {
				world.setBlock(x, y, z, this, 0, 3);
				world.addBlockEvent(x, y, z, this, 2, 6);
				break;
			}
			break;
		case 3:
			if (world.getBlock(x, y, z - 1) != this
			|| world.getBlockMetadata(x, y, z - 1) != 1
			|| world.getBlock(x + 1, y, z - 1) != this
			|| world.getBlockMetadata(x + 1, y, z - 1) != 2
			|| world.getBlock(x + 1, y, z) != this
			|| world.getBlockMetadata(x + 1, y, z) != 4) {
				world.setBlock(x, y, z, this, 0, 3);
				world.addBlockEvent(x, y, z, this, 2, 6);
				break;
			}
			break;
		case 4:
			if (world.getBlock(x - 1, y, z - 1) != this
			|| world.getBlockMetadata(x - 1, y, z - 1) != 1
			|| world.getBlock(x, y, z - 1) != this
			|| world.getBlockMetadata(x, y, z - 1) != 2
			|| world.getBlock(x - 1, y, z) != this
			|| world.getBlockMetadata(x - 1, y, z) != 3) {
				world.setBlock(x, y, z, this, 0, 3);
				world.addBlockEvent(x, y, z, this, 2, 6);
				break;
			}
			break;
		}

		super.onNeighborBlockChange(world, x, y, z, neighbor);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity tile = world.getTileEntity(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);

		if (metadata == 0 || player.isSneaking()) {
			return false;
		}

		if (world.isRemote) {
			return true;
		}

		if (tile != null && tile instanceof TileInfusionWorkbench) {
			player.openGui(Magistics.instance, 14, world, x, y, z);
			return true;
		}

		switch (metadata) {
		case 2:
			tile = world.getTileEntity(x - 1, y, z);

			if (tile != null && tile instanceof TileInfusionWorkbench) {
				player.openGui(Magistics.instance, 14, world, x - 1, y, z);
				return true;
			}

			return false;
		case 3:
			tile = world.getTileEntity(x, y, z - 1);

			if (tile != null && tile instanceof TileInfusionWorkbench) {
				player.openGui(Magistics.instance, 14, world, x, y, z - 1);
				return true;
			}

			return false;
		case 4:
			tile = world.getTileEntity(x - 1, y, z - 1);

			if (tile != null && tile instanceof TileInfusionWorkbench) {
				player.openGui(Magistics.instance, 14, world, x - 1, y, z - 1);
				return true;
			}

			return false;
		default:
			return false;
		}
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		return world.getBlockMetadata(x, y, z) == 0;
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int event, int data) {
		if (event == 1) {
			if (world.isRemote) {
				Thaumcraft.proxy.blockSparkle(world, x, y, z, data, 5);
			}

			return true;
		}

		if (event == 2) {
			for (int i = 0; i < 8; ++i) {
				world.spawnParticle("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0, 0, 0);
			}

			world.playSoundEffect(x, y, z, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			return true;
		}

		return super.onBlockEventReceived(world, x, y, z, event, data);
	}
}