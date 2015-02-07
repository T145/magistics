package T145.magistics.api.client.renderers.block;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import T145.magistics.api.client.lib.ChestRenderHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ChestItemRenderer implements IItemRenderer {
	public ResourceLocation textures[], cover;

	public ChestItemRenderer(ResourceLocation[] resources) {
		textures = resources;
	}

	public ChestItemRenderer(ResourceLocation[] resources, ResourceLocation overlay) {
		textures = resources;
		cover = overlay;
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
		} else if (cover != null)
			bindTexture(cover);
		ChestRenderHelper.renderChest(type);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack is, Object... data) {
		if (is != null)
			for (int pass = 0; pass < 2; pass++)
				render(type, is, pass);
	}
}