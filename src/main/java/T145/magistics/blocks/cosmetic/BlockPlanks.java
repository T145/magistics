package T145.magistics.blocks.cosmetic;

import T145.magistics.api.variants.blocks.EnumWood;
import T145.magistics.blocks.MBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPlanks extends MBlock<EnumWood> {

	public BlockPlanks() {
		super("planks", Material.WOOD, EnumWood.class);

		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		setHardness(2F);
		setResistance(5F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
}