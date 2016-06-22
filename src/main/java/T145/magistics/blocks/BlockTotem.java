package T145.magistics.blocks;

import java.util.List;

import net.minecraft.block.Block;
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
import T145.magistics.Magistics;
import T145.magistics.tiles.TileTotemRune;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTotem extends BlockContainer {
	public static final Block INSTANCE = new BlockTotem();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public IIcon[] dawn = new IIcon[7];
	public IIcon[] dusk = new IIcon[7];

	public BlockTotem() {
		super(Material.rock);
		setBlockName("totem");
		setCreativeTab(Magistics.tabMagistics);

		setResistance(10F);
		setHardness(2F);
		setStepSound(soundTypeStone);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		dawn[0] = r.registerIcon("magistics:totem/dawntile");
		dawn[1] = r.registerIcon("magistics:totem/dawntotembase");
		dawn[2] = r.registerIcon("magistics:totem/dawntotem1");
		dawn[3] = r.registerIcon("magistics:totem/dawntotem2");
		dawn[4] = r.registerIcon("magistics:totem/dawntotem3");
		dawn[5] = r.registerIcon("magistics:totem/dawntotem4");
		dawn[6] = r.registerIcon("magistics:totem/dawnrune");

		dusk[0] = r.registerIcon("magistics:totem/dusktile");
		dusk[1] = r.registerIcon("magistics:totem/dusktotembase");
		dusk[2] = r.registerIcon("magistics:totem/dusktotem1");
		dusk[3] = r.registerIcon("magistics:totem/dusktotem2");
		dusk[4] = r.registerIcon("magistics:totem/dusktotem3");
		dusk[5] = r.registerIcon("magistics:totem/dusktotem4");
		dusk[6] = r.registerIcon("magistics:totem/duskrune");
	}

	/*
	 * Metadata spread:
	 * 0 - dawn totem
	 * 1 - dawn tile
	 * 2 - charged dawn totem
	 * 3 - dawn rune
	 * 
	 * 4 - dusk totem
	 * 5 - dusk tile
	 * 6 - charged dusk totem
	 * 7 - dusk rune
	 */

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (metadata <= 2) {
			return dawn[0];
		} else if (metadata == 3) {
			return dawn[6];
		}

		if (metadata > 3 && metadata != 7) {
			return dusk[0];
		} else if (metadata == 7) {
			return dusk[6];
		}

		return super.getIcon(side, metadata);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int randFace = (2 + Math.abs((side + x % 4 + z % 4 + y % 4) % 4));
		int md = world.getBlockMetadata(x, y, z);

		if ((md == 0 || md == 2) && side > 1 && side < 100) {
			if (world.getBlock(x, y + 1, z) == this && (world.getBlockMetadata(x, y + 1, z) == 0 || world.getBlockMetadata(x, y + 1, z) == 2)) {
				return dawn[0];
			}

			if (world.getBlock(x, y - 1, z) != this || (world.getBlockMetadata(x, y - 1, z) != 0 && world.getBlockMetadata(x, y - 1, z) != 2)) {
				return dawn[0];
			}

			return dawn[randFace];
		}

		if ((md == 4 || md == 6) && side > 1 && side < 100) {
			if (world.getBlock(x, y + 1, z) == this && (world.getBlockMetadata(x, y + 1, z) == 4 || world.getBlockMetadata(x, y + 1, z) == 6)) {
				return dusk[0];
			}

			if (world.getBlock(x, y - 1, z) != this || (world.getBlockMetadata(x, y - 1, z) != 4 && world.getBlockMetadata(x, y - 1, z) != 6)) {
				return dusk[0];
			}

			return dusk[randFace];
		}

		return super.getIcon(world, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 8; ++i) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if (metadata == 3 || metadata == 7) {
			return new TileTotemRune();
		}
		return null;
	}
}