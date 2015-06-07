package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import T145.magistics.common.tiles.TileThaumicEnchanter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockThaumicEnchanter extends BlockContainer {
	public static IIcon icon[] = new IIcon[3];

	public BlockThaumicEnchanter() {
		super(Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		setBlockName("thaumic_enchanter");
		setLightOpacity(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:enchanter_bottom");
		icon[1] = r.registerIcon("magistics:enchanter_thaumic_top");
		icon[2] = r.registerIcon("magistics:enchanter_thaumic_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side > 1) {
			return icon[2];
		} else {
			return icon[side];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		Blocks.enchanting_table.randomDisplayTick(world, x, y, z, rand);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileThaumicEnchanter();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			return true;
		}
	}
}