package T145.magistics.client.renderers.block;

import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import T145.magistics.client.lib.ChestRenderHelper;
import T145.magistics.client.lib.TextureHelper;
import T145.magistics.common.blocks.BlockChestHungryMetal;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockChestHungryMetalItemRenderer implements IItemRenderer {
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
		BlockChestHungryMetal.Types chest = BlockChestHungryMetal.Types.values()[item.getItemDamage()];
		ResourceLocation loc = TextureHelper.ironChestTextures.get(chest);
		if (loc != null)
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(loc);
		ChestRenderHelper.renderChest(type, chestModel);
	}
}