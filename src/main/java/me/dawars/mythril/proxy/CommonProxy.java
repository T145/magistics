package me.dawars.mythril.proxy;

import cpw.mods.fml.common.*;

public class CommonProxy
{
    public void initIconRegistry() {
    }
    
    public void registerRenderers() {
    }
    
    public String getMinecraftVersion() {
        return Loader.instance().getMinecraftModContainer().getVersion();
    }
}
