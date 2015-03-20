package T145.magistics.client.fx;

import java.awt.Color;

import net.minecraft.world.World;
import thaumcraft.client.fx.particles.FXWisp;

public class FXColoredWisp extends FXWisp {
	public FXColoredWisp(World world, double x, double y, double z, double x2, double y2, double z2, float size, int type, boolean shrink, float gravity, Color color) {
		super(world, x, y, z, x2, y2, z2, size, type);

		particleRed = color.getRed();
		particleBlue = color.getBlue();
		particleGreen = color.getGreen();

		/*switch (colour) {
		case 0: {
			particleRed = 0.0f;
			particleBlue = 0.0f;
			particleGreen = 0.0f;
			particleGravity = 1F;
			break;
		}
		case 1: {
			particleRed = 1.0f;
			particleBlue = 0.0f + world.rand.nextFloat() * 0.25f;
			particleGreen = 0.0f + world.rand.nextFloat() * 0.25f;
			break;
		}
		case 2: {
			particleRed = 0.14f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.0f + world.rand.nextFloat() * 0.25f;
			particleGreen = 0.54f;
			break;
		}
		case 3: {
			particleRed = 0.43f + world.rand.nextFloat() * 0.1f;
			particleGreen = 0.24f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.0f;
			break;
		}
		case 4: {
			particleRed = 0.0f + world.rand.nextFloat() * 0.25f;
			particleBlue = 1.0f;
			particleGreen = 0.0f + world.rand.nextFloat() * 0.25f;
			break;
		}
		case 5: {
			particleRed = 0.69f;
			particleGreen = 0.0f + world.rand.nextFloat() * 0.2f;
			particleBlue = 0.76f + world.rand.nextFloat() * 0.1f;
			break;
		}
		case 6: {
			particleRed = 0.0f + world.rand.nextFloat() * 0.1f;
			particleGreen = 0.63f + world.rand.nextFloat() * 0.2f;
			particleBlue = 0.63f + world.rand.nextFloat() * 0.2f;
			break;
		}
		case 7: {
			particleRed = 0.02f + world.rand.nextFloat() * 0.2f;
			particleGreen = 0.02f + world.rand.nextFloat() * 0.2f;
			particleBlue = 0.02f + world.rand.nextFloat() * 0.2f;
			break;
		}
		case 8: {
			particleRed = 0.44f + world.rand.nextFloat() * 0.2f;
			particleGreen = 0.44f + world.rand.nextFloat() * 0.2f;
			particleBlue = 0.44f + world.rand.nextFloat() * 0.2f;
			break;
		}
		case 9: {
			particleRed = 0.88f;
			particleGreen = 0.0f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.82f + world.rand.nextFloat() * 0.2f;
			break;
		}
		case 10: {
			particleRed = 0.35f + world.rand.nextFloat() * 0.2f;
			particleGreen = 0.9f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.22f + world.rand.nextFloat() * 0.1f;
			break;
		}
		case 11: {
			particleRed = 0.7f + world.rand.nextFloat() * 0.1f;
			particleGreen = 0.7f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.0f + world.rand.nextFloat() * 0.2f;
			break;
		}
		case 12: {
			particleRed = 0.0f + world.rand.nextFloat() * 0.2f;
			particleGreen = 0.57f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.7f + world.rand.nextFloat() * 0.1f;
			break;
		}
		case 13: {
			particleRed = 0.45f + world.rand.nextFloat() * 0.2f;
			particleGreen = 0.0f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.6f + world.rand.nextFloat() * 0.2f;
			break;
		}
		case 14: {
			particleRed = 0.67f + world.rand.nextFloat() * 0.2f;
			particleGreen = 0.49f + world.rand.nextFloat() * 0.1f;
			particleBlue = 0.0f + world.rand.nextFloat() * 0.1f;
			break;
		}
		case 15: {
			particleRed = 1.0f;
			particleGreen = 1.0f;
			particleBlue = 1.0f;
			break;
		}
		case 16: {
			particleRed = world.rand.nextFloat();
			particleGreen = world.rand.nextFloat();
			particleBlue = world.rand.nextFloat();
			break;
		}
		}*/
	}
}