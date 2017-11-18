package T145.magistics.api.magic;

public enum FillPriority {

	LOW(-1), MEDIUM(0), HIGH(1);

	private final int value;

	FillPriority(int value) {
		this.value = value;
	}
}