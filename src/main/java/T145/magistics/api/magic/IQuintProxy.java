package T145.magistics.api.magic;

import net.minecraft.util.EnumFacing;

// A quintessence proxy is not a tank necessarily;
// more like something that either just provides or consumes quints
public interface IQuintProxy {

	/**
	 * @return Whether or not this proxy can should be given fill priority.
	 */
	FillPriority getPriority();

	/**
	 * @return Whether or not this proxy can connect at the given side.
	 */
	boolean canConnectAtSide(EnumFacing side);

	/**
	 *
	 * @param amount
	 *            Quintessence amount attempting to fill this proxy.
	 * @param doFill
	 *            If false, the fill will only be simulated.
	 * @return Amount of quintessence that was accepted by the proxy.
	 */
	float fill(float amount, boolean doFill);

	/**
	 *
	 * @param amount
	 *            Maximum amount of quintessence to be removed from the proxy.
	 * @param doDrain
	 *            If false, the drain will only be simulated.
	 * @return Amount of quintessence that was removed from the proxy.
	 */
	float drain(float amount, boolean doDrain);
}