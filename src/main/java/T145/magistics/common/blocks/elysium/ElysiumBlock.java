package T145.magistics.common.blocks.elysium;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ElysiumBlock extends Block
{
	public ElysiumBlock(Material mat)
	{
		super(mat);
	}

	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = "elysium:" + texture;
        return this;
    }
	
    public boolean canBeReplacedByLake()
	{
		return true;
	}
}
