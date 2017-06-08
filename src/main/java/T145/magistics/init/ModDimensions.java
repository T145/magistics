package T145.magistics.init;

import T145.magistics.config.ConfigMain;
import T145.magistics.world.providers.WorldProviderVoid;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ModDimensions {

	public static DimensionType voidDimensionType;

	public static void init() {
		voidDimensionType = DimensionType.register("The Void", "void", ConfigMain.voidDimensionId, WorldProviderVoid.class, false);
		DimensionManager.registerDimension(ConfigMain.voidDimensionId, voidDimensionType);
	}

	public static WorldServer getWorldServerForDimension(int dim) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(dim);
	}

	public static WorldServer getServerVoidWorld() {
		return getWorldServerForDimension(ConfigMain.voidDimensionId);
	}
}