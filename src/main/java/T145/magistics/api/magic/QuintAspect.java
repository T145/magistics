package T145.magistics.api.magic;

import net.minecraft.util.IStringSerializable;

public enum QuintAspect implements IStringSerializable {

	AIR("aer", 16777086, "e", 1), EARTH("terra", 5685248, "2", 1), FIRE("ignis", 16734721, "c", 1), WATER("aqua", 3986684, "3", 1), VOID("vacuos", 8947848, 771), MAGIC("praecantatio", 13566207);

	private final String tag;
	private final int color;
	private final int blend;
	private String chatColor;

	QuintAspect(String tag, int color, int blend) {
		this.tag = tag;
		this.color = color;
		this.blend = blend;
	}

	QuintAspect(String tag, int color) {
		this(tag, color, 1);
	}

	QuintAspect(String tag, int color, String chatColor, int blend) {
		this(tag, color, blend);
		setChatColor(chatColor);
	}

	public String getTag() {
		return tag;
	}

	public int getColor() {
		return color;
	}

	public int getBlend() {
		return blend;
	}

	public String getChatColor() {
		return chatColor;
	}

	public void setChatColor(String chatcolor) {
		this.chatColor = chatcolor;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}