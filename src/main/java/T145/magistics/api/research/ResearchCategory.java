package T145.magistics.api.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class ResearchCategory {

	public int minDisplayColumn;
	public int minDisplayRow;
	public int maxDisplayColumn;
	public int maxDisplayRow;

	private final String key;
	private final ResourceLocation icon;
	private final ResourceLocation background;
	private final List<ResearchEntry> entries;

	public ResearchCategory(String key, ResourceLocation icon, ResourceLocation background, ResearchEntry... entries) {
		this.key = key;
		this.icon = icon;
		this.background = background;
		this.entries = new ArrayList<ResearchEntry>();

		if (entries.length > 0) {
			for (ResearchEntry entry : entries) {
				this.entries.add(entry);
			}
		}
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return I18n.format("magistics.research_category" + key);
	}

	public ResourceLocation getIcon() {
		return icon;
	}

	public ResourceLocation getBackground() {
		return background;
	}
}