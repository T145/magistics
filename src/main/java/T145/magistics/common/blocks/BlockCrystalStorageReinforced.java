package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalStorageReinforced extends Block {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon iconGlow, icon[] = new IIcon[2];

	public BlockCrystalStorageReinforced() {
		super(Material.glass);
	}

	public BlockCrystalStorageReinforced(Material material) {
		super(material);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
		icon[0] = r.registerIcon("magistics:decor/plate");
		icon[1] = r.registerIcon("magistics:decor/shield");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta > 5 ? icon[1] : icon[0];
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item i, CreativeTabs t, List l) {
		for (int j = 0; j < 12; j++)
			l.add(new ItemStack(i, 1, j));
	}
}