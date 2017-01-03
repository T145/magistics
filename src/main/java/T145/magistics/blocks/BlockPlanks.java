package T145.magistics.blocks;

import T145.magistics.api.enums.EnumWood;
import T145.magistics.api.objects.IModel;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlanks extends MBlock<EnumWood> implements IModel {

	public BlockPlanks(String name) {
		super(name, Material.WOOD, EnumWood.class);

		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		setHardness(2F);
		setResistance(5F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		for (EnumWood type : EnumWood.values()) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.ordinal(), new ModelResourceLocation(getRegistryName(), type.getClientName()));
		}
	}
}