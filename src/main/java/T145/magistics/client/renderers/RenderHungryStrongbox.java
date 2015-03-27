package T145.magistics.client.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import T145.magistics.common.tiles.TileHungryStrongbox;
import codechicken.lib.render.CCRenderState;
import cofh.core.render.RenderUtils;
import cofh.lib.render.RenderHelper;
import cofh.thermalexpansion.block.strongbox.BlockStrongbox;
import cofh.thermalexpansion.render.model.ModelStrongbox;

public class RenderHungryStrongbox extends TileEntitySpecialRenderer implements IItemRenderer {
	static ResourceLocation[] texture = new ResourceLocation[BlockStrongbox.Types.values().length];
	static ModelStrongbox model = new ModelStrongbox();

	public void render(int n, int n2, int n3, double n4, double n5, double n6) {
		RenderHelper.bindTexture(texture[n]);
		GL11.glPushMatrix();
		GL11.glTranslated(n4, n5 + 1.0, n6 + 1.0);
		GL11.glScalef(1.0f, -1.0f, -1.0f);
		GL11.glTranslatef(0.5f, 0.5f, 0.5f);
		GL11.glRotatef((float) RenderUtils.facingAngle[n3], 0.0f, 1.0f, 0.0f);
		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
		GL11.glEnable(32826);
		model.render(n2);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double n, double n2, double n3, float n4) {
		CCRenderState.reset();
		CCRenderState.pullLightmap();
		CCRenderState.setDynamic();
		TileHungryStrongbox tileStrongbox = (TileHungryStrongbox) tileEntity;
		model.boxLid.rotateAngleX = (float) tileStrongbox.getRadianLidAngle(n4);
		render(tileStrongbox.type, tileStrongbox.getAccess().ordinal(), tileStrongbox.getFacing(), n, n2, n3);
	}

	@Override
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... array) {
		double n = -0.5;
		if (itemRenderType == ItemRenderType.EQUIPPED || itemRenderType == ItemRenderType.EQUIPPED_FIRST_PERSON)
			n = 0.0;
		int byte1 = 0;
		if (itemStack.stackTagCompound != null)
			byte1 = itemStack.stackTagCompound.getByte("Access");
		model.boxLid.rotateAngleX = 0.0f;
		render(itemStack.getItemDamage(), byte1, 5, n, n, n);
		GL11.glEnable(32826);
	}

	static {
		for (BlockStrongbox.Types type : BlockStrongbox.Types.values())
			texture[type.ordinal()] = new ResourceLocation("magistics", "textures/models/chest_hungry/" + type.name().toLowerCase() + ".png");
	}
}