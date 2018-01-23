package T145.magistics.common.blocks;

import T145.magistics.api.front.IModelProvider;
import T145.magistics.common.blocks.base.BlockBase;
import T145.magistics.common.tiles.TileResearchTable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockResearchTable extends BlockBase implements IModelProvider, ITileEntityProvider {

	public BlockResearchTable() {
		super("research_table", Material.WOOD, MapColor.WOOD, false);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileResearchTable();
	}

	@Override
	public void initModel() {
		IModelProvider.registerBlockModel(this, 0, "inventory");
	}
}