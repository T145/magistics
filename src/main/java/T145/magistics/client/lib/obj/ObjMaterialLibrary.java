package T145.magistics.client.lib.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.vecmath.Vector3f;

import com.google.common.base.Charsets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class ObjMaterialLibrary extends Dictionary<String, ObjMaterial> {

	private static final Set<String> unknownCommands = new HashSet<String>();
	private final Dictionary<String, ObjMaterial> materialLibrary = new Hashtable<String, ObjMaterial>();
	private ObjMaterial currentMaterial;

	@Override
	public int size() {
		return materialLibrary.size();
	}

	@Override
	public boolean isEmpty() {
		return materialLibrary.isEmpty();
	}

	@Override
	public Enumeration<String> keys() {
		return materialLibrary.keys();
	}

	@Override
	public Enumeration<ObjMaterial> elements() {
		return materialLibrary.elements();
	}

	@Override
	public ObjMaterial get(Object key) {
		return materialLibrary.get(key);
	}

	@Override
	public ObjMaterial put(String key, ObjMaterial value) {
		return materialLibrary.put(key, value);
	}

	@Override
	public ObjMaterial remove(Object key) {
		return materialLibrary.remove(key);
	}

	public void loadFromStream(ResourceLocation loc) throws IOException {
		String currentLine;
		IResource res = Minecraft.getMinecraft().getResourceManager().getResource(loc);
		InputStreamReader lineStream = new InputStreamReader(res.getInputStream(), Charsets.UTF_8);
		BufferedReader lineReader = new BufferedReader(lineStream);

		while ((currentLine = lineReader.readLine()) != null) {
			ResourceLocation resourceLocation;

			if (currentLine.length() == 0 || currentLine.startsWith("#"))
				continue;

			String[] fields = currentLine.split(" ", 2);
			String keyword = fields[0];
			String data = fields[1];

			if (keyword.equalsIgnoreCase("newmtl")) {
				pushMaterial(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("Ka")) {
				currentMaterial.setAmbientColor(parseVector3f(data));
				continue;
			}

			if (keyword.equalsIgnoreCase("Kd")) {
				currentMaterial.setDiffuseColor(parseVector3f(data));
				continue;
			}

			if (keyword.equalsIgnoreCase("Ks")) {
				currentMaterial.setSpecularColor(parseVector3f(data));
				continue;
			}

			if (keyword.equalsIgnoreCase("Ns")) {
				currentMaterial.setSpecularCoefficient(Float.parseFloat(data));
				continue;
			}

			if (keyword.equalsIgnoreCase("Tr")) {
				currentMaterial.setTransparency(Float.parseFloat(data));
				continue;
			}

			if (keyword.equalsIgnoreCase("illum")) {
				currentMaterial.setIlluminationModel(Integer.parseInt(data));
				continue;
			}

			if (keyword.equalsIgnoreCase("map_Ka")) {
				currentMaterial.setAmbientTextureMap(data);
				resourceLocation = new ResourceLocation(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("map_Kd")) {
				currentMaterial.setDiffuseTextureMap(data);
				resourceLocation = new ResourceLocation(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("map_Ks")) {
				currentMaterial.setSpecularTextureMap(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("map_Ns")) {
				currentMaterial.setSpecularHighlightTextureMap(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("map_d")) {
				currentMaterial.setAlphaTextureMap(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("map_bump") || keyword.equalsIgnoreCase("bump")) {
				currentMaterial.setBumpMap(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("disp")) {
				currentMaterial.setDisplacementMap(data);
				continue;
			}

			if (keyword.equalsIgnoreCase("decal")) {
				currentMaterial.setStencilDecalMap(data);
				continue;
			}

			if (unknownCommands.contains(keyword))
				continue;

			unknownCommands.add(keyword);
		}
	}

	private Vector3f parseVector3f(String data) {
		String[] parts = data.split(" ");
		return new Vector3f(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
	}

	private void pushMaterial(String materialName) {
		currentMaterial = new ObjMaterial(materialName);
		materialLibrary.put(currentMaterial.getName(), currentMaterial);
	}
}