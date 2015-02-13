package me.dawars.mythril.blocks;

import net.minecraft.block.material.*;
import me.dawars.mythril.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;
import me.dawars.mythril.tiles.*;
import net.minecraft.client.renderer.texture.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.creativetab.*;
import java.util.*;

public class MithrilPillarBlock extends BaseBlockContainer
{
    public MithrilPillarBlock(final Material mat) {
        super(mat);
    }
    
    public int getRenderType() {
        return MythrilMod.mythrilPillarRenderID;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public int onBlockPlaced(final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ, final int meta) {
        final int j1 = meta & 0x3;
        byte b0 = 0;
        switch (side) {
            case 0:
            case 1: {
                b0 = 0;
                break;
            }
            case 2:
            case 3: {
                b0 = 8;
                break;
            }
            case 4:
            case 5: {
                b0 = 4;
                break;
            }
        }
        return j1 | b0;
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileMythrilPillar tile = new TileMythrilPillar();
        return tile;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(final IIconRegister itemIcon) {
        super.blockIcon = itemIcon.registerIcon("mythril:mythril_pillar_side");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int par1, final int par2) {
        return super.blockIcon;
    }
    
    public int damageDropped(final int p_149692_1_) {
        return p_149692_1_ & 0x3;
    }
    
    public int func_150162_k(final int p_150162_1_) {
        return p_150162_1_ & 0x3;
    }
    
    protected ItemStack createStackedBlock(final int p_149644_1_) {
        return new ItemStack(Item.getItemFromBlock((Block)this), 1, this.func_150162_k(p_149644_1_));
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }
}
