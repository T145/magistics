package hu.hundevelopers.elysium.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.biome.ElysiumBiomeGenCorruption;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.util.ForgeDirection;

public class ElysiumBlockGrass extends ElysiumBlock implements IGrowable
{
	@SideOnly(Side.CLIENT)
	private IIcon icon_top;
    @SideOnly(Side.CLIENT)
    private IIcon icon_snow;
    @SideOnly(Side.CLIENT)
    private IIcon icon_side;
	    
	public ElysiumBlockGrass(Material mat)
	{
		super(mat);
        this.setTickRandomly(true);
	}
	
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        if (world.getBiomeGenForCoords(x, z) instanceof ElysiumBiomeGenCorruption && side == UP)
        {
            return true;
        }
        return false;
    }
	
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.icon_top : (side == 0 ? Elysium.blockDirt.getBlockTextureFromSide(side) : this.blockIcon);
    }
    
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(Elysium.blockDirt);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, Elysium.blockDirt);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = x + rand.nextInt(3) - 1;
                    int j1 = y + rand.nextInt(5) - 3;
                    int k1 = z + rand.nextInt(3) - 1;
                    Block block = world.getBlock(i1, j1 + 1, k1);

                    if (world.getBlock(i1, j1, k1) == Elysium.blockDirt && world.getBlockMetadata(i1, j1, k1) == 0 && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                        world.setBlock(i1, j1, k1, Elysium.blockGrass);
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        if (side == 1)
        {
            return this.icon_top;
        }
        else if (side == 0)
        {
            return Elysium.blockDirt.getBlockTextureFromSide(side);
        }
//        else
//        {
//            Material material = p_149673_1_.getBlock(p_149673_2_, p_149673_3_ + 1, p_149673_4_).getMaterial();
//            return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.icon_snow;
//        }
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.icon_top = iconRegister.registerIcon(this.getTextureName() + "_top");
    }

	@Override //can use bonemeal on block
	public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
		return true;
	}


	@Override //cangrow
	public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
		return true;
	}


	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z)
	{
        int l = 0;

        while (l < 128)
        {
            int i1 = x;
            int j1 = y + 1;
            int k1 = z;
            int l1 = 0;

            while (true)
            {
                if (l1 < l / 16)
                {
                    i1 += rand.nextInt(3) - 1;
                    j1 += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
                    k1 += rand.nextInt(3) - 1;

                    if (world.getBlock(i1, j1 - 1, k1) == Elysium.blockGrass && !world.getBlock(i1, j1, k1).isNormalCube())
                    {
                        ++l1;
                        continue;
                    }
                }
                else if (world.getBlock(i1, j1, k1).getMaterial() == Material.air)
                {
                    if (rand.nextInt(8) != 0)
                    {
                        if (Elysium.blockTallGrass.canBlockStay(world, i1, j1, k1))
                        {
                            world.setBlock(i1, j1, k1, Elysium.blockTallGrass, 1, 3);
                        }
                    }
                    else
                    {
                        world.getBiomeGenForCoords(i1, k1).plantFlower(world, rand, i1, j1, k1);
                    }
                }

                ++l;
                break;
            }
        }
    }
    
}
