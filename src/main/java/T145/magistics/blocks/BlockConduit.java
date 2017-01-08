package T145.magistics.blocks;

import T145.magistics.api.enums.EnumConduit;
import T145.magistics.tiles.TileConduit;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockConduit extends MBlock<EnumConduit> implements ITileEntityProvider {

	public BlockConduit() {
		super("conduit", Material.CIRCUITS, EnumConduit.class);

		GameRegistry.registerTileEntity(TileConduit.class, TileConduit.class.getSimpleName());
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConduit();
	}
}