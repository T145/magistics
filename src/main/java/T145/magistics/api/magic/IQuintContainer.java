package T145.magistics.api.magic;

import net.minecraft.util.EnumFacing;

public interface IQuintContainer extends IQuintHandler {

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
}