package T145.magistics.client.render.models.blocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

import T145.magistics.Magistics;
import T145.magistics.blocks.cosmetic.BlockCrystal;
import T145.magistics.client.lib.obj.MeshLoader;
import T145.magistics.client.lib.obj.MeshModel;
import T145.magistics.client.lib.obj.MeshPart;
import codechicken.lib.vec.Vector3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.property.IExtendedBlockState;

public class ModelCrystal implements IBakedModel {

	private final ResourceLocation model = new ResourceLocation(Magistics.MODID, "models/obj/crystal.obj");
	private final TextureAtlasSprite sprite;
	private MeshModel sourceMesh;

	public ModelCrystal(TextureAtlasSprite sprite) {
		this.sprite = sprite;

		try {
			sourceMesh = new MeshLoader().loadFromResource(this.model);

			for (MeshPart part : sourceMesh.parts) {
				part.setTintIndex(0);
			}
		} catch (IOException err) {
			Magistics.LOGGER.catching(err);
		}
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (side == null && state instanceof IExtendedBlockState) {
			ArrayList<BakedQuad> ret = new ArrayList<BakedQuad>();
			IExtendedBlockState es = (IExtendedBlockState)state;
			int m = ((BlockCrystal) state.getBlock()).getGrowth(state) + 1;
			MeshModel mm = sourceMesh.clone();

			if (!(es.getValue(BlockCrystal.UP) && es.getValue(BlockCrystal.DOWN) && es.getValue(BlockCrystal.EAST) && es.getValue(BlockCrystal.WEST) && es.getValue(BlockCrystal.NORTH) && es.getValue(BlockCrystal.SOUTH))) {
				List<Integer> c;
				MeshModel mod;

				if (es.getValue(BlockCrystal.UP)) {
					c = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
					Collections.shuffle(c, new Random(rand));
					mm.parts.clear();

					for (int a = 0; a < m; ++a) {
						mm.parts.add(sourceMesh.parts.get(c.get(a)));
					}

					mod = mm.clone();
					mod.rotate(Math.toRadians(180.0), new Vector3(1.0, 0.0, 0.0), new Vector3(0.0, 1.0, 1.0));

					for (BakedQuad quad : mod.bakeModel(getParticleTexture())) {
						ret.add(quad);
					}
				}

				if (es.getValue(BlockCrystal.DOWN)) {
					c = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
					Collections.shuffle(c, new Random(rand + 5));
					mm.parts.clear();

					for (int a = 0; a < m; ++a) {
						mm.parts.add(sourceMesh.parts.get(c.get(a)));
					}

					for (BakedQuad quad : mm.bakeModel(getParticleTexture())) {
						ret.add(quad);
					}
				}

				if (es.getValue(BlockCrystal.EAST)) {
					c = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
					Collections.shuffle(c, new Random(rand + 10));
					mm.parts.clear();

					for (int a = 0; a < m; ++a) {
						mm.parts.add(sourceMesh.parts.get(c.get(a)));
					}

					mod = mm.clone();
					mod.rotate(Math.toRadians(90.0), new Vector3(1.0, 0.0, 0.0), new Vector3(0.0, 0.0, 0.0));
					mod.rotate(Math.toRadians(270.0), new Vector3(0.0, 1.0, 0.0), new Vector3(1.0, 1.0, 0.0));

					for (BakedQuad quad : mod.bakeModel(getParticleTexture())) {
						ret.add(quad);
					}
				}

				if (es.getValue(BlockCrystal.WEST)) {
					c = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
					Collections.shuffle(c, new Random(rand + 15));
					mm.parts.clear();

					for (int a = 0; a < m; ++a) {
						mm.parts.add(sourceMesh.parts.get(c.get(a)));
					}

					mod = mm.clone();
					mod.rotate(Math.toRadians(90.0), new Vector3(1.0, 0.0, 0.0), new Vector3(0.0, 0.0, 0.0));
					mod.rotate(Math.toRadians(90.0), new Vector3(0.0, 1.0, 0.0), new Vector3(0.0, 1.0, 1.0));

					for (BakedQuad quad : mod.bakeModel(getParticleTexture())) {
						ret.add(quad);
					}
				}

				if (es.getValue(BlockCrystal.NORTH)) {
					c = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
					Collections.shuffle(c, new Random(rand + 20));
					mm.parts.clear();

					for (int a = 0; a < m; ++a) {
						mm.parts.add(sourceMesh.parts.get(c.get(a)));
					}

					mod = mm.clone();
					mod.rotate(Math.toRadians(90.0), new Vector3(1.0, 0.0, 0.0), new Vector3(0.0, 1.0, 0.0));

					for (BakedQuad quad : mod.bakeModel(getParticleTexture())) {
						ret.add(quad);
					}
				}

				if (es.getValue(BlockCrystal.SOUTH)) {
					c = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
					Collections.shuffle(c, new Random(rand + 25));
					mm.parts.clear();

					for (int a = 0; a < m; ++a) {
						mm.parts.add(sourceMesh.parts.get(c.get(a)));
					}

					MeshModel mod2 = mm.clone();
					mod2.rotate(Math.toRadians(90.0), new Vector3(1.0, 0.0, 0.0), new Vector3(0.0, 0.0, 0.0));
					mod2.rotate(Math.toRadians(180.0), new Vector3(0.0, 1.0, 0.0), new Vector3(1.0, 1.0, 1.0));

					for (BakedQuad quad : mod2.bakeModel(getParticleTexture())) {
						ret.add(quad);
					}
				}
			}

			return ret;
		}

		return ImmutableList.of();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return sprite;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}
}