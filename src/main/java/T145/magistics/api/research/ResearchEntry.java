package T145.magistics.api.research;

import java.util.List;

import net.minecraft.client.resources.I18n;

public class ResearchEntry {

	private final String key;
	private final String name;
	private final String title;
	private final List<ResearchEntry> parents;
	private final List<ResearchEntry> children;
	private final short displayX;
	private final short displayY;
	private final List<String> pages;
	private ResearchType type;

	public ResearchEntry(String key, String name, String title, List<ResearchEntry> parents, List<ResearchEntry> children, short displayX, short displayY, List<String> pages) {
		this.key = key;
		this.name = name;
		this.title = title;
		this.parents = parents;
		this.children = children;
		this.displayX = displayX;
		this.displayY = displayY;
		this.pages = pages;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getLocalizedName() {
		return I18n.format(name);
	}

	public String getTitle() {
		return title;
	}

	public String getLocalizedTitle() {
		return I18n.format(title);
	} 

	public List<ResearchEntry> getParents() {
		return parents;
	}

	public List<ResearchEntry> getChildren() {
		return children;
	}

	public short getDisplayX() {
		return displayX;
	}

	public short getDisplayY() {
		return displayY;
	}

	public List<String> getPages() {
		return pages;
	}

	public ResearchType getType() {
		return type;
	}

	public void setType(ResearchType type) {
		this.type = type;
	}

	public boolean hasType(ResearchType type) {
		return this.type == type;
	}

	public boolean isGeneric() {
		return this.type == null;
	}
}