package T145.magistics.common.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.EntityUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCruelMask extends ItemArmor {
	public ItemCruelMask(ArmorMaterial m, int i, int j) {
		super(m, i, j);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:cruel_mask");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		return "magistics:textures/models/cruel_mask.png";
	}

	@Override
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is) {
		Entity e = EntityUtils.getPointedEntity(world, player, 2D, 24D, 0.2F);

		if (e != null && e instanceof EntityLiving) {
			EntityLiving creature = (EntityLiving) e;

			switch (world.rand.nextInt(4)) {
			case 0:
				creature.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 6));
				is.damageItem(1, player);
				break;
			case 1:
				creature.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
				is.damageItem(1, player);
				break;
			case 2:
				creature.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 6));
				is.damageItem(1, player);
				break;
			case 3:
				creature.attackEntityFrom(DamageSource.causePlayerDamage(player), 1);
				is.damageItem(1, player);
				break;
			}
		}
	}
}