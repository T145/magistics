package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class ElysiumBlockFalling extends BlockFalling
{
	public ElysiumBlockFalling(Material mat)
	{
		super(mat);
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.ID  + ":" + texture;
        return this;
    }
}
