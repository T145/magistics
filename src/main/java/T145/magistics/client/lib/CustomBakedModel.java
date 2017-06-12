package T145.magistics.client.lib;

import java.util.List;

import T145.magistics.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class CustomBakedModel implements IBakedModel {

	private final IBakedModel model;

	public CustomBakedModel(final IBakedModel modelIn) {
		model = modelIn;
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return model.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return model.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return model.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		//Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ModBlocks.chestVoid), model);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		return true; // force tesr rendering
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return model.getParticleTexture();
	}

	@SuppressWarnings("deprecation")
	@Override
	@Deprecated
	public ItemCameraTransforms getItemCameraTransforms() {
		return model.getItemCameraTransforms();
	}

	@Override
		return model.getOverrides();
	}
}
