package T145.magistics.addons;

import net.minecraftforge.fml.common.Loader;

public abstract class AddonBase implements IAddon {

	private final String modid;

	public AddonBase(String modid) {
		this.modid = modid;
	}

	public String getModId() {
		return modid;
	}

	public boolean isLoaded() {
		return Loader.isModLoaded(modid);
	}
}