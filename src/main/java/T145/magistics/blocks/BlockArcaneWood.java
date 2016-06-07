package T145.magistics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import T145.magistics.Magistics;

public class BlockArcaneWood extends Block {
	public static final Block INSTANCE = new BlockArcaneWood();

	public BlockArcaneWood() {
		super(Material.wood);

		setBlockName("arcane_wood");
		setBlockTextureName("magistics:arcane_wood");
		setCreativeTab(Magistics.tabMagistics);

		setHardness(2F);
		setStepSound(soundTypeWood);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		return 0;
	}
}