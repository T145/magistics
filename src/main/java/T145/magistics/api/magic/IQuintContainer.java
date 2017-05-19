package T145.magistics.api.magic;

public interface IQuintContainer extends IQuintManager {

	float getMaxQuints();

	float getQuints();

	float getDisplayQuints();

	void setQuints(float amount);
}