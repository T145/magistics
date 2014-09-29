package T145.magistics.client.renderers.block;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL12;

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

		glPushMatrix();
		glTranslatef(-0.5F, -0.5F, -0.5F);
		glEnable(GL12.GL_RESCALE_NORMAL);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		glTranslatef(0.0F, 1.0F, 1.0F);
		glScalef(1.0F, -1F, -1F);
		glTranslatef(0.5F, 0.5F, 0.5F);
		glRotatef(-90F, 0.0F, 1.0F, 0.0F);
		glTranslatef(-0.5F, -0.5F, -0.5F);
		chestModel.renderAll();
		glDisable(GL12.GL_RESCALE_NORMAL);
		glPopMatrix();
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}