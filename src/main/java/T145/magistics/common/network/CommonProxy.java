package T145.magistics.common.network;

import T145.magistics.common.Magistics;
import T145.magistics.common.config.ModConfig;
import T145.magistics.common.lib.ModRegistry;

public abstract class CommonProxy {
	protected ModRegistry reg = Magistics.getRegistry();

	public abstract void registerRenderers();

	public void registerObjects() {
		reg.registerObjects();
		ModConfig.sendInterModComms();
	}
}