package T145.magistics.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.blocks.BlockCustomOre;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrystalStorage extends Block {
	public static IIcon iconGlow;

	public BlockCrystalStorage() {
		super(Material.rock);
		setStepSound(soundTypeStone);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		iconGlow = r.registerIcon("thaumcraft:animatedglow");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iconGlow;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i <= new BlockCustomOre().icon.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer) {
		UtilsFX.infusedStoneSparkle(world, target.blockX, target.blockY, target.blockZ, world.getBlockMetadata(target.blockX, target.blockY, target.blockZ));
		return super.addHitEffects(world, target, effectRenderer);
	}

	@Override
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
		return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world,  int x, int y, int z) {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		super.setBlockBoundsBasedOnState(world, x, y, z);
	}

	@Override
	public void addCollisionBoxesToList(World world,  int x, int y, int z, AxisAlignedBB box, List list, Entity entity) {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}
}