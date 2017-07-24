package T145.magistics.client.lib.obj;

import javax.vecmath.Vector3f;

public class ObjMaterial {

	private final String name;
	private Vector3f ambientColor;
	private Vector3f diffuseColor;
	private Vector3f specularColor;
	private float specularCoefficient;
	private float transparency;
	private int illuminationModel;
	private String ambientTextureMap;
	private String diffuseTextureMap;
	private String specularTextureMap;
	private String specularHighlightTextureMap;
	private String bumpMap;
	private String displacementMap;
	private String stencilDecalMap;
	private String alphaTextureMap;

	public ObjMaterial(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Vector3f getAmbientColor() {
		return ambientColor;
	}

	public void setAmbientColor(Vector3f ambientColor) {
		this.ambientColor = ambientColor;
	}

	public Vector3f getDiffuseColor() {
		return diffuseColor;
	}

	public void setDiffuseColor(Vector3f diffuseColor) {
		this.diffuseColor = diffuseColor;
	}

	public Vector3f getSpecularColor() {
		return specularColor;
	}

	public void setSpecularColor(Vector3f specularColor) {
		this.specularColor = specularColor;
	}

	public float getSpecularCoefficient() {
		return specularCoefficient;
	}

	public void setSpecularCoefficient(float specularCoefficient) {
		this.specularCoefficient = specularCoefficient;
	}

	public float getTransparency() {
		return transparency;
	}

	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public int getIlluminationModel() {
		return illuminationModel;
	}

	public void setIlluminationModel(int illuminationModel) {
		this.illuminationModel = illuminationModel;
	}

	public String getAmbientTextureMap() {
		return ambientTextureMap;
	}

	public void setAmbientTextureMap(String ambientTextureMap) {
		this.ambientTextureMap = ambientTextureMap;
	}

	public String getDiffuseTextureMap() {
		return diffuseTextureMap;
	}

	public void setDiffuseTextureMap(String diffuseTextureMap) {
		this.diffuseTextureMap = diffuseTextureMap;
	}

	public String getSpecularTextureMap() {
		return specularTextureMap;
	}

	public void setSpecularTextureMap(String specularTextureMap) {
		this.specularTextureMap = specularTextureMap;
	}

	public String getSpecularHighlightTextureMap() {
		return specularHighlightTextureMap;
	}

	public void setSpecularHighlightTextureMap(String specularHighlightTextureMap) {
		this.specularHighlightTextureMap = specularHighlightTextureMap;
	}

	public String getBumpMap() {
		return bumpMap;
	}

	public void setBumpMap(String bumpMap) {
		this.bumpMap = bumpMap;
	}

	public String getDisplacementMap() {
		return displacementMap;
	}

	public void setDisplacementMap(String displacementMap) {
		this.displacementMap = displacementMap;
	}

	public String getStencilDecalMap() {
		return stencilDecalMap;
	}

	public void setStencilDecalMap(String stencilDecalMap) {
		this.stencilDecalMap = stencilDecalMap;
	}

	public String getAlphaTextureMap() {
		return alphaTextureMap;
	}

	public void setAlphaTextureMap(String alphaTextureMap) {
		this.alphaTextureMap = alphaTextureMap;
	}
}