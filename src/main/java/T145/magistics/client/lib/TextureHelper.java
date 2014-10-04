package T145.magistics.client.lib;

import java.util.Map;

import net.minecraft.util.ResourceLocation;
import T145.magistics.common.blocks.BlockChestHungryMetal;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public class TextureHelper {
	public static String connected_suffix[] = { "", "1_d", "1_u", "1_l", "1_r", "2_h", "2_v", "2_dl", "2_dr", "2_ul", "2_ur", "3_d", "3_u", "3_l", "3_r", "4" };
	public static Map<BlockChestHungryMetal.Types, ResourceLocation> ironChestTextures;

	static {
		Builder<BlockChestHungryMetal.Types, ResourceLocation> builder = ImmutableMap.<BlockChestHungryMetal.Types, ResourceLocation> builder();
		for (BlockChestHungryMetal.Types type : BlockChestHungryMetal.Types.values())
			builder.put(type, new ResourceLocation("magistics", "textures/models/chest_hungry/" + type.name() + ".png"));
		ironChestTextures = builder.build();
	}
}