package T145.magistics.tiles.cosmetic;

import T145.magistics.tiles.base.TileSynchronized;
import net.minecraft.util.ITickable;

public class TileNitor extends TileSynchronized implements ITickable {

	public void update() {
		if (world.isRemote) {

			// draw wisp particles
		}
	}
}