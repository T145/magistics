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
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import T145.magistics.common.lib.InventoryHelper;
import T145.magistics.common.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnder extends BlockContainer {
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
	public boolean isOpaqueCube() {
		return Blocks.ender_chest.isOpaqueCube();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return Blocks.ender_chest.renderAsNormalBlock();
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Blocks.ender_chest.getItemDropped(meta, rand, fortune);
	}

	@Override
	public int quantityDropped(Random rand) {
		return Blocks.ender_chest.quantityDropped(rand);
	}

	@Override
	public boolean canSilkHarvest() {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase user, ItemStack is) {
		if (user != null && user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) user;
			TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);

			chest.setOwner(player.getCommandSenderName());
		}

		Blocks.ender_chest.onBlockPlacedBy(world, x, y, z, user, is);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !world.getBlock(x, y + 1, z).isNormalCube()) {
			TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);

			if (chest != null && chest.isOwnedBy(player))
				player.displayGUIChest(chest);
		}

		return true;
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);
		return ForgeHooks.blockStrength(chest.isOwnedBy(player) ? this : Blocks.bedrock, player, world, 0, 0, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(i, j, k);
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, chest.getEnderInventory(), this, 1, 2, world, i, j, k, true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		Blocks.ender_chest.randomDisplayTick(world, x, y, z, rand);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int rs) {
		TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);

		if (chest != null && chest.getEnderInventory() != null)
			return Container.calcRedstoneFromInventory(chest.getEnderInventory());
		else
			return 0;
	}
}