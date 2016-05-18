package T145.magistics.items.relics;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import T145.magistics.Magistics;

public class ItemDawnstone extends Item {
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
		if (world.getWorldTime() % globalTime >= 6000L) {
			long time = world.getWorldTime() + globalTime;
			long timeDawn = time - (time % globalTime);

			for (int j = 0; j < MinecraftServer.getServer().worldServers.length; j++) {
				MinecraftServer.getServer().worldServers[j].setWorldTime(timeDawn);
			}

			--stack.stackSize;
			world.playSoundAtEntity(player, "thaumcraft.recover", 1F, 1F);
		}

		return super.onItemRightClick(stack, world, player);
	}
}