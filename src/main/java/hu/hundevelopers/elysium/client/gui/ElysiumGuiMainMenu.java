package hu.hundevelopers.elysium.client.gui;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import hu.hundevelopers.elysium.Elysium;
import hu.hundevelopers.elysium.render.RenderingHelper;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.FMLCommonHandler;

public class ElysiumGuiMainMenu extends ElysiumGui
{
	public ResourceLocation imgBackground = new ResourceLocation(Elysium.ID + ":textures/gui/menu/menu.png");
	public ResourceLocation imgTitle = new ResourceLocation(Elysium.ID + ":textures/gui/menu/title.png");
//	public ResourceLocation imgPatreon = new ResourceLocation(Elysium.ID + ":textures/gui/menu/patreon.png");
//	public ResourceLocation imgPatreonHover = new ResourceLocation(Elysium.ID + ":textures/gui/menu/patreon_hover.png");
	public ElysiumGuiButton btnSingle, btnMulti, btnMods, btnOptions, btnQuit;
	public ElysiumGui subGui;

	public int margin;
	public int titleX, titleY, titleWidth, titleHeight;
	public int menuX, menuY, menuWidth;
	public int subX, subY, subWidth, subHeight;
	public int btnHeight;
	
//	private boolean hoverPatreon = false;

    
	public ElysiumGuiMainMenu(ElysiumGui parent)
	{
		super(parent);
	}

	public void setSubGui(ElysiumGui gui) {
		if (this.subGui != null)
			this.subGui.close();
		this.subGui = gui;
	}

