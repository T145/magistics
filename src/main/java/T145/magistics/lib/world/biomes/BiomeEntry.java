package T145.magistics.lib.world.biomes;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

public class BiomeEntry {

	private final Biome biome;
	private final boolean highAura;
	private final boolean negativeAura;
	private boolean airChance = false;
	private boolean fireChance = false;
	private boolean waterChance = false;
	private boolean earthChance = false;
	private boolean visChance = false;
	private boolean taintChance = false;

	public BiomeEntry(Biome biome, boolean highAura, boolean negativeAura) {
		this.biome = biome;
		this.highAura = highAura;
		this.negativeAura = negativeAura;
	}

	public boolean hasHighAura() {
		return highAura;
	}

	public boolean hasNegativeAura() {
		return negativeAura;
	}

	
}