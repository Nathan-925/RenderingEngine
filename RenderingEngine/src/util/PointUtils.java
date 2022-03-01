package util;

public class PointUtils {

	public static void rotate(double[] point, double yaw, double pitch, double roll) {
		rotate(point, yaw, pitch, roll, new double[] {0, 0, 0});
	}
	
	public static void rotate(double[] point, double yaw, double pitch, double roll, double[] pivot) {
		double yawCos = Math.cos(yaw), yawSin = Math.sin(yaw),
			   pitchCos = Math.cos(pitch), pitchSin = Math.sin(pitch),
			   rollCos = Math.cos(roll), rollSin = Math.sin(roll);
		double dx, dy, dz;
		
		dx = point[0]-pivot[0];
		dz = point[2]-pivot[2];
		point[0] = dx*yawCos-dz*yawSin+pivot[0];
		point[2] = dz*yawCos+dx*yawSin+pivot[2];
		
		dy = point[1]-pivot[1];
		dz = point[2]-pivot[2];
		point[1] = dy*pitchCos-dz*pitchSin+pivot[1];
		point[2] = dz*pitchCos+dy*pitchSin+pivot[2];
		
		dx = point[0]-pivot[0];
		dy = point[1]-pivot[1];
		point[0] = dx*rollCos-dy*rollSin+pivot[0];
		point[1] = dy*rollCos+dx*rollSin+pivot[1];
	}
	
	public static void translate(double[] point, double x, double y, double z) {
		point[0] += x;
		point[1] += y;
		point[2] += z;
	}
	
	public static void scale(double[] point, double factor) {
		scale(point, factor, new double[] {0, 0, 0});
	}
	
	public static void scale(double[] point, double factor, double[] pivot) {
		double dx = point[0]-pivot[0], dy = point[1]-pivot[1], dz = point[2]-pivot[2];
		
		point[0] = dx*factor+pivot[0];
		point[1] = dy*factor+pivot[1];
		point[2] = dz*factor+pivot[2];
	}
	
	public static double distance(double[] point1, double[] point2) {
		return Math.sqrt(Math.pow(point1[0]-point2[0], 2)+Math.pow(point1[1]-point2[1], 2)+Math.pow(point1[2]-point2[2], 2));
	}
}
