package T145.magistics.fluids;

import T145.magistics.Magistics;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MFluid extends Fluid {

	public static final ResourceLocation STILL = new ResourceLocation(Magistics.MODID, "fluids/liquid");
	public static final ResourceLocation FLOWING = new ResourceLocation(Magistics.MODID, "blocks/fluid/liquid_flow");

	private int color;

	public MFluid(String fluidName, ResourceLocation still, ResourceLocation flowing) {
		super(fluidName, still, flowing);
	}

	public MFluid(String fluidName, int color, ResourceLocation still, ResourceLocation flowing) {
		this(fluidName, still, flowing);

		if (((color >> 24) & 0xFF) == 0) {
			color |= 0xFF << 24;
		}

		this.color = color;
	}

	public MFluid(String fluidName, int color) {
		this(fluidName, color, STILL, FLOWING);
	}

	public MFluid(String fluidName) {
		this(fluidName, STILL, FLOWING);
	}

	@Override
	public int getColor() {
		return color == 0 ? super.getColor() : color;
	}
}