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

public class BlockAesthetic extends Block {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[12], iconGlow;

	public BlockAesthetic() {
		super(Material.ground);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		for (int i = 0; i < 12; i++)
			if (i <= 6)
				icon[i] = r.registerIcon("magistics:decor/storage_basic");
			else
				icon[i] = r.registerIcon("magistics:decor/storage_brick");
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icon[meta];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs t, List l) {
		for (int i = 0; i < icon.length; i++)
			l.add(new ItemStack(item, 1, i));
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return renderID;
	}
}