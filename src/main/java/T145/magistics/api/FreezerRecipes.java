package T145.magistics.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FreezerRecipes
{
	public static Map<String, LiquidRecipe> fluids = new HashMap<String, LiquidRecipe>();

	private FreezerRecipes()
	{
	}

	public static ItemStack getResultForFluid(Fluid liquid)
	{
		LiquidRecipe res = fluids.get(liquid.getName());
		if(res == null) return null;
		return res.result;
	}
	
	public static ItemStack getResultForFluid(FluidStack liquid)
	{
		return getResultForFluid(liquid.getFluid());
	}

	public static final class LiquidRecipe
	{
		public final Fluid liquid;
		public final ItemStack result;

		private LiquidRecipe(String fluidName, ItemStack result)
		{
			this(FluidRegistry.getFluid(fluidName), result);
		}

		private LiquidRecipe(Fluid liquid, ItemStack result)
		{
			this.liquid = liquid;
			this.result = result;
		}
	}

	public static void addRecipe(Fluid fluid, ItemStack result)
	{
		fluids.put(fluid.getName(), new LiquidRecipe(fluid, result));
	}

	public static void addRecipe(String fluidName, ItemStack result)
	{
		fluids.put(fluidName, new LiquidRecipe(fluidName, result));
	}
}