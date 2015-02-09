package T145.magistics.api.sentry;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLLog;

public class SentryBehaviors {
	
	/** Registry for all sentry behaviors. */
	public static Map<String, ISentryBehaviorItem> sentryBehaviorRegistry = new HashMap<String, ISentryBehaviorItem>();

	/**
	 * Use this method to register a new behavior for an item/block.
	 * @param item - the item of the projectile or weapon
	 * @param behavior - The behavior for the projectile or weapon - must extend SentryDefaultProjectile
	 */	
	public static void add(Item item, Object behavior)
	{
		if(behavior instanceof ISentryBehaviorItem)
		{
			sentryBehaviorRegistry.put(item.getUnlocalizedName(), (ISentryBehaviorItem)behavior);
		} else {
			FMLLog.warning("[CraftingPillar]: Couldn't register " + behavior.toString() + "! It has to implement IBehaviorSentryItem!");
		}
	}

	/**
	 * Gets the behavior for the item
	 * @param item - item of the weapon/projectile
	 * @return - behavior for the weapon/projectile
	 */
	public static ISentryBehaviorItem get(Item item)
	{
		return (ISentryBehaviorItem) sentryBehaviorRegistry.get(item.getUnlocalizedName());
	}
}
