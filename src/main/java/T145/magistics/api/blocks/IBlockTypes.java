package T145.magistics.api.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

public abstract interface IBlockTypes {
	public abstract String getTypeName(IBlockState state);
	public abstract boolean hasTypes();
	public abstract IProperty[] getTypes();
}