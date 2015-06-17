package T145.magistics.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import T145.magistics.common.tiles.TileEverfullUrn;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEverfullUrn extends BlockContainer {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static IIcon icon[] = new IIcon[3];

	public BlockEverfullUrn() {
		super(Material.rock);
		setBlockName("everfull_urn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		icon[0] = r.registerIcon("magistics:urn_everfull_bottom");
		icon[1] = r.registerIcon("magistics:urn_everfull_top");
		icon[2] = r.registerIcon("magistics:urn_everfull_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side > 1 ? icon[2] : icon[side];
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
	public int getRenderType() {
		return renderID;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsForItemRender();
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBounds(0.125F, 0F, 0.125F, 0.875F, 0.5625F, 0.875F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB.getBoundingBox(0.3125D, 0.5625D, 0.3125D, 0.6875D, 1D, 0.6875D);
		AxisAlignedBB.getBoundingBox(0.125D, 0D, 0.125D, 0.875D, 0.5625D, 0.875D);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEverfullUrn();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack currentItem = player.inventory.getCurrentItem();
		Item itemInHand = currentItem.getItem();

		if (currentItem != null) {
			if (itemInHand == Items.bucket) {
				player.inventory.decrStackSize(player.inventory.currentItem, 1);

				if (!player.inventory.addItemStackToInventory(new ItemStack(Items.water_bucket, 1)))
					player.dropItem(Items.water_bucket, 1);

				world.playSoundAtEntity(player, "liquid.swim", 0.5F, 1.0F);
			} else if (FluidContainerRegistry.isEmptyContainer(currentItem)) {
				ItemStack newStack = currentItem;

				player.inventory.decrStackSize(player.inventory.currentItem, 1);

				if (!player.inventory.addItemStackToInventory(FluidContainerRegistry.fillFluidContainer(new FluidStack(FluidRegistry.WATER, 1000), newStack)))
					player.dropItem(FluidContainerRegistry.fillFluidContainer(new FluidStack(FluidRegistry.WATER, 1000), newStack).getItem(), 1);

				world.playSoundAtEntity(player, "liquid.swim", 0.5F, 1.0F);
			} else if (itemInHand instanceof IFluidContainerItem) {
				ItemStack newStack = new ItemStack(itemInHand, 1);

				player.inventory.decrStackSize(player.inventory.currentItem, 1);
				((IFluidContainerItem) newStack.getItem()).fill(newStack, new FluidStack(FluidRegistry.WATER, 1000), true);

				if (!player.inventory.addItemStackToInventory(newStack))
					player.dropItem(newStack.getItem(), newStack.stackSize);

				world.playSoundAtEntity(player, "liquid.swim", 0.5F, 1.0F);
			}
		}

		return true;
	}
}