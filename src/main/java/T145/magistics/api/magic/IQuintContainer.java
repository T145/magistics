package T145.magistics.api.magic;

public interface IQuintContainer extends IQuintManager {

	public float getMaxQuints();

	public float getQuints();

	public float getDisplayQuints();

	public void setQuints(float amount);
}