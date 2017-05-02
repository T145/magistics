package T145.magistics.blocks.devices;

import T145.magistics.blocks.MBlock;
import T145.magistics.tiles.devices.TileElevator;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockElevator extends MBlock {

	public BlockElevator() {
		super("elevator", Material.ROCK);
		setSoundType(SoundType.STONE);
		setHardness(4F);
		setResistance(100F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileElevator();
	}
}