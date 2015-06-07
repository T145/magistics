package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.common.tiles.TileCrystalMachine;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalMachine extends BlockCrystalStorage {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon_fabricator[] = new IIcon[3], icon_fertilizer[] = new IIcon[3], icon_fire_basin[] = new IIcon[4], icon_freezer, icon_liquid_detector, icon_liquid_void, icon_spring[] = new IIcon[2];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		super.registerBlockIcons(r);
		icon_fabricator[0] = r.registerIcon("magistics:decor/machines/topbottom");
		icon_fabricator[1] = r.registerIcon("magistics:decor/machines/fabricator_top");
		icon_fabricator[2] = r.registerIcon("magistics:decor/machines/fabricator_side");
		icon_fertilizer[0] = r.registerIcon("magistics:decor/machines/topbottom");
		icon_fertilizer[1] = r.registerIcon("magistics:decor/machines/fertilizer_top");
		icon_fertilizer[2] = r.registerIcon("magistics:decor/machines/fertilizer_side");
		icon_freezer = r.registerIcon("magistics:decor/machines/freezer");
		icon_liquid_detector = r.registerIcon("magistics:decor/machines/liquid_detector");
		icon_liquid_void = r.registerIcon("magistics:decor/machines/liquid_void");
		icon_fire_basin[0] = r.registerIcon("magistics:decor/machines/topbottom");
		icon_fire_basin[1] = r.registerIcon("magistics:decor/machines/fire_basin_top_off");
		icon_fire_basin[2] = r.registerIcon("magistics:decor/machines/fire_basin_top_on");
		icon_fire_basin[3] = r.registerIcon("magistics:decor/machines/fire_basin_side");
		icon_spring[0] = r.registerIcon("magistics:decor/machines/topbottom");
		icon_spring[1] = r.registerIcon("magistics:decor/machines/spring_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (meta) {

		}
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		return blockIcon;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile instanceof TileCrystalMachine) {
			TileCrystalMachine machine = (TileCrystalMachine) tile;

			if (machine.canRotate()) {
				int facing = -1;

				// for the vertical axis
				if (MathHelper.abs((float) ent.posX - (float) x) < 2F && MathHelper.abs((float) ent.posZ - (float) z) < 2F) {
					double d0 = ent.posY + 1.82D - (double) ent.yOffset;
					if (d0 - (double) y > 2D)
						facing = ForgeDirection.UP.ordinal();
					if ((double) y - d0 > 0D)
						facing = ForgeDirection.DOWN.ordinal();
				}

				if (facing < 0)
					// for the horizontal axis
					switch (MathHelper.floor_double(ent.rotationYaw * 4F / 360F + 0.5) & 0x3) {
					case 0:
						machine.setFacing(2);
						break;
					case 1:
						machine.setFacing(5);
						break;
					case 2:
						machine.setFacing(3);
						break;
					case 3:
						machine.setFacing(4);
						break;
					}
				else
					machine.setFacing(facing);
			}
		}
	}
}