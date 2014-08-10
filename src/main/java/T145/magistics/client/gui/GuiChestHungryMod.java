package T145.magistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import T145.magistics.common.blocks.BlockChestHungryMod;
import T145.magistics.common.blocks.BlockChestHungryMod.Types;

public class GuiChestHungryMod extends GuiContainer {
	private BlockChestHungryMod.Types types;
	private ResourceLocation guiTexture[] = new ResourceLocation[types.values().length];

	public GuiChestHungryMod(Container c) {
		super(c);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		for (Types type : types.values()) {
			guiTexture[type.ordinal()] = new ResourceLocation("magistics", "textures/gui/chest_" + type.name());
			mc.getTextureManager().bindTexture(guiTexture[type.ordinal()]);
		}
		int x = (width - xSize) / 2, y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}