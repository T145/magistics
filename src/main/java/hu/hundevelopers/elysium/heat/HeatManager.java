package hu.hundevelopers.elysium.heat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hu.hundevelopers.elysium.Elysium;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HeatManager {
	private static HeatManager instance = new HeatManager();
	
	public static HeatManager getInstance() {
		return instance;
	}
	
	private HashMap map = new HashMap<Block, Float>();
	
	public void registerBlock(Block block, float heat) {
		this.map.put(block, new Float(heat));
	}
	
	public float getBlockHeat(Block block) {
		if(this.map.containsKey(block))
			return ((Float)this.map.get(block)).floatValue();
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
			heat += this.getBlockHeat(b);
		return heat;
	}
}
