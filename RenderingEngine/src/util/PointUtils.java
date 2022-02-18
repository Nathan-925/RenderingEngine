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
	
}
