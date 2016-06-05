package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
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

public class BlockChestHungryEnder extends BlockEnderChest {
	public static final Block INSTANCE = new BlockChestHungryEnder();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockChestHungryEnder() {
		super();
		setBlockName("hungry_ender_chest");
		setCreativeTab(Magistics.tabMagistics);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileChestHungryEnder();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("magistics:chest_hungry/ender");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase user, ItemStack stack) {
		if (user != null && user instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) user;
			TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);

			chest.setOwner(player.getCommandSenderName());
		}

		super.onBlockPlacedBy(world, x, y, z, user, stack);
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
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int power) {
		TileChestHungryEnder chest = (TileChestHungryEnder) world.getTileEntity(x, y, z);
		IInventory inventory = chest.getEnderInventory();

		if (inventory != null) {
			return Container.calcRedstoneFromInventory(inventory);
		} else {
			return 0;
		}
	}
}