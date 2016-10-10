package T145.magistics.blocks;

import T145.magistics.api.blocks.IBlockMagisticsEnum;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockInfuser extends BlockMagistics {

	public BlockInfuser() {
		super(Material.ROCK, InfuserType.class, SoundType.STONE);
	}

	public static enum InfuserType implements IBlockMagisticsEnum {
		LIGHT, DARK;

		private InfuserType() {}

		public String getName() {
			return name().toLowerCase();
		}

		public String toString() {
			return getName();
		}
	}
}