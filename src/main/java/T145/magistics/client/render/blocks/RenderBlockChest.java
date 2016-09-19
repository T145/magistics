package T145.magistics.client.render.blocks;

import T145.magistics.client.lib.ClientChestRenderHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderBlockChest implements ISimpleBlockRenderingHandler {
	private int id = 0;
	private ResourceLocation[] textures;
	private ResourceLocation overlay;
	private TileEntity chest;

	public RenderBlockChest(int renderID, ResourceLocation[] resources) {
		id = renderID;
		textures = resources;
	}

	public RenderBlockChest(int renderID, ResourceLocation[] resources, ResourceLocation overlay) {
		this(renderID, resources);
		this.overlay = overlay;
	}

	public RenderBlockChest(int renderID, TileEntity tile) {
		id = renderID;
		chest = tile;
	}

	public boolean hasOverlay() {
		return overlay != null;
	}

	public boolean hasTileEntity() {
		return chest != null;
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		if (hasTileEntity()) {
			ClientChestRenderHelper.renderChest(chest);
		} else {
			Minecraft.getMinecraft().getTextureManager().bindTexture(textures[meta]);
			ClientChestRenderHelper.renderChest(false);

			if (hasOverlay()) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(overlay);
				ClientChestRenderHelper.renderChest(true);
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return id;
	}
}