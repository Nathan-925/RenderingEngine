package util;

public class VectorUtils {
	
	public static double[] subtract(double[] vec1, double[] vec2) {
		return new double[] {vec1[0]-vec2[0], vec1[1]-vec2[1], vec1[2]-vec2[2]};
	}

	public static double[] toUnitVector(double[] vec) {
		double mag = Math.sqrt((vec[0]*vec[0])+(vec[1]*vec[1])+(vec[2]*vec[2]));
		return new double[] {vec[0]/mag, vec[1]/mag, vec[2]/mag};
	}
	
	public static double[] crossProduct(double[] vec1, double[] vec2) {
		double cross[][] = new double[2][5];
		double out[] = new double[3];
		System.arraycopy(vec1, 0, cross[0], 0, 3);
		System.arraycopy(vec2, 0, cross[1], 0, 3);
		System.arraycopy(vec1, 0, cross[0], 3, 2);
		System.arraycopy(vec2, 0, cross[1], 3, 2);
		
		for(int i = 0; i < 3; i++)
			out[i] += cross[0][i+1]*cross[1][i+2];
		for(int i = 4; i > 1; i--)
			out[i%3] -= cross[0][i-1]*cross[1][i-2];
		return out;
	}
	
	public static double dotProduct(double[] vec1, double[] vec2) {
		double out = 0;
		for(int i = 0; i < 3; i++)
			out += vec1[i]*vec2[i];
		return out;
	}
	
	public static double magnitude(double[] vec) {
		return Math.sqrt((vec[0]*vec[0])+(vec[1]*vec[1])+(vec[2]*vec[2]));
	}
	
	public static double angleBetween(double[] vec1, double[] vec2) {
		return Math.acos(dotProduct(vec1, vec2)/(magnitude(vec1)*magnitude(vec2)));
	}
	
}
