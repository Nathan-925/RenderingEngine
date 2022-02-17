package objects;

import util.PointUtils;

public class Shape3D {
	
	private double[][] points;
	private int[][] edges;
	
	public Shape3D(double[][] points, int[][] edges) {
		for(double[] point: points)
			if(point.length != 3)
				throw new IllegalArgumentException("Each point must be 3 double values");
		for(int[] edge: edges)
			if(edge.length != 2 || edge[0]<0 || edge[0]>=points.length || edge[1]<0 || edge[1]>=points.length)
				throw new IllegalArgumentException("Each edge must be 2 ints with an index of a point");
		
		this.points = points;
		this.edges = edges;
	}
	
	public void translate(double x, double y, double z) {
		for(double[] point: points) {
			point[0] += x;
			point[1] += y;
			point[2] += z;
		}
			//PointUtils.translate(point, x, y, z);
	}
	
	public void rotate(double yaw, double pitch, double roll, double[] pivot) {
		translate(-pivot[0], -pivot[1], -pivot[2]);
		for(double[] point: points)
			PointUtils.rotate(point, yaw, pitch, roll);
		translate(pivot[0], pivot[1], pivot[2]);
	}
	
	public void rotateYaw(double angle) {
		double[] center = getCenter();
		translate(-center[0], -center[1], -center[2]);
		for(double[] point: points) {
			double dx = point[0], dz = point[2];
			double cos = Math.cos(angle), sin = Math.sin(angle);
			point[0] = dx*cos-dz*sin;
			point[2] = dz*cos+dx*sin;
		}
		translate(center[0], center[1], center[2]);
	}
	
	public void rotatePitch(double angle, double centerY, double centerZ) {
		for(double[] point: points) {
			double dy = point[1]-centerY, dz = point[2]-centerZ;
			double cos = Math.cos(angle), sin = Math.sin(angle);
			point[1] = dy*cos-dz*sin+centerY;
			point[2] = dz*cos+dy*sin+centerZ;
		}
	}
	
	public void rotateRoll(double angle, double centerX, double centerY) {
		for(double[] point: points) {
			double dx = point[0]-centerX, dy = point[1]-centerY;
			double cos = Math.cos(angle), sin = Math.sin(angle);
			point[0] = dx*cos-dy*sin+centerX;
			point[1] = dy*cos+dx*sin+centerY;
		}
	}
	
	public void rotate(double yaw, double pitch, double roll) {
		rotate(yaw, pitch, roll, getCenter());
	}
	
	public void scale(double factor) {
		double center[] = getCenter();
		for(double[] point: points) {
			for(int i = 0; i < point.length; i++) {
				point[i] -= center[i];
				point[i] *= factor;
				point[i] += factor;
			}
		}
	}
	
	public double[] getCenter() {
		double out[] = new double[3];
		for(double[] point: points)
			for(int i = 0; i < point.length; i++)
				out[i] += point[i];
		out[0] /= points.length;
		out[1] /= points.length;
		out[2] /= points.length;
		return out;
	}
	
	public double[][] getPoints(){
		return points;
	}
	
	public int[][] getEdges(){
		return edges;
	}
	
}
