package T145.magistics.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

	public static List<Entity> entityRegistry = new ArrayList<Entity>();

	public static void init() {}

	@SideOnly(Side.CLIENT)
	public static void initClient() {}
}