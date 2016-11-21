package T145.magistics.api.tiles;

public interface IVisContainer extends IVisManager {

	public float getVis();
	public float getMiasma();

	public void setVis(float amount);
	public void setMiasma(float amount);
}