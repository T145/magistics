package me.dawars.mythril.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseBlock extends Block
{
    public BaseBlock(final Material mat) {
        super(mat);
    }
    
    public boolean canBeReplacedByLeaves(final IBlockAccess world, final int x, final int y, final int z) {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister itemIcon) {
        super.blockIcon = itemIcon.registerIcon("mythril:" + this.getUnlocalizedName().substring(5));
    }
}
