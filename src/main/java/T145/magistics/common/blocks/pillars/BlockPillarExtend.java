package T145.magistics.common.blocks.pillars;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import T145.magistics.common.tiles.pillars.TilePillarExtend;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockPillarExtend extends BlockPillarBase {
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPillarExtend(Material mat) {
		super(mat);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		switch (side) {
		case 0: case 1:
			meta = 0;
			break;
		case 2: case 3:
			meta = 1;
			break;
		case 4: case 5:
			meta = 2;
			break;
		}

		return meta;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TilePillarExtend();
	}
}