	@Override
	public void onMouseClicked(int x, int y, int btn) {
		super.onMouseClicked(x, y, btn);
		if (this.subGui != null)
			this.subGui.onMouseClicked(x, y, btn);
		
//		if(hoverPatreon && Elysium.isPatreonButton)
//		{
//			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
//		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
//		        try {
//		            desktop.browse(URI.create("http://www.patreon.com/dawars"));
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }
//		    }
//		}
	}

	@Override
	public void onKeyTyped(char c, int i) {
		super.onKeyTyped(c, i);
		if (this.subGui != null)
			this.subGui.onKeyTyped(c, i);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		this.margin = this.height / 100;
		this.titleX = this.width / 4;
		this.titleY = this.margin;
		this.titleWidth = this.width / 2;
		this.titleHeight = this.titleWidth / 5;
		this.menuX = this.margin;
		this.menuY = this.titleY + this.titleHeight + this.margin;
		this.menuWidth = this.titleX - this.menuX - this.margin;
		this.subX = this.titleX;
		this.subY = this.menuY;
		this.subWidth = this.titleWidth;
		this.subHeight = this.height - this.subY - this.margin;
		this.btnHeight = this.menuWidth / 5;

		this.btnSingle = new ElysiumGuiButton(this, this.menuX, this.menuY,
				this.menuWidth, this.btnHeight,
				StatCollector.translateToLocal("menu.singleplayer"));
		this.subs.add(this.btnSingle);
		this.btnMulti = new ElysiumGuiButton(this, this.menuX, this.menuY
				+ this.btnHeight + this.margin, this.menuWidth, this.btnHeight,
				StatCollector.translateToLocal("menu.multiplayer"));
		this.subs.add(this.btnMulti);
		this.btnMods = new ElysiumGuiButton(this, this.menuX, this.menuY
				+ (this.btnHeight + this.margin) * 2, this.menuWidth,
				this.btnHeight, "Mods");
		this.subs.add(this.btnMods);
		this.btnOptions = new ElysiumGuiButton(this, this.menuX, this.menuY
				+ (this.btnHeight + this.margin) * 3, this.menuWidth,
				this.btnHeight, StatCollector.translateToLocal("menu.options"));
		this.subs.add(this.btnOptions);
		this.btnQuit = new ElysiumGuiButton(this, this.menuX, this.menuY
				+ this.subHeight - this.btnHeight, this.menuWidth,
				this.btnHeight, StatCollector.translateToLocal("menu.quit"));
		this.subs.add(this.btnQuit);

		if (this.subGui != null)
			this.subGui.setBounds(this.subX, this.subY, this.subWidth,
					this.subHeight);
	}

	@Override
	public void update(int mouseX, int mouseY, boolean pressed) {
		super.update(mouseX, mouseY, pressed);
		if (this.subGui != null)
			this.subGui.update(mouseX, mouseY, pressed);

		if (this.btnSingle.wasClicked())
//			 this.setSubGui(new ElysiumGuiSelectWorld(this, this.subX,
//			 this.subY, this.subWidth, this.subHeight));
			Minecraft.getMinecraft().displayGuiScreen(
					new GuiSelectWorld(ElysiumGuiEmulator.instance));
		if (this.btnMulti.wasClicked())
//			 this.setSubGui(new ElysiumGuiMultiplayer(this, this.subX,
//			 this.subY, this.subWidth, this.subHeight));
			Minecraft.getMinecraft().displayGuiScreen(
					new GuiMultiplayer(ElysiumGuiEmulator.instance));
		if (this.btnMods.wasClicked())
			Minecraft.getMinecraft().displayGuiScreen(
					new GuiModList(ElysiumGuiEmulator.instance));
		if (this.btnOptions.wasClicked())
			Minecraft.getMinecraft().displayGuiScreen(
					new GuiOptions(ElysiumGuiEmulator.instance, Minecraft
							.getMinecraft().gameSettings));
		if (this.btnQuit.wasClicked())
			Minecraft.getMinecraft().shutdown();
		
//		if(mouseX >= 10 && mouseX <= 10 + this.width/7 && mouseY >= 3 * this.height / 5 && mouseY <= 3 * this.height / 5 + this.width/7)
//			this.hoverPatreon = true;
//		else
//			this.hoverPatreon = false;
	}

	FontRenderer fontRendererObj = FMLClientHandler.instance().getClient().fontRenderer;
	
	@Override
	public void render(float partialTick)
	{
		RenderingHelper.setColor(1F, 1F, 1F, 1F);
		RenderingHelper.bindTexture(this.imgBackground);
		RenderingHelper.Gui.drawTexturedRect(0, 0, this.width, this.height, 0F, 0F, 1F, 1F);
		RenderingHelper.bindTexture(this.imgTitle);

		
		glPushMatrix();
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
			RenderingHelper.Gui.drawTexturedRect(this.titleX, this.titleY, this.titleWidth, this.titleHeight, 0F, 0F, 1F, 1F);
		glDisable(GL_BLEND);
		glPopMatrix();
		
		RenderingHelper.setColor(0F, 0F, 0F, 0.75F);
		super.render(partialTick);
		if (this.subGui != null)
			this.subGui.render(partialTick);
		
//		if(!hoverPatreon)
//		{
//			RenderingHelper.bindTexture(this.imgPatreon);
//		} else {
//			RenderingHelper.bindTexture(this.imgPatreonHover);
//		}
//		if(Elysium.isPatreonButton)
//			RenderingHelper.Gui.drawTexturedRect(10, (int) (3 * this.height / 5), (int) (this.width / 8), (int) (this.width / 8), 0F, 0F, 1F, 1F);

        List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));
        for (int i = 0; i < brandings.size(); i++)
        {
            String brd = brandings.get(i);
            if (!Strings.isNullOrEmpty(brd))
            {
                fontRendererObj.drawStringWithShadow(brd, this.width - this.fontRendererObj.getStringWidth(brd), this.height - ( 10 + (i+2) * (this.fontRendererObj.FONT_HEIGHT + 1)), 16777215);
            }
        }
        String s1 = "Copyright Mojang AB. Do not distribute!";
        this.fontRendererObj.drawStringWithShadow(s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);

	}

	@Override
	public void close() {
		super.close();
		if (this.subGui != null)
			this.subGui.close();
	}
}
