package T145.magistics.blocks;

import T145.magistics.api.enums.EnumWood;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.model.IModel;

public class BlockPlanks extends MBlock<EnumWood> {

	public BlockPlanks(String name) {
		super(name, Material.WOOD, EnumWood.class);

		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		setHardness(2F);
		setResistance(5F);
	}
}