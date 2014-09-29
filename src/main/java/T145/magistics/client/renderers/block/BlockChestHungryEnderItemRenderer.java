package T145.magistics.client.renderers.block;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import T145.magistics.common.lib.ChestRenderHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockChestHungryEnderItemRenderer implements IItemRenderer {
	public ModelChest chestModel = new ModelChest();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("magistics", "textures/models/chest_hungry/ender.png"));
		ChestRenderHelper.renderChest(type, chestModel);
	}
}