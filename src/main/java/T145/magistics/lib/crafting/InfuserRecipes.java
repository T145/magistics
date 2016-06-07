package T145.magistics.lib.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class InfuserRecipes {
	private static final InfuserRecipes infusingBase = new InfuserRecipes();
	public ArrayList resultList = new ArrayList();
	public ArrayList componentList = new ArrayList();
	public ArrayList costList = new ArrayList();
	public ArrayList darkList = new ArrayList();

	public static final InfuserRecipes infusing() {
		return infusingBase;
	}

	public void addInfusing(ItemStack var1, int var2, ItemStack[] var3) {
		resultList.add(var1);
		componentList.add(var3);
		costList.add(Integer.valueOf(var2));
		darkList.add(Boolean.valueOf(false));
	}

	public void addInfusing(ItemStack var1, int var2, ItemStack[] var3, boolean var4) {
		resultList.add(var1);
		componentList.add(var3);
		costList.add(Integer.valueOf(var2));
		darkList.add(Boolean.valueOf(var4));
	}

	public ItemStack getInfusingResult(Object[] var1) {
		try {
			int var2 = findEntry(var1);
			ItemStack var3 = (ItemStack) resultList.get(var2);
			return !((Boolean) darkList.get(var2)).booleanValue() ? var3 : null;
		} catch (IndexOutOfBoundsException var4) {
			return null;
		}
	}

	public ItemStack getInfusingResult(Object[] var1, boolean var2) {
		try {
			int var3 = findEntry(var1);
			ItemStack var4 = (ItemStack) resultList.get(var3);
			return ((Boolean) darkList.get(var3)).booleanValue() == var2 ? var4 : null;
		} catch (IndexOutOfBoundsException var5) {
			return null;
		}
	}

	public int getInfusingCost(ItemStack var1) {
		for (int var2 = 0; var2 < resultList.size(); ++var2) {
			if (((ItemStack) resultList.get(var2)).isItemEqual(var1) && !((Boolean) darkList.get(var2)).booleanValue()) {
				return ((Integer) costList.get(var2)).intValue();
			}
		}

		return -1;
	}

	public int getInfusingCost(ItemStack var1, boolean var2) {
		for (int var3 = 0; var3 < resultList.size(); ++var3) {
			if (((ItemStack) resultList.get(var3)).isItemEqual(var1) && ((Boolean) darkList.get(var3)).booleanValue() == var2) {
				return ((Integer) costList.get(var3)).intValue();
			}
		}

		return -1;
	}

	public boolean isComponentFor(ItemStack var1, ItemStack var2, boolean var3) {
		for (int var4 = 0; var4 < resultList.size(); ++var4) {
			if (((ItemStack) resultList.get(var4)).isItemEqual(var2)
					&& ((Boolean) darkList.get(var4)).booleanValue() == var3) {
				ItemStack[] var5 = (ItemStack[]) componentList.get(var4);
				int var6 = var5.length;

				for (int var7 = 0; var7 < var6; ++var7) {
					ItemStack var8 = var5[var7];

					if (var1.isItemEqual(var8) || var8.getItemDamage() == -1 && var8.getItem() == var1.getItem()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public ItemStack[] getInfusingComponents(ItemStack var1, boolean var2) {
		for (int var3 = 0; var3 < resultList.size(); ++var3) {
			if (((ItemStack) resultList.get(var3)).isItemEqual(var1)
					&& ((Boolean) darkList.get(var3)).booleanValue() == var2) {
				return (ItemStack[]) componentList.get(var3);
			}
		}

		return null;
	}

	public ItemStack[] getInfusingComponents(ItemStack var1) {
		for (int var2 = 0; var2 < resultList.size(); ++var2) {
			if (((ItemStack) resultList.get(var2)).isItemEqual(var1)
					&& !((Boolean) darkList.get(var2)).booleanValue()) {
				return (ItemStack[]) componentList.get(var2);
			}
		}

		return null;
	}

	public int getInfusingCost(Object[] var1, boolean var2) {
		try {
			int var3 = findEntry(var1);
			int var4 = ((Integer) costList.get(var3)).intValue();
			return ((Boolean) darkList.get(var3)).booleanValue() == var2 ? var4
					: 0;
		} catch (IndexOutOfBoundsException var5) {
			return 0;
		}
	}

	public int getInfusingCost(Object[] var1) {
		try {
			int var2 = findEntry(var1);
			int var3 = ((Integer) costList.get(var2)).intValue();
			return !((Boolean) darkList.get(var2)).booleanValue() ? var3
					: 0;
		} catch (IndexOutOfBoundsException var4) {
			return 0;
		}
	}

	private int findEntry(Object[] var1) {
		int var2 = 0;

		while (var2 < componentList.size()) {
			ItemStack[] var3 = (ItemStack[]) componentList.get(var2);
			ArrayList var4 = new ArrayList();
			int var5 = var3.length;
			Object[] var6 = var1;
			int var7 = var1.length;
			int var8 = 0;

			while (true) {
				if (var8 < var7) {
					Object var9 = var6[var8];
					boolean var10 = false;

					if (var5 == 0) {
						return -1;
					}

					for (int var11 = 0; var11 < var3.length; ++var11) {
						if (!var4.contains(Integer.valueOf(var11))
								&& ((ItemStack) var9).getItem() == var3[var11]
										.getItem()
										&& (((ItemStack) var9).getItemDamage() == var3[var11]
												.getItemDamage() || var3[var11]
														.getItemDamage() == -1)) {
							--var5;
							var4.add(Integer.valueOf(var11));
							var10 = true;
							break;
						}
					}

					if (var10) {
						++var8;
						continue;
					}
				} else if (var5 == 0) {
					return var2;
				}

				++var2;
				break;
			}
		}

		return -1;
	}
}