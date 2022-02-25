package util;

public class VectorUtils {

	public static double[] toUnitVector(double[] vec) {
		double mag = Math.sqrt((vec[0]*vec[0])+(vec[1]*vec[1])+(vec[2]*vec[2]));
		return new double[] {vec[0]/mag, vec[1]/mag, vec[2]/mag};
	}
	
}
