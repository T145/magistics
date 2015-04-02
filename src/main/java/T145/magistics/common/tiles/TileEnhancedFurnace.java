package T145.magistics.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEnhancedFurnace extends TileEntityFurnace {
	private static int speedModifier = 0;

	public static int getSpeedModifier() {
		return speedModifier;
	}

	public static void setSpeedModifier(int speed) {
		speedModifier = speed;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int tick) {
		return furnaceCookTime * tick / 200;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int tick) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}

		return furnaceBurnTime * tick / currentItemBurnTime;
	}
}