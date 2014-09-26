package T145.magistics.client.renderers.block;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import T145.magistics.common.blocks.BlockChestHungryMetal;
import T145.magistics.common.lib.TextureHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockChestHungryMetalItemRenderer implements IItemRenderer {
	public ModelChest chestModel = new ModelChest();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		render(item, 0);
		render(item, 1);
	}

	public void render(ItemStack itemStack, int renderPass) {
		if (itemStack == null)
			return;

		if (renderPass == 0) {
			BlockChestHungryMetal.Types type = BlockChestHungryMetal.Types.values()[itemStack.getItemDamage()];
			ResourceLocation loc = TextureHelper.ironChestTextures.get(type);
			if (loc != null)
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(loc);
		}

		GL11.glPushMatrix();
		GL11.glTranslatef(1F, 1F, 1F);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		chestModel.renderAll();
		GL11.glPopMatrix();
	}
}