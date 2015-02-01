package T145.magistics.api.client.renderers.block;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import T145.magistics.api.client.lib.ChestRenderHelper;

import com.dynious.refinedrelocation.lib.Resources;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SortingChestItemRenderer implements IItemRenderer {
	public ResourceLocation textures[];

	public SortingChestItemRenderer(ResourceLocation[] resources) {
		textures = resources;
	}

	@Override
	public boolean handleRenderType(ItemStack is, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack is, ItemRendererHelper helper) {
		return true;
	}

	public void bindTexture(ResourceLocation texture) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
	}

	public void render(ItemRenderType type, ItemStack is, int pass) {
		if (pass == 0) {
			if (textures.length > 0)
				if (textures.length == 1)
					bindTexture(textures[0]);
				else
					bindTexture(textures[is.getItemDamage()]);
			ChestRenderHelper.renderChest(type);
		} else {
			bindTexture(Resources.MODEL_TEXTURE_OVERLAY_CHEST);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			ChestRenderHelper.renderChest(type);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
		if (is != null)
			for (int pass = 0; pass < 2; pass++)
				render(type, is, pass);
	}
}