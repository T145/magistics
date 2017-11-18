package T145.magistics.api.magic;

public interface IQuintContainer extends IQuintProxy {

	/**
	 * @return The amount of quintessence in this container.
	 */
	int getQuints();

	/**
	 * Set amount of quintessence in this container.
	 */
	void setQuints(int quints);

	/**
	 * @return Quintessence capacity of this IQuintContainer.
	 */
	int getCapacity();
}