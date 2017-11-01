package T145.magistics.world.data;

import java.util.HashMap;

import T145.magistics.Magistics;
import T145.magistics.core.ConfigMain;
import T145.magistics.lib.world.DimensionBlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Magistics.MODID)
public class WorldDataVoidChest extends WorldSavedData {

	public int nextCoord = 0;
	public HashMap<Integer, double[]> spawnPoints = new HashMap<>();
	public HashMap<Integer, DimensionBlockPos> voidChestPositions = new HashMap<>();

	public static WorldDataVoidChest INSTANCE;

	public WorldDataVoidChest(String name) {
		super(name);
	}

	public DimensionBlockPos getChestPosition(int coord) {
		return voidChestPositions.get(coord);
	}

	public void addVoidChestPosition(int coord, BlockPos pos, int dimension) {
		voidChestPositions.put(coord, new DimensionBlockPos(pos, dimension));
		Magistics.LOG.debug("Adding chest position: coords=%d, pos=%s, dimension=%d", coord, pos, dimension);
		markDirty();
	}

	public void removeVoidChestPosition(int coord) {
		voidChestPositions.remove(coord);
		Magistics.LOG.debug("Removing chest position by coord: coords=%d", coord);
		markDirty();
	}

	private void addSpawnPoint(int coord, double[] destination) {
		if (destination.length != 3) {
			Magistics.LOG.warn("Trying to set spawn point with invalid double[]=%s", destination);
			return;
		}

		spawnPoints.put(coord, destination);
		Magistics.LOG.debug("Setting spawn point: coords=%d, x=%.2f, y=%.2f, z=%.2f", coord, destination[0], destination[1], destination[2]);
		markDirty();
	}

	public void addSpawnPoint(int coord, double x, double y, double z) {
		addSpawnPoint(coord, new double[] { x, y, z });
	}

	public static int reserveVoidChestId() {
		int val = WorldDataVoidChest.INSTANCE.nextCoord;
		WorldDataVoidChest.INSTANCE.nextCoord++;
		WorldDataVoidChest.INSTANCE.markDirty();
		return val;
	}

	@SubscribeEvent
	public static void loadWorld(WorldEvent.Load event) {
		if (event.getWorld().isRemote || event.getWorld().provider.getDimension() != ConfigMain.voidDimensionId) {
			return;
		}

		Magistics.LOG.info("Loading saved data for chest world");
		WorldDataVoidChest wsd = (WorldDataVoidChest) event.getWorld().getMapStorage().getOrLoadData(WorldDataVoidChest.class, "WorldDataVoidChest");

		if (wsd == null) {
			wsd = new WorldDataVoidChest("WorldDataVoidChest");
			wsd.markDirty();
		}

		Magistics.LOG.info(" > " + wsd.spawnPoints.size() + " spawn points");
		Magistics.LOG.info(" > Next chest id: " + wsd.nextCoord);

		WorldDataVoidChest.INSTANCE = wsd;
		event.getWorld().getMapStorage().setData("WorldDataVoidChest", wsd);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("nextChestCoord", nextCoord);

		NBTTagList spawnPointList = new NBTTagList();
		for (int coords : spawnPoints.keySet()) {
			double[] positions = spawnPoints.get(coords);
			NBTTagCompound tag = new NBTTagCompound();

			tag.setInteger("coords", coords);
			tag.setDouble("x", positions[0]);
			tag.setDouble("y", positions[1]);
			tag.setDouble("z", positions[2]);
			spawnPointList.appendTag(tag);
		}

		NBTTagList chestList = new NBTTagList();
		for (int coords : voidChestPositions.keySet()) {
			DimensionBlockPos dimpos = voidChestPositions.get(coords);
			BlockPos position = dimpos.getBlockPos();
			NBTTagCompound tag = new NBTTagCompound();

			tag.setInteger("coords", coords);
			tag.setInteger("x", position.getX());
			tag.setInteger("y", position.getY());
			tag.setInteger("z", position.getZ());
			tag.setInteger("dim", dimpos.getDimension());
			chestList.appendTag(tag);
		}

		compound.setTag("spawnpoints", spawnPointList);
		compound.setTag("voidchests", chestList);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		nextCoord = compound.getInteger("nextChestCoord");

		if (compound.hasKey("spawnpoints")) {
			spawnPoints.clear();

			NBTTagList tagList = compound.getTagList("spawnpoints", 10);

			for (int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound tag = tagList.getCompoundTagAt(i);
				int coords = tag.getInteger("coords");
				double[] positions = new double[] { tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z") };

				spawnPoints.put(coords, positions);
			}
		}

		if (compound.hasKey("voidchests")) {
			voidChestPositions.clear();

			NBTTagList tagList = compound.getTagList("voidchests", 10);

			for (int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound tag = tagList.getCompoundTagAt(i);
				BlockPos position = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));

				voidChestPositions.put(tag.getInteger("coords"), new DimensionBlockPos(position, tag.getInteger("dim")));
			}
		}
	}
}