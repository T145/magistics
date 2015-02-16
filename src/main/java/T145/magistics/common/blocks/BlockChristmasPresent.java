package T145.magistics.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.config.ConfigObjects;
import T145.magistics.common.tiles.pillars.TileChristmasPresent;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockChristmasPresent extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockChristmasPresent(Material mat) {
		super(mat);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockMetadata(x, y, z) == 1)
			this.setBlockBounds(0F, 0F, 0F, 1F, 7 / 16F, 1F);
		else
			this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return world.rand.nextInt(2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float i, float j, float k) {
		if (!world.isRemote && player.getCurrentEquippedItem() == null) {
			EntityItem item = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D);
			item.setEntityItemStack(ConfigObjects.present_loot[world.rand.nextInt(ConfigObjects.present_loot.length)].copy());
			if (item.getEntityItem().isItemEqual(new ItemStack(ConfigObjects.blockChristmasPresent)))
				player.addStat(ConfigObjects.achievementRecursion3, 1);
			world.spawnEntityInWorld(item);
			world.setBlockToAir(x, y, z);
		}

		return super.onBlockActivated(world, x, y, z, player, meta, i, j, k);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileChristmasPresent();
	}
}