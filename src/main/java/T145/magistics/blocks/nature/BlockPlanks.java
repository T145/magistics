package T145.magistics.blocks.nature;

import T145.magistics.blocks.MBlock;
import T145.magistics.blocks.nature.BlockPlanks.WoodType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockPlanks extends MBlock<WoodType> {

	public BlockPlanks() {
		super("planks", Material.WOOD, WoodType.class);
		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		setHardness(2F);
		setResistance(5F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	public static enum WoodType implements IStringSerializable {

		GREATWOOD, SILVERWOOD;

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		public static WoodType byMetadata(int meta) {
			return values()[MathHelper.clamp(meta, 0, meta)];
		}
	}
}