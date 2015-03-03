package T145.magistics.common.blocks.elysium;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class ElysiumBlockFalling extends BlockFalling
{
	public ElysiumBlockFalling(Material mat)
	{
		super(mat);
	}
	
	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = "elysium:" + texture;
        return this;
    }
}
