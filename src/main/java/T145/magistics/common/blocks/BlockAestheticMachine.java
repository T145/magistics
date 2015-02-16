package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.tiles.TileFabricator;
import T145.magistics.common.tiles.TileFertilizer;
import T145.magistics.common.tiles.TileFireBasin;
import T145.magistics.common.tiles.TileFluidVoid;
import T145.magistics.common.tiles.TileFreezer;
import T145.magistics.common.tiles.TileSpring;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAestheticMachine extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon iconGlow, fabricator[] = new IIcon[2], fertilizer[] = new IIcon[2], fire_basin[] = new IIcon[3], freezer, liquid_detector, liquid_void, spring[] = new IIcon[1];

	public BlockAestheticMachine() {
		super(Material.iron);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
		fabricator[0] = r.registerIcon("magistics:decor/machines/fabricator_top");
		fabricator[1] = r.registerIcon("magistics:decor/machines/topbottom");
		fabricator[2] = r.registerIcon("magistics:decor/machines/fabricator_side");
		fertilizer[0] = r.registerIcon("magistics:decor/machines/fertilizer_top");
		fertilizer[1] = r.registerIcon("magistics:decor/machines/topbottom");
		fertilizer[2] = r.registerIcon("magistics:decor/machines/fertilizer_side");
		fire_basin[0] = r.registerIcon("magistics:decor/machines/fire_basin_top_off");
		fire_basin[1] = r.registerIcon("magistics:decor/machines/fire_basin_top_on");
		fire_basin[2] = r.registerIcon("magistics:decor/machines/fire_basin_side");
		fire_basin[3] = r.registerIcon("magistics:decor/machines/topbottom");
		freezer = r.registerIcon("magistics:decor/machines/freezer");
		liquid_detector = r.registerIcon("magistics:decor/machines/liquid_detector");
		liquid_void = r.registerIcon("magistics:decor/machines/liquid_void");
		spring[0] = r.registerIcon("magistics:decor/machines/topbottom");
		spring[1] = r.registerIcon("magistics:decor/machines/spring_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);

		switch (meta) {
		case 0:
			if (side < 2)
				return fabricator[side];
			else
				return fabricator[2];
		case 1:
			if (side < 2)
				return fertilizer[side];
			else
				return fertilizer[2];
		case 2:
			if (side < 2)
				if (side == 0) {
					if (tile instanceof TileFireBasin) {
						TileFireBasin basin = (TileFireBasin) tile;

						if (basin.active)
							return fire_basin[1];
						else
							return fire_basin[0];
					}
				} else
					return fire_basin[2];
			else
				return fire_basin[3];
		case 3:
			return freezer;
		case 4:
			return liquid_detector;
		case 5:
			return liquid_void;
		case 6:
			if (side < 2)
				return spring[0];
			else
				return spring[1];
		default:
			return getIcon(side, meta);
		}
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs t, List l) {
		for (int j = 0; j < 16; j++)
			l.add(new ItemStack(i, 1, j));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TileFabricator();
		case 1:
			return new TileFertilizer();
		case 2:
			return new TileFireBasin();
		case 3:
			return new TileFreezer();
		case 5:
			return new TileFluidVoid();
		case 6:
			return new TileSpring();
		default:
			return null;
		}
	}
}