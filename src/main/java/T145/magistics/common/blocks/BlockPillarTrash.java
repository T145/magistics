package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import T145.magistics.common.tiles.pillars.TilePillarTrash;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPillarTrash extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarTrash(Material mat) {
		super(mat);
		setBlockBounds(0, 0, 0, 1F, 15F / 16F, 1F);
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		world.setBlockMetadataWithNotify(x, y, z, determineOrientation(world, x, y, z, entity), 0);
	}

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity) {
		return MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;

		TilePillarTrash pillarTile = (TilePillarTrash) world.getTileEntity(x, y, z);

		if (!player.isSneaking() && player.getCurrentEquippedItem() == null) {
			pillarTile.isOpen = !pillarTile.isOpen;
			pillarTile.onInventoryChanged();
			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.click", 0.3F, world.rand.nextFloat() * 0.1F + 0.5F);

			return false;
		}

		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y - 1, z)) {
			TilePillarTrash pillarTile = (TilePillarTrash) world.getTileEntity(x, y, z);

			pillarTile.isOpen = !pillarTile.isOpen;
			pillarTile.onInventoryChanged();

			world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.click", 0.3F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityItem) {
			TilePillarTrash pillarTile = (TilePillarTrash) world.getTileEntity(x, y, z);
			if (pillarTile.isOpen) {
				if (world.isRemote) {
					world.spawnParticle("largesmoke", entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D);
					world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "random.fizz", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
				} else
					entity.setDead();
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		TilePillarTrash pillarTile = (TilePillarTrash) world.getTileEntity(x, y, z);

		if (pillarTile.isOpen) {
			double d0 = (double) ((float) x + rand.nextFloat()), d1 = (double) ((float) y + 0.8F), d2 = (double) ((float) z + rand.nextFloat());
			world.spawnParticle("smoke", d0, d1, d2, 0D, 0D, 0D);
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TilePillarTrash();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("craftingpillars:craftingPillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return blockIcon;
	}
}