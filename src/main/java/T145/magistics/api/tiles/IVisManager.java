package T145.magistics.api.tiles;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public interface IVisManager {

	public boolean getConnectable(EnumFacing facing);

	boolean isVisSource();
	boolean isVisConduit();

	float[] subtractVis(float amount);

	float getPureVis();
	void setPureVis(float amount);

	float getTaintedVis();
	void setTaintedVis(float amount);

	float getMaxVis();

	int getVisSuction(BlockPos pos);
	void setVisSuction(int amount);

	int getTaintSuction(BlockPos pos);
	void setTaintSuction(int amount);

	void setSuction(int pressure);
	int getSuction(BlockPos amount);
}
