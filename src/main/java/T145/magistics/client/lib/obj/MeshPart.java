package T145.magistics.client.lib.obj;

import java.util.ArrayList;
import java.util.List;

public class MeshPart {

	private String name;
	private ObjMaterial material;
	private List<int[]> indices = new ArrayList();
	private int tintIndex = -1;

	public MeshPart(MeshPart p, int tintIndex) {
		this.name = p.name;
		this.material = p.material;
		this.indices = p.indices;
		this.tintIndex = tintIndex;
	}

	public MeshPart() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjMaterial getMaterial() {
		return material;
	}

	public void setMaterial(ObjMaterial material) {
		this.material = material;
	}

	public List<int[]> getIndices() {
		return indices;
	}

	public int getTintIndex() {
		return tintIndex;
	}

	public void setTintIndex(int tintIndex) {
		this.tintIndex = tintIndex;
	}

	public void addTriangleFace(int[] a, int[] b, int[] c) {
		this.indices.add(a);
		this.indices.add(b);
		this.indices.add(c);
		this.indices.add(c);
	}

	public void addQuadFace(int[] a, int[] b, int[] c, int[] d) {
		this.indices.add(a);
		this.indices.add(b);
		this.indices.add(c);
		this.indices.add(d);
	}
}