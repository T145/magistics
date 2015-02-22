package hu.hundevelopers.elysium.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;

public class ElysiumBlockBush extends BlockBush
{
	public ElysiumBlockBush()
	{
		super();
	}
	
	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = "elysium:" + texture;
        return this;
    }
}
