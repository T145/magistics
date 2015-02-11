package T145.magistics.common.blocks.craftingpillars.sentry;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import T145.magistics.api.sentry.ISentryBehaviorItem;
import T145.magistics.common.config.Log;

public class SentryBehaviors {
	public static Map<String, ISentryBehaviorItem> sentryBehaviorRegistry = new HashMap<String, ISentryBehaviorItem>();

	public static void add(Item item, Object behavior) {
		if (behavior instanceof ISentryBehaviorItem)
			sentryBehaviorRegistry.put(item.getUnlocalizedName(), (ISentryBehaviorItem) behavior);
		else
			Log.warn("Couldn't register " + behavior.toString() + "! It has to implement IBehaviorSentryItem!");
	}

	public static ISentryBehaviorItem get(Item item) {
		return (ISentryBehaviorItem) sentryBehaviorRegistry.get(item.getUnlocalizedName());
	}
}