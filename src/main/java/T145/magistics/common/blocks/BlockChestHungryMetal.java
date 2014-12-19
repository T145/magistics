package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;

public class BlockChestHungryMetal extends BlockIronChest {
	public static enum Types {
		iron, gold, diamond, copper, silver, crystal, obsidian, dirt;
	}

	public static IIcon icon[] = new IIcon[Types.values().length];

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		for (Types type : Types.values())
			icon[type.ordinal()] = r.registerIcon("magistics:chest_hungry/" + type.name());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileChestHungryMetal tile = (TileChestHungryMetal) world.getTileEntity(i, j, k);
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (IInventory) tile, this, 2, 2, world, i, j, k, true);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileChestHungryMetal(IronChestType.values()[meta]);
	}
}