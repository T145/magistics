package T145.magistics.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.EntityUtils;
import T145.magistics.Magistics;

public class ItemCruelMask extends ItemArmor {
	public ItemCruelMask(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		setCreativeTab(Magistics.tabMagistics);
		setTextureName("magistics:cruel_mask");
		setUnlocalizedName("cruel_mask");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "magistics:textures/models/cruel_mask.png";
	}

	@Override
	public EnumRarity getRarity(ItemStack is) {
		return EnumRarity.rare;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		Entity entity = EntityUtils.getPointedEntity(world, player, 2D, 24D, 0.2F);

		if (entity != null && entity instanceof EntityLiving) {
			EntityLiving mob = (EntityLiving) entity;

			switch (world.rand.nextInt(4)) {
			case 0:
				mob.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 6));
				stack.damageItem(1, player);
				break;
			case 1:
				mob.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
				stack.damageItem(1, player);
				break;
			case 2:
				mob.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 6));
				stack.damageItem(1, player);
				break;
			case 3:
				mob.attackEntityFrom(DamageSource.causePlayerDamage(player), 1);
				stack.damageItem(1, player);
				break;
			}
		}
	}
}