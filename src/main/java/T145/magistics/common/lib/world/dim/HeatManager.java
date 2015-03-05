package T145.magistics.common.lib.world.dim;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HeatManager {
	private static HeatManager instance = new HeatManager();
	
	public static HeatManager getInstance() {
		return instance;
	}
	
	private HashMap map = new HashMap<Block, Float>();
	
	public void registerBlock(Block block, float heat) {
		map.put(block, new Float(heat));
	}
	
	public float getBlockHeat(Block block) {
		if(map.containsKey(block))
			return ((Float)map.get(block)).floatValue();
		else
			return 0F;
	}
	
	public void setHeatAt(World world, int x, int y, int z, float heat) {
		((IHeatable)world.getBlock(x, y, z)).setHeatAt(world, x, y, z, heat);
	}
	
	public float getHeatAt(IBlockAccess blockAccess, int x, int y, int z) {
		float heat = blockAccess.getBiomeGenForCoords(x, z).getFloatTemperature(x, y, z)*40F;
		Block b = blockAccess.getBlock(x, y, z);
		if(b instanceof IHeatable)
			heat += ((IHeatable)b).getHeatAt(blockAccess, x, y, z);
		else
			heat += getBlockHeat(b);
		return heat;
	}
}
