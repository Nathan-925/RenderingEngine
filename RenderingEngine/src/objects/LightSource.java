package objects;

import java.awt.Color;

public abstract class LightSource {
	
	protected double intensity;
	protected Color color;
	
	public LightSource(double intensity, Color color) {
		this.intensity = intensity;
		this.color = color;
	}
	
	public abstract  Color applyLight(double[] point, double[] vec, Color polyColor);
	
	public double getIntensity() {
		return intensity;
	}
	
	public Color getColor() {
		return color;
	}
	
}
