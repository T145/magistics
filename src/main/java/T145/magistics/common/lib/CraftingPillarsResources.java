package T145.magistics.common.lib;

import java.util.Calendar;

public class CraftingPillarsResources {
	public static boolean isWinterTime() {
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.DECEMBER);
		b.set(Calendar.DAY_OF_MONTH, 1);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		e.set(Calendar.MONTH, Calendar.JANUARY);
		e.set(Calendar.DAY_OF_MONTH, 15);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}

	public static boolean isEasterTime() {
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.APRIL);
		b.set(Calendar.DAY_OF_MONTH, 19);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.MONTH, Calendar.APRIL);
		e.set(Calendar.DAY_OF_MONTH, 25);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}

	public static boolean isHalloweenTime() {
		Calendar c = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH, Calendar.OCTOBER);
		b.set(Calendar.DAY_OF_MONTH, 24);
		b.set(Calendar.HOUR_OF_DAY, 0);
		b.set(Calendar.MINUTE, 0);
		b.set(Calendar.MILLISECOND, 0);
		Calendar e = Calendar.getInstance();
		e.set(Calendar.MONTH, Calendar.NOVEMBER);
		e.set(Calendar.DAY_OF_MONTH, 7);
		e.set(Calendar.HOUR_OF_DAY, 0);
		e.set(Calendar.MINUTE, 0);
		e.set(Calendar.MILLISECOND, 0);
		return c.after(b) && c.before(e);
	}

	public static boolean isValentineTime() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) == Calendar.FEBRUARY && c.get(Calendar.DAY_OF_MONTH) == 14;
	}
}