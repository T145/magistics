package hu.hundevelopers.elysium.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.hundevelopers.elysium.heat.HeatManager;
import hu.hundevelopers.elysium.heat.IHeatable;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ElysiumHeatableMetaImpl extends ElysiumBlock implements IHeatable {
	public float minHeat, maxHeat;

	public ElysiumHeatableMetaImpl(Material mat, float minHeat, float maxHeat) {
		super(mat);
		this.minHeat = minHeat;
		this.maxHeat = maxHeat;
	}

	public float toHeatPercentage(float heat) {
		if(heat > this.maxHeat)
			heat = this.maxHeat;
		if(heat < this.minHeat)
			heat = this.minHeat;
		return (heat - this.minHeat) / (this.maxHeat - this.minHeat);
	}

	public float toHeatValue(float percentage) {
		if(percentage > 1F)
			percentage = 1F;
		if(percentage < 0F)
			percentage = 0F;
		return this.minHeat + (this.maxHeat - this.minHeat) * percentage;
	}

	@Override
	public void setHeatAt(World world, int x, int y, int z, float heat) {
		int meta = (int) (this.toHeatPercentage(heat) * 15);
		if (meta < 0)
			meta = 0;
		if (meta > 15)
			meta = 15;
		world.setBlockMetadataWithNotify(x, y, z, meta, 1);
	}

	@Override
	public float getHeatAt(IBlockAccess blockAccess, int x, int y, int z) {
		return this.toHeatValue(blockAccess.getBlockMetadata(x, y, z)/15F);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return 15/2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		float heat = this.toHeatPercentage(HeatManager.getInstance().getHeatAt(blockAccess, x, y, z));
		int r = (int)(heat >= 0.5F ? 255 : heat*255/0.5F);
		int b = (int)(heat <= 0.5F ? 255 : (1F-heat)*255/0.5F);
		int g = (int)((1F-Math.abs(heat-0.5F)/0.5F)*255);
		return (r << 16) + (g << 8) + b;
	}
}