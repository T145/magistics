package hu.hundevelopers.elysium.client.gui;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.world.storage.SaveFormatComparator;

public class ElysiumGuiSelectWorld extends ElysiumGuiMainMenuSub {
	public List<SaveFormatComparator> saves;
	
	public ElysiumGuiSelectWorld(ElysiumGui parent, int x, int y, int width, int height) {
		super(parent, x, y, width, height);
		this.loadSaves();
	}
	
	public void loadSaves() {
		try {
			this.saves = Minecraft.getMinecraft().getSaveLoader().getSaveList();
			Collections.sort(this.saves);
		} catch (AnvilConverterException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setBounds(int x, int y, int height, int width) {
		super.setBounds(x, y, width, height);
		
	}
}
