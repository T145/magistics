package T145.magistics.init;

import T145.magistics.config.ConfigMain;
import T145.magistics.world.providers.WorldProviderVoid;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {

	public static DimensionType voidDimensionType;

	public static void init() {
		voidDimensionType = DimensionType.register("The Void", "void", ConfigMain.voidDimensionId, WorldProviderVoid.class, false);
		DimensionManager.registerDimension(ConfigMain.voidDimensionId, voidDimensionType);
	}
}