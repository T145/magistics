package me.dawars.mythril.proxy;

import me.dawars.mythril.MythrilMod;
import me.dawars.mythril.renderer.RenderMythrilPillar;
import me.dawars.mythril.tiles.TileMythrilPillar;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initIconRegistry() {
        MythrilMod.mythrilArmorID = RenderingRegistry.addNewArmourRendererPrefix("mythril");
        MythrilMod.mythrilPillarRenderID = RenderingRegistry.getNextAvailableRenderId();
    }
    
    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderMythrilPillar());
        ClientRegistry.bindTileEntitySpecialRenderer((Class)TileMythrilPillar.class, (TileEntitySpecialRenderer)new RenderMythrilPillar());
    }
}
