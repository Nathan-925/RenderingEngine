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
		for(double[] point: points)
			PointUtils.rotate(point, yaw, pitch, roll, pivot);
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
