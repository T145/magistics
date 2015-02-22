package hu.hundevelopers.elysium.heat;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IHeatable {
	public void setHeatAt(World world, int x, int y, int z, float heat);
	public float getHeatAt(IBlockAccess blockAccess, int x, int y, int z);
}