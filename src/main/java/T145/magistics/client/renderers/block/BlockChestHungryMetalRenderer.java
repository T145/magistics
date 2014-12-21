package T145.magistics.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import T145.magistics.client.lib.ChestRenderHelper;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.ironchest.IronChestType;

public class BlockChestHungryMetalRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		ResourceLocation texture = TextureHelper.ironChestTextures.get(IronChestType.values()[meta]);
		if (texture != null)
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		ChestRenderHelper.renderChest(0.5F, 0.5F, 0.5F, new ModelChest());
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockChestHungryMetal.renderID;
	}
}