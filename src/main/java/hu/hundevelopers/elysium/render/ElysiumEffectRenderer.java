package hu.hundevelopers.elysium.render;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ElysiumEffectRenderer
{
	@SideOnly(Side.CLIENT)
	private static EffectRenderer effectRenderer = FMLClientHandler.instance().getClient().effectRenderer;

	public static void addBlockHitEffect(World worldObj, double x, double y, double z, Block block, int meta)
    {
        byte b0 = 4;

        for (int i = 0; i < b0; ++i)
        {
            for (int j = 0; j < b0; ++j)
            {
                for (int k = 0; k < b0; ++k)
                {
                    double d0 = (double)x + ((double)i + 0.5D) / (double)b0;
                    double d1 = (double)y + ((double)j + 0.5D) / (double)b0;
                    double d2 = (double)z + ((double)k + 0.5D) / (double)b0;
                	effectRenderer.addEffect((new EntityDiggingFX(worldObj, d0, d1, d2, d0 - (double)x - 0.5D, d1 - (double)y - 0.5D, d2 - (double)z - 0.5D, block, meta)).applyColourMultiplier((int)x, (int)y, (int)z));
                }
            }
        }
    }
}
