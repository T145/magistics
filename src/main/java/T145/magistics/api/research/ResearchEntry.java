package T145.magistics.api.research;

import java.util.ArrayList;
import java.util.List;

public class ResearchEntry {

	private final List<ResearchEntry> dependencies;
	private final ResearchType type;
	private final String text;
	private final short x;
	private final short y;

	public ResearchEntry(ResearchType type, String text, short x, short y, ResearchEntry... dependencies) {
		this.type = type;
		this.text = text;
		this.x = x;
		this.y = y;
		this.dependencies = new ArrayList<ResearchEntry>();

		if (dependencies.length > 0) {

			// will be O(1) a majority of the time, as most ppl only add one dependency
			for (ResearchEntry dependency : dependencies) {
				this.dependencies.add(dependency);
			}
		}
	}

	public List<ResearchEntry> getDependencies() {
		return dependencies;
	}

	public ResearchType getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public short getX() {
		return x;
	}

	public short getY() {
		return y;
	}
}