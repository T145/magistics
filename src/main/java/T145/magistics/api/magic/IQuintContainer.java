package T145.magistics.api.magic;

import net.minecraft.util.EnumFacing;

public interface IQuintContainer {

	static final int MAX_SUCTION = 50;

	/**
	 * @return Whether or not this container can be connect to at the given side.
	 */
	boolean canConnectAtSide(EnumFacing side);

	/**
	 * @return The amount of quintessence in this container.
	 */
	float getQuints();

	/**
	 * Set the amount of quintessence in this container.
	 */
	void setQuints(float quints);

	/**
	 * @return Quintessene capacity of this IQuintContainer.
	 */
	float getCapacity();

	/**
	 * @return The total pressure of this IQuintContainer; the higher the suction,
	 *         the greater priority for handling quints in general this
	 *         IQuintContainer has.
	 */
	int getSuction();

	/**
	 * Sets the vis and miasma suction for this IQuintContainer
	 *
	 * @param suction
	 *            The suction value to set.
	 */
	void setSuction(int suction);
}