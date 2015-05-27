package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.common.config.ModConfig;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMysticFarmland extends Block {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icons[] = new IIcon[3], iconGlow;
	private int stackHeight = ModConfig.growthStackHeight, growthHeight = ModConfig.maxGrowthHeight;

	public BlockMysticFarmland() {
		super(Material.rock);
		setBlockName("mystic_farmland");
		setHardness(0.5f);
		setLightLevel(0.5f);
		setTickRandomly(true);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.registerBlockIcons(r);
		for (int i = 0; i < icons.length; i++) {
			icons[0] = r.registerIcon("magistics:decor/machines/topbottom");
			icons[1] = r.registerIcon("magistics:decor/machines/fertilizer_top");
			icons[2] = r.registerIcon("magistics:decor/machines/fertilizer_side");
		}
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta > 1)
			meta = 0;
		if (side == 1)
			return icons[1];
		if (side == 0)
			return icons[0];
		return icons[2];
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return true;
	}

	@Override
	public boolean isFertile(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		Block block = world.getBlock(x, y + 1, z);

		if (block instanceof IPlantable) {
			int numberOfGrowthBlocks = 0;

			for (int i = y; i > y - stackHeight; i--) {
				if (world.getBlock(x, i, z).equals(ModConfig.blockMysticFarmland)) {
					numberOfGrowthBlocks++;
				}
			}

			if (rand.nextInt((stackHeight + 1) - numberOfGrowthBlocks) == 0) {
				int metadata = world.getBlockMetadata(x, y + 1, z);

				if (block == Blocks.nether_wart) {
					if (metadata < 3) {
						world.setBlockMetadataWithNotify(x, y + 1, z, metadata + 1, 2);
					}
				} else if (block == Blocks.reeds) {
					int i = 1;
					while (world.getBlock(x, y + i, z) == Blocks.reeds) {
						i++;
					}
					if (i < growthHeight) {
						if (world.isAirBlock(x, y + i, z)) {
							world.setBlock(x, y + i, z, Blocks.reeds, 2, 2);
						}
					}
				} else if (block == Blocks.cactus) {
					int i = 1;
					while (world.getBlock(x, y + i, z) == Blocks.cactus) {
						i++;
					}
					if (i < growthHeight) {
						if (world.isAirBlock(x, y + i, z)) {
							world.setBlock(x, y + i, z, Blocks.cactus, 2, 2);
						}
					}
				} else if (block instanceof BlockCrops) {
					if (metadata < 7) {
						world.setBlockMetadataWithNotify(x, y + 1, z, metadata + 1, 2);
					}
				}
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!world.isRemote) {
			updateMeta(world, x, y, z);
		}
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (!world.isRemote) {
			updateMeta(world, x, y, z);
		}
	}

	@SideOnly(Side.CLIENT)
	protected void updateMeta(World world, int x, int y, int z) {
		Block upperBlock = world.getBlock(x, y + 1, z);
		if (upperBlock instanceof BlockMysticFarmland) {
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
	}
}