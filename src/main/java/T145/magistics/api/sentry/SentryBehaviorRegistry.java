package T145.magistics.api.sentry;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class SentryBehaviorRegistry {
	public static Map<String, ISentryBehaviorItem> sentryBehaviorRegistry = new HashMap<String, ISentryBehaviorItem>();

	public static void addBehavior(Item item, ISentryBehaviorItem behavior) {
		sentryBehaviorRegistry.put(item.getUnlocalizedName(), behavior);
	}

	public static ISentryBehaviorItem getBehavior(Item item) {
		return sentryBehaviorRegistry.get(item.getUnlocalizedName());
	}
}