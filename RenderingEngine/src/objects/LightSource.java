package objects;

import java.awt.Color;

public class LightSource {
	
	private double[] point;
	private double intensity;
	private Color color;
	
	public LightSource(double[] point, double intensity, Color color) {
		this.point = point;
		this.intensity = intensity;
		this.color = color;
	}
	
	public Color applyLight(double[] vec, Color ployColor) {
		
	}
	
	public double[] getPosition() {
		return point;
	}
	
	public double getIntensity() {
		return intensity;
	}
	
	public Color getColor() {
		return color;
	}
	
}
