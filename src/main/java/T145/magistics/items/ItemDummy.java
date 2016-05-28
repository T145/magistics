package T145.magistics.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDummy extends Item {
	public static final Item INFERNAL_FURNACE = new ItemDummy("magistics:waila/infernal_furnace", "arcane_furnace");

	@SideOnly(Side.CLIENT)
	public IIcon icon;

	private String texture;

	public ItemDummy(String texturePath, String name) {
		setUnlocalizedName(name);
		setCreativeTab(null);
		setMaxStackSize(1);

		texture = texturePath;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icon = register.registerIcon(texture);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int metadata) {
		return icon;
	}
}