package T145.magistics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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
import T145.magistics.Magistics;
import T145.magistics.lib.InventoryHelper;
import T145.magistics.tiles.TileChestHungryEnder;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestHungryEnder extends BlockContainer {
	public static final Block INSTANCE = new BlockChestHungryEnder();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockChestHungryEnder() {
		super(Material.rock);
		setBlockName("hungry_ender_chest");
		setBlockBounds((float) Blocks.ender_chest.getBlockBoundsMinX(), (float) Blocks.ender_chest.getBlockBoundsMinY(), (float) Blocks.ender_chest.getBlockBoundsMinZ(), (float) Blocks.ender_chest.getBlockBoundsMaxX(), (float) Blocks.ender_chest.getBlockBoundsMaxY(), (float) Blocks.ender_chest.getBlockBoundsMaxZ());
		setBlockTextureName("magistics:chest_hungry/ender");
		setCreativeTab(Magistics.tabMagistics);
		setHardness(22.5F);
		setLightLevel(0.5F);
		setResistance(1000F);
		setStepSound(soundTypePiston);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileChestHungryEnder();
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
	@SideOnly(Side.CLIENT)
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
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof TileChestHungryEnder) {
			TileChestHungryEnder chest = (TileChestHungryEnder) tile;

			if (chest.isOwnedBy(player)) {
				return true;
			}
		}
		return false;
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

			if (chest != null && chest.isOwnedBy(player)) {
				player.displayGUIChest(chest);
			}
		}

		return true;
	}

	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z) {
		TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);
		return ForgeHooks.blockStrength(chest.isOwnedBy(player) ? this : Blocks.bedrock, player, world, 0, 0, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);
		InventoryHelper.absorbCollidingItemStackIntoInventory(entity, chest.getEnderInventory(), this, 1, 2, world, x, y, z, true);
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
		return (chest != null && chest.getEnderInventory() != null) ? Container.calcRedstoneFromInventory(chest.getEnderInventory()) : 0;
	}
}
