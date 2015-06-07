package T145.magistics.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryAlchemical;

import com.pahimar.ee3.block.BlockAlchemicalChest;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryAlchemical extends BlockAlchemicalChest {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("EE3:alchemicalChest");
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryAlchemical(meta);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, (TileChestHungryAlchemical) world.getTileEntity(i, j, k), this, 2, 2, world, i, j, k, true);
	}
}