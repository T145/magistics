/*package T145.magistics.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.renderers.block.BlockRenderer;
import thermalexpansion.block.strongbox.BlockStrongbox;
import T145.magistics.client.lib.ChestRenderHelper;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.common.blocks.BlockHungryStrongbox;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockHungryStrongboxRenderer extends BlockRenderer implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		ResourceLocation texture = TextureHelper.strongboxTextures.get(BlockStrongbox.Types.values()[meta]);
		if (texture != null)
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		int byte1 = 0;
		ItemStack is = new ItemStack(block);
		if (is.stackTagCompound != null)
			byte1 = is.stackTagCompound.getByte("Access");
		ChestRenderHelper.renderStrongbox(byte1, 5, 0, 0, 0, 0);
		GL11.glEnable(32826);
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
		return BlockHungryStrongbox.renderID;
	}
}*/