package hu.hundevelopers.elysium.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ElysiumBlockRilt extends ElysiumBlock
{
	public ElysiumBlockRilt(Material mat)
	{
		super(mat);
	}

	@Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		entity.motionX *= 0.025;
		entity.motionY *= 0.025;
		entity.motionZ *= 0.025;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
}
