package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.Magistics;
import T145.magistics.tiles.TileChthonianFurnace;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChthonianFurnace extends BlockContainer {
	public static final Block INSTANCE = new BlockChthonianFurnace();
	private int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockChthonianFurnace() {
		super(Material.rock);
		setBlockName("chthonian_furnace");
		setCreativeTab(Magistics.tabMagistics);
		setBlockTextureName("obsidian");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileChthonianFurnace();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return renderID;
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int facing = BlockPistonBase.determineOrientation(world, x, y, z, player);
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile != null && tile instanceof TileChthonianFurnace) {
			TileChthonianFurnace furnace = (TileChthonianFurnace) tile;
			furnace.setFacing(facing);
		}
	}
}