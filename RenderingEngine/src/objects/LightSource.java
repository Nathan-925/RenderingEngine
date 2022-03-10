package objects;

import java.awt.Color;

public abstract class LightSource {
	
	protected double intensity;
	protected Color color;
	
	public LightSource(double intensity, Color color) {
		this.intensity = intensity;
		this.color = color;
	}
	
	public abstract double[] getEffect(double[] point, double[] vec);
	
	public double getIntensity() {
		return intensity;
	}
	
	public Color getColor() {
		return color;
	}
	
}
