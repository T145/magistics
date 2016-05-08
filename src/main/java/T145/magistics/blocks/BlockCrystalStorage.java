package T145.magistics.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.crafting.IInfusionStabiliser;
import T145.magistics.Magistics;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalStorage extends Block implements IInfusionStabiliser {
	private String path;

	private IIcon icon;
	private IIcon iconGlow;

	private int renderID;

	private boolean fullBlock = false;

	public BlockCrystalStorage(String blockName, String iconPath) {
		super(Material.glass);

		path = iconPath;
		renderID = RenderingRegistry.getNextAvailableRenderId();

		setBlockName(blockName);
		setCreativeTab(Magistics.tabMagistics);

		setHardness(3F);
		setResistance(6F);
		setLightLevel(0.5F);
		setStepSound(soundTypeMetal);
	}

	public BlockCrystalStorage(String blockName, String iconPath, boolean isFullBlock) {
		this(blockName, iconPath);
		fullBlock = isFullBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon = r.registerIcon(path);
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon() {
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return getIcon();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconGlow() {
		return iconGlow;
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
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		int maxIndex = 6;

		if (fullBlock) {
			maxIndex = 16;
		}

		for (int i = 0; i < maxIndex; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public boolean canStabaliseInfusion(World arg0, int arg1, int arg2, int arg3) {
		return true;
	}
}