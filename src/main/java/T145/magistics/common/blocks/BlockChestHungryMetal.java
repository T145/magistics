package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.client.renderers.TileChestHungryMetalRenderer;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryMetal;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;

public class BlockChestHungryMetal extends BlockIronChest {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[IronChestType.values().length];

	public BlockChestHungryMetal() {
		setBlockName("hungry_metal_chest");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		for (IronChestType type : IronChestType.values())
			if (type != type.WOOD)
				icon[type.ordinal()] = r.registerIcon("magistics:chest_hungry/" + TileChestHungryMetalRenderer.getSimpleChestName(type));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileChestHungryMetal(IronChestType.values()[meta]);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileChestHungryMetal) world.getTileEntity(x, y, z), this, 2, 2, world, x, y, z, true);
	}
}