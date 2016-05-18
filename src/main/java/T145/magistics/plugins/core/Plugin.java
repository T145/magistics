package T145.magistics.plugins.core;

public abstract class Plugin {
	protected final String name;
	protected static boolean active = false;

	public Plugin(String modid, boolean enable) {
		name = modid;
		active = enable;
	}

	public String getModId() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public abstract void preInit();
	public abstract void init();
	public abstract void postInit();
}