package T145.magistics.common.items.relics;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenEnd;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MithrilFlute extends Item {
	private static Random rand = new Random();
	private long lastPlay = 0L;

	public MithrilFlute() {
		setMaxDamage(super.maxStackSize = 1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer entity) {
		if (!world.isRemote && (System.currentTimeMillis() - lastPlay) / 1000L > getMaxItemUseDuration(is) / 20) {
			lastPlay = System.currentTimeMillis();
			world.playSoundAtEntity((Entity) entity, "mythril:flute", 1F, 1F);
			if (world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd) {
				if (isDragonAlive(world) >= 1)
					entity.addChatMessage(new ChatComponentText(LanguageRegistry.instance().getStringLocalization("flute.dragonExist." + MithrilFlute.rand.nextInt(4))));
			} else
				entity.addChatMessage(new ChatComponentText(LanguageRegistry.instance().getStringLocalization("flute.tips." + MithrilFlute.rand.nextInt(3))));
		}
		entity.setItemInUse(is, getMaxItemUseDuration(is));
		return is;
	}

	private int isDragonAlive(World world) {
		List list = world.loadedEntityList;
		int dragonNum = 0;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) instanceof EntityDragon)
				++dragonNum;
		return dragonNum;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer entity) {
		if (!world.isRemote && world.getWorldChunkManager().getBiomeGenAt(0, 0) instanceof BiomeGenEnd && isDragonAlive(world) < 1) {
			is.damageItem(2, (EntityLivingBase) entity);
			EntityDragon entitydragon = new EntityDragon(world);
			entitydragon.setLocationAndAngles(0, 128, 0, MithrilFlute.rand.nextFloat() * 360F, 0F);
			world.spawnEntityInWorld((Entity) entitydragon);
		}
		return is;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack item) {
		return 200;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.block;
	}
}