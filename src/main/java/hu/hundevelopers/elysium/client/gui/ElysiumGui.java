package hu.hundevelopers.elysium.client.gui;

import java.util.ArrayList;
import java.util.List;

public class ElysiumGui {
	public ElysiumGui parent;
	public int x, y, width, height;
	public List<ElysiumGui> subs;
	
	public ElysiumGui(ElysiumGui parent) {
		this(parent, 0, 0, 0, 0);
	}
	
	public ElysiumGui(ElysiumGui parent, int x, int y, int width, int height) {
		this.parent = parent;
		this.subs = new ArrayList<ElysiumGui>();
		this.setBounds(x, y, width, height);
	}
	
	public void onMouseClicked(int x, int y, int btn) {
		for(ElysiumGui sub : this.subs)
			sub.onMouseClicked(x, y, btn);
	}
	
	public void onKeyTyped(char c, int i) {
		for(ElysiumGui sub : this.subs)
			sub.onKeyTyped(c, i);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.subs.clear();
	}
	
	public boolean inBounds(int x, int y) {
		return this.x <= x && this.x+this.width >= x && this.y <= y && this.y+this.height >= y;
	}
	
	public void update(int mouseX, int mouseY, boolean pressed) {
		for(ElysiumGui sub : this.subs)
			sub.update(mouseX, mouseY, pressed);
	}
	
	public void render(float partialTick) {
		for(ElysiumGui sub : this.subs)
			sub.render(partialTick);
	}
	
	public void close() {
		for(ElysiumGui sub : this.subs)
			sub.close();
	}
	
	public boolean pauseGame() {
		return true;
	}
}
