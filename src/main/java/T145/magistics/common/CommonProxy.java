package T145.magistics.common;

import T145.magistics.common.lib.ModRegistry;

public abstract class CommonProxy {
	protected ModRegistry reg = Magistics.getRegistry();

	public abstract void registerRenderers();

	public void registerObjects() {
		reg.registerObjects();
	}
}