package util;

public class PointUtils {

	public static void rotate(double[] point, double yaw, double pitch, double roll) {
		double yawCos = Math.cos(yaw), yawSin = Math.sin(yaw),
			   pitchCos = Math.cos(pitch), pitchSin = Math.sin(pitch),
			   rollCos = Math.cos(roll), rollSin = Math.sin(roll);
		
		point[0] = point[0]*yawCos-point[2]*yawSin;
		point[2] = point[2]*yawCos+point[0]*yawSin;
		//System.out.println(point[0]);
		
		//point[1] = point[1]*pitchCos-point[2]*pitchSin;
		//point[2] = point[2]*pitchCos+point[1]*pitchSin;
		
		//point[0] = point[0]*rollCos-point[1]*rollSin;
		//point[1] = point[1]*rollCos+point[0]*rollSin;
	}
	
	public static void translate(double[] point, double x, double y, double z) {
		point[0] += x;
		point[1] += y;
		point[2] += z;
	}
	
}
