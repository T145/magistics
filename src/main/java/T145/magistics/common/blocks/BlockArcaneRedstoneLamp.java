package T145.magistics.common.blocks;

import static thaumcraft.client.renderers.block.BlockRenderer.W12;
import static thaumcraft.client.renderers.block.BlockRenderer.W14;
import static thaumcraft.client.renderers.block.BlockRenderer.W2;
import static thaumcraft.client.renderers.block.BlockRenderer.W4;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import T145.magistics.common.tiles.TileArcaneRedstoneLamp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArcaneRedstoneLamp extends BlockContainer {
	public static IIcon icon[] = new IIcon[4];

	public BlockArcaneRedstoneLamp() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileArcaneRedstoneLamp();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:lamp_redstone_side_off");
		icon[1] = r.registerIcon("magistics:lamp_redstone_top_off");
		icon[2] = r.registerIcon("magistics:lamp_redstone_side_on");
		icon[3] = r.registerIcon("magistics:lamp_redstone_top_on");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int i, int j, int k, int side) {
		TileArcaneRedstoneLamp lamp = (TileArcaneRedstoneLamp) world.getTileEntity(i, j, k);

		if (lamp.active) {
			if (side <= 1)
				return icon[1];
			return icon[0];
		} else {
			if (side <= 1)
				return icon[3];
			return icon[2];
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
		setBlockBounds(W4, W2, W4, W12, W14, W12);
		super.setBlockBoundsBasedOnState(world, i, j, k);
	}

	public void checkForActivity(World world, int i, int j, int k) {
		TileArcaneRedstoneLamp lamp = (TileArcaneRedstoneLamp) world.getTileEntity(i, j, k);

		if (lamp != null && !world.isRemote) {
			if (world.isAirBlock(i + lamp.facing.offsetX, j + lamp.facing.offsetY, k + lamp.facing.offsetZ)) {
				dropBlockAsItem(world, i, j, k, 7, 0);
				world.setBlockToAir(i, j, k);
			}

			if (lamp.active && !world.isBlockIndirectlyGettingPowered(i, j, k))
				lamp.active = false;
			else if (!lamp.active && world.isBlockIndirectlyGettingPowered(i, j, k))
				lamp.active = true;
		}
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		checkForActivity(world, i, j, k);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block b) {
		checkForActivity(world, i, j, k);
	}

	@Override
	public int getLightValue(IBlockAccess world, int i, int j, int k) {
		return 15;
	}
}