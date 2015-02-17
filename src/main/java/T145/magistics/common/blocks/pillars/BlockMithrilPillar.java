package T145.magistics.common.blocks.pillars;

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
import net.minecraft.world.World;
import T145.magistics.common.tiles.pillars.TilePillarMithril;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMithrilPillar extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockMithrilPillar(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return renderID;
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
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		int j1 = meta & 0x3;
		byte b0 = 0;
		switch (side) {
		case 0: case 1:
			b0 = 0;
			break;
		case 2: case 3:
			b0 = 8;
			break;
		case 4: case 5:
			b0 = 4;
			break;
		}
		return j1 | b0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister itemIcon) {
		blockIcon = itemIcon.registerIcon("magistics:mithril/pillar_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public int damageDropped(int meta) {
		return meta & 0x3;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(Item.getItemFromBlock((Block) this), 1, damageDropped(meta));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TilePillarMithril();
	}
}