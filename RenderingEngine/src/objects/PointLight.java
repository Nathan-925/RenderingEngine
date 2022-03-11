package objects;

import java.awt.Color;

import util.ColorUtils;
import util.VectorUtils;

public class PointLight extends LightSource{

	private double[] point;
	
	public PointLight(double[] point, double intensity, Color color) {
		super(intensity, color);
		this.point = point;
	}
	
	@Override
	public double[] getEffect(double[] point, double[] vec) {
		double lightVec[] = VectorUtils.subtract(this.point, point);
		double effect = Math.max(0, intensity*VectorUtils.dotProduct(lightVec, vec)/(VectorUtils.magnitude(lightVec)*VectorUtils.magnitude(vec)));
		return new double[] {effect, effect, effect};
	}

}
