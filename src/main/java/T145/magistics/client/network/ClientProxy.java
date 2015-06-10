package T145.magistics.client.network;

import T145.magistics.common.network.CommonProxy;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		reg.registerRenderers();
	}
}