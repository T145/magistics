package T145.magistics.network;

import T145.magistics.Magistics;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Magistics.MODID + ":" + id, "inventory"));
	}

	@Override
	public void registerBlockRenderer(Block block, int meta, String id) {
		registerItemRenderer(Item.getItemFromBlock(block), meta, id);
	}
}