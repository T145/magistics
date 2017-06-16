package T145.magistics.api.variants.blocks;

import javax.annotation.Nullable;

import T145.magistics.Magistics;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum EnumCrystal implements IStringSerializable {

	AIR, FIRE, WATER, EARTH, MAGIC, VOID;

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Nullable
	public ResourceLocation getTexture() {
		switch (this) {
		case AIR:
			return new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystaly.png");
		case FIRE:
			return new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalr.png");
		case WATER:
			return new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalb.png");
		case EARTH:
			return new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystalg.png");
		case MAGIC:
			return new ResourceLocation(Magistics.MODID, "textures/models/crystals/crystal.png");
		default:
			return null;
		}
	}

	public float getColor() {
		return this == VOID ? 0.2F : 1F;
	}
}