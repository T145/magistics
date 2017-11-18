package T145.magistics.api.magic;

public interface IQuintContainer extends IQuintProxy {

	/**
	 * @return The amount of quintessence in this container.
	 */
	float getQuints();

	/**
	 * Set amount of quintessence in this container.
	 */
	void setQuints(float quints);

	/**
	 * @return Quintessence capacity of this IQuintContainer.
	 */
	float getCapacity();
}