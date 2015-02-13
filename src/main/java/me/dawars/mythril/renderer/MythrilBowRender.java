package me.dawars.mythril.renderer;

import net.minecraftforge.client.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;

public class MythrilBowRender implements IItemRenderer
{
    private RenderManager renderManager;
    private Minecraft mc;
    private TextureManager texturemanager;
    private RenderBlocks renderBlocksIr;
    
    public MythrilBowRender() {
        this.renderBlocksIr = new RenderBlocks();
        this.renderManager = RenderManager.instance;
        this.mc = Minecraft.getMinecraft();
        this.texturemanager = this.mc.getTextureManager();
    }
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return false;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
    }
}
