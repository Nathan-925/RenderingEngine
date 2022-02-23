package objects;

public class LightSource {

	private double[] point;
	private double intensity;
	
	public LightSource(double[] point, double intensity) {
		this.point = point;
		this.intensity = intensity;
	}
	
	public double[] getPosition() {
		return point;
	}
	
	public double getIntensity() {
		return intensity;
	}
	
}
