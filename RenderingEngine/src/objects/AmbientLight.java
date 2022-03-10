package objects;

import java.awt.Color;

import util.ColorUtils;
import util.VectorUtils;

public class AmbientLight extends LightSource {

	public AmbientLight(double intensity, Color color) {
		super(intensity, color);
	}
	
	@Override
	public double[] getEffect(double[] point, double[] vec) {
		return new double[] {intensity, intensity, intensity};
	}
	
}
