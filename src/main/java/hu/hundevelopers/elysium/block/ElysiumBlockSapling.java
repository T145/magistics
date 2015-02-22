package hu.hundevelopers.elysium.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenCorruptFostimber;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenDarkFostimber;
import hu.hundevelopers.elysium.world.gen.features.ElysiumGenFostimber;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ElysiumBlockSapling extends BlockSapling
{
	public static final String[] names = new String[] {"fostimber", "gildensilv"};
    private static final IIcon[] icons = new IIcon[names.length];
    
	public ElysiumBlockSapling()
	{
		super();
		this.setCreativeTab(Elysium.tabElysium);
		
		float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1F - f, 0.5F + f);
	}

	@Override
    public Block setBlockTextureName(String texture)
    {
        this.textureName = Elysium.ID  + ":" + texture;
        return this;
    }
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        meta &= 7;
        return icons[MathHelper.clamp_int(meta, 0, 5)];
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int meta)
    {
        return MathHelper.clamp_int(meta & 7, 0, 5);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
    	for(int i = 0; i < names.length; i++)
        list.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(this.getTextureName() + "_" + names[i]);
        }
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_)
    {
        return true;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z)
    {
        return (double)world.rand.nextFloat() < 0.45D;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z)
    {
        this.func_149879_c(world, x, y, z, rand);
    }
    
    @Override
    public void func_149879_c(World world, int x, int y, int z, Random rand)
    {
        int l = world.getBlockMetadata(x, y, z);

        if ((l & 8) == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, l | 8, 4);
        }
        else
        {
            this.growTree(world, x, y, z, rand, l & 7);
        }
    }
    
    public void growTree(World world, int x, int y, int z, Random rand, int meta)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        WorldGenerator treeGenerator;
        switch (meta) {
	        case 0:
				treeGenerator = new ElysiumGenFostimber(Elysium.blockLeaves, Elysium.blockLog, true);
			break;
	        case 1:
				treeGenerator = new ElysiumGenDarkFostimber(Elysium.blockLeaves, Elysium.blockLog, 1, 1, true, false);
			break;

			default:
				treeGenerator = new ElysiumGenFostimber(Elysium.blockLeaves, Elysium.blockLog, true);
			break;
		}


        Block block = Blocks.air;

        if (flag)
        {
            world.setBlock(x + i1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        }
        else
        {
            world.setBlock(x, y, z, block, 0, 4);
        }

		if (!((WorldGenerator)treeGenerator).generate(world, rand, x + i1, y, z + j1))
        {
            if (flag)
            {
                world.setBlock(x + i1, y, z + j1, this, meta, 4);
                world.setBlock(x + i1 + 1, y, z + j1, this, meta, 4);
                world.setBlock(x + i1, y, z + j1 + 1, this, meta, 4);
                world.setBlock(x + i1 + 1, y, z + j1 + 1, this, meta, 4);
            }
            else
            {
                world.setBlock(x, y, z, this, meta, 4);
            }
        }
    }
    
    /**
     * is the block grass, dirt or farmland
     */
    @Override
    protected boolean canPlaceBlockOn(Block block)
    {
        return super.canPlaceBlockOn(block) || block == Elysium.blockDirt || block == Elysium.blockGrass;
    }
}
