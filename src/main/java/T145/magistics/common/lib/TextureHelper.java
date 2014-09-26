package T145.magistics.common.lib;

import java.util.Map;

import net.minecraft.util.ResourceLocation;
import T145.magistics.common.blocks.BlockChestHungryMetal;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public class TextureHelper {
	public static Map<BlockChestHungryMetal.Types, ResourceLocation> ironChestTextures;

	static {
		Builder<BlockChestHungryMetal.Types, ResourceLocation> builder = ImmutableMap.<BlockChestHungryMetal.Types, ResourceLocation> builder();
		for (BlockChestHungryMetal.Types type : BlockChestHungryMetal.Types.values())
			builder.put(type, new ResourceLocation("magistics", "textures/models/chest_hungry/" + type.name() + ".png"));
		ironChestTextures = builder.build();
	}
}