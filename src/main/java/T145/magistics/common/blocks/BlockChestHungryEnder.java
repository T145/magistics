package T145.magistics.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import vazkii.botania.api.wand.IWandable;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnder extends BlockContainer implements IWandable {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockChestHungryEnder() {
		super(Material.rock);
		setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryEnder();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		blockIcon = r.registerIcon("magistics:chest_hungry/ender");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
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
	public int getRenderType() {
		return renderID;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.obsidian);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 8;
	}

	@Override
	public boolean canSilkHarvest() {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase user, ItemStack is) {
		if (user != null && user instanceof EntityPlayer) {
			EntityPlayer owner = (EntityPlayer) user;
			TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
			tile.setOwner(owner.getCommandSenderName());
		}
		Blocks.ender_chest.onBlockPlacedBy(world, i, j, k, user, is);
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !world.getBlock(i, j + 1, k).isNormalCube()) {
			TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
			if (tile != null && tile.isOwnedBy(player))
				player.displayGUIChest(tile);
		}
		return true;
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(x, y, z);
		return ForgeHooks.blockStrength(tile.isOwnedBy(player) ? this : Blocks.bedrock, player, world, 0, 0, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, tile.getEnderInventory(), this, 1, 2, world, i, j, k, true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random rand) {
		Blocks.ender_chest.randomDisplayTick(world, i, j, k, rand);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int i, int j, int k, int rs) {
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		if (tile != null && tile.getEnderInventory() != null)
			return Container.calcRedstoneFromInventory(tile.getEnderInventory());
		else
			return 0;
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int i, int j, int k, int side) {
		TileChestHungryEnder tile = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		return tile.onWanded(player, side);
	}
}