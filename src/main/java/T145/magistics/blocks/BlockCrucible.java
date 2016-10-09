package T145.magistics.blocks;

import java.util.List;

import T145.magistics.Magistics;
import T145.magistics.tiles.TileCrucible;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrucible extends BlockMagistics implements ITileEntityProvider {

	public BlockCrucible() {
		super(Material.ROCK, "crucible");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		Magistics.proxy.registerBlockRenderer(this, 0, getName());
		Magistics.proxy.registerBlockRenderer(this, 1, getName());
		Magistics.proxy.registerBlockRenderer(this, 2, getName());
		Magistics.proxy.registerBlockRenderer(this, 3, getName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 3));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileCrucible();
	}
}