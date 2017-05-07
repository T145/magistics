package T145.magistics.blocks.crafting;

import T145.magistics.api.logic.IHorizontalFacing;
import T145.magistics.api.variants.blocks.EnumForge;
import T145.magistics.blocks.MBlockDevice;
import T145.magistics.tiles.crafting.TileForge;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockForge extends MBlockDevice<EnumForge> implements IHorizontalFacing {

	public BlockForge() {
		super("forge", Material.ROCK, EnumForge.class);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileForge();
	}
}