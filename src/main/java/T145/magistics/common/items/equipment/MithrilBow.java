package T145.magistics.common.items.equipment;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import T145.magistics.common.config.ConfigObjects;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MithrilBow extends ItemBow {
	public static String[] bowPullIconNameArray = new String[] { "pulling_0", "pulling_1", "pulling_2" };;
	private IIcon[] iconArray;

	public MithrilBow() {
		maxStackSize = 1;
		setMaxDamage(384);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (usingItem == null)
			return itemIcon;
		int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;
		if (ticksInUse > 17)
			return iconArray[2];
		if (ticksInUse > 13)
			return iconArray[1];
		if (ticksInUse > 0)
			return iconArray[0];
		return itemIcon;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		int j = getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post((Event) event);
		if (event.isCanceled())
			return;
		j = event.charge;
		boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		if (flag || par3EntityPlayer.inventory.hasItem(Items.arrow)) {
			float f = j / 20F;
			f = (f * f + f * 2F) / 3F;
			if (f < 0.1)
				return;
			if (f > 1F)
				f = 1F;
			EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, 10F);
			if (f == 1F)
				entityarrow.setIsCritical(true);
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			if (k > 0)
				entityarrow.setDamage(entityarrow.getDamage() + k * 0.5 + 0.5);
			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
			if (l > 0)
				entityarrow.setKnockbackStrength(l);
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
				entityarrow.setFire(100);
			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1F, 1F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (flag)
				entityarrow.canBePickedUp = 2;
			else
				par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
			if (!par2World.isRemote)
				par2World.spawnEntityInWorld(entityarrow);
		}
	}

	@Override
	public int getItemEnchantability() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r) {
		itemIcon = r.registerIcon("magistics:mithril/bow_standby");
		iconArray = new IIcon[MithrilBow.bowPullIconNameArray.length];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = r.registerIcon("magistics:mithril/bow_" + MithrilBow.bowPullIconNameArray[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(ConfigObjects.itemMithrilBow);
		stack.addEnchantment(Enchantment.sharpness, 8);
		stack.addEnchantment(Enchantment.infinity, 1);
		list.add(stack);
	}
}