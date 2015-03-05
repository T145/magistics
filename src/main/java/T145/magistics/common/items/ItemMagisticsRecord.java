package T145.magistics.common.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.BlockJukebox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMagisticsRecord extends ItemRecord {
	private static Map records = new HashMap();
	public String recordName;

	public ItemMagisticsRecord(String name) {
		super(name);

		recordName = name;
		maxStackSize = 1;

		records.put(recordName, this);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (world.getBlock(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0) {
			if (world.isRemote)
				return true;
			else {
				((BlockJukebox) Blocks.jukebox).func_149926_b(world, x, y, z, itemStack);
				world.playAuxSFXAtEntity((EntityPlayer) null, 1005, x, y, z, Item.getIdFromItem(this));

				--itemStack.stackSize;
				return true;
			}
		} else
			return false;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4) {
		list.add(getRecordNameLocal());
	}

	@Override
	public String getRecordNameLocal() {
		return StatCollector.translateToLocal(getUnlocalizedName() + ".desc");
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return EnumRarity.rare;
	}

	public static ItemMagisticsRecord getRecord(String sound) {
		return (ItemMagisticsRecord) records.get(sound);
	}

	@Override
	public ResourceLocation getRecordResource(String soundName) {
		soundName = recordName;
		return new ResourceLocation("magistics:" + soundName);
	}
}