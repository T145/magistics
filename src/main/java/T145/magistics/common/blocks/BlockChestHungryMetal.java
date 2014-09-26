package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.common.lib.MagisticsUtils;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;

public class BlockChestHungryMetal extends BlockIronChest {
	public static enum Types {
		iron, gold, diamond, copper, silver, crystal, obsidian, dirt;
	}

	@SideOnly(Side.CLIENT)
	public static IIcon icon[] = new IIcon[Types.values().length];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		for (int i = 0; i <= Types.values().length - 1; i++)
			icon[i] = r.registerIcon("magistics:chest_hungry/" + Types.values()[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderInPass(int pass) {
		return pass == 0 || pass == 1;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileChestHungryMetal tile = (TileChestHungryMetal) world.getTileEntity(i, j, k);
		MagisticsUtils.absorbCollidingItemStackIntoInventory(entity, tile, (IInventory) tile, this, 2, 2, world, i, j, k, true);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileChestHungryMetal(IronChestType.values()[meta]);
	}
}