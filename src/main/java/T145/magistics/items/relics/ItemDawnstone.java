package T145.magistics.items.relics;

import T145.magistics.Magistics;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ItemDawnstone extends Item {
	public static final Item INSTANCE = new ItemDawnstone();

	private static final long globalTime = 24000L;

	public ItemDawnstone() {
		setCreativeTab(Magistics.tabMagistics);
		setMaxStackSize(1);
		setTextureName("magistics:dawnstone");
		setUnlocalizedName("dawnstone");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			--stack.stackSize;
		}

		if (world.getWorldTime() % globalTime >= 6000L) {
			long time = world.getWorldTime() + globalTime;
			long timeDawn = time - (time % globalTime);

			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; j++) {
				MinecraftServer.getServer().worldServers[j].setWorldTime(timeDawn);
			}

			world.playSoundAtEntity(player, "thaumcraft.recover", 1F, 1F);
		}

		return stack;
	}
}