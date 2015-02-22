package hu.hundevelopers.elysium.block;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;

public class ElysiumBlockBush extends BlockBush
{
	public ElysiumBlockBush()
	{
		super();
		this.setCreativeTab(Elysium.tabElysium);
	}
	
	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.ID + ":" + texture;
        return this;
    }
}
