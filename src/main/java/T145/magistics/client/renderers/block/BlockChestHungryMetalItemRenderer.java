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
		BlockChestHungryMetal.Types chest = BlockChestHungryMetal.Types.values()[item.getItemDamage()];
		ResourceLocation loc = TextureHelper.ironChestTextures.get(chest);
		if (loc != null)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(loc);

		switch (type) {
		case ENTITY: {
			renderChest(0.5F, 0.5F, 0.5F, item.getItemDamage());
			break;
		}
		case EQUIPPED: {
			renderChest(1.0F, 1.0F, 1.0F, item.getItemDamage());
			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			renderChest(1.0F, 1.0F, 1.0F, item.getItemDamage());
			break;
		}
		case INVENTORY: {
			renderChest(0.0F, 0.075F, 0.0F, item.getItemDamage());
			break;
		}
		default:
			break;
		}
	}

	public void renderChest(float x, float y, float z, int meta) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		chestModel.renderAll();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}
}