package T145.magistics.blocks;

import T145.magistics.Magistics;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockMagistics extends Block {

	private String name;

	public BlockMagistics(Material material, String name) {
		super(material);

		this.name = name;

		setUnlocalizedName(Magistics.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(Magistics.tab);
	}

	public String getName() {
		return name;
	}

	@SideOnly(Side.CLIENT)
	public abstract void registerModel();
}