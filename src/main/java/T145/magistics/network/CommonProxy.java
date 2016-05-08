package T145.magistics.network;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import T145.magistics.blocks.BlockCrystalStorage;
import T145.magistics.blocks.BlockCrystalStorageItem;
import T145.magistics.blocks.BlockCrystalStorageStructureItem;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IGuiHandler {
	public static Block basicCrystalStorage = new BlockCrystalStorage("basic_crystal", "magistics:decor/basic");
	public static Block brickCrystalStorage = new BlockCrystalStorage("brick_crystal", "magistics:decor/brick");
	public static Block plateCrystalStorage = new BlockCrystalStorage("plate_crystal", "magistics:decor/plate");
	public static Block platformCrystalStorage = new BlockCrystalStorage("platform_crystal", "magistics:decor/platform");
	public static Block shieldCrystalStorage = new BlockCrystalStorage("shield_crystal", "magistics:decor/shield");
	public static Block structureCrystalStorage = new BlockCrystalStorage("structure_crystal", "magistics:decor/structure", true);

	public static Block lightEngineeringCrystal = new BlockCrystalStorage("light_engineering_crystal", "magistics:decor/brick_light");
	public static Block darkEngineeringCrystal = new BlockCrystalStorage("dark_engineering_crystal", "magistics:decor/brick_dark");

	public abstract void registerRenderInformation();

	public void registerObjectInformation() {
		GameRegistry.registerBlock(basicCrystalStorage, BlockCrystalStorageItem.class, basicCrystalStorage.getUnlocalizedName());
		GameRegistry.registerBlock(brickCrystalStorage, BlockCrystalStorageItem.class, brickCrystalStorage.getUnlocalizedName());
		GameRegistry.registerBlock(plateCrystalStorage, BlockCrystalStorageItem.class, plateCrystalStorage.getUnlocalizedName());
		GameRegistry.registerBlock(platformCrystalStorage, BlockCrystalStorageItem.class, platformCrystalStorage.getUnlocalizedName());
		GameRegistry.registerBlock(shieldCrystalStorage, BlockCrystalStorageItem.class, shieldCrystalStorage.getUnlocalizedName());
		GameRegistry.registerBlock(structureCrystalStorage, BlockCrystalStorageStructureItem.class, structureCrystalStorage.getUnlocalizedName());

		GameRegistry.registerBlock(lightEngineeringCrystal, BlockCrystalStorageItem.class, lightEngineeringCrystal.getUnlocalizedName());
		GameRegistry.registerBlock(darkEngineeringCrystal, BlockCrystalStorageItem.class, darkEngineeringCrystal.getUnlocalizedName());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}