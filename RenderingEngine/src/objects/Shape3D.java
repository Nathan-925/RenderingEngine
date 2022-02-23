package objects;

import util.PointUtils;

public class Shape3D {
	
	private double[][] points;
	private int[][] faces;
	
	public Shape3D(double[][] points, int[][] faces) {
		for(double[] point: points)
			if(point.length != 3)
				throw new IllegalArgumentException("Each point must be 3 double values");
		for(int[] face: faces)
			if(face.length < 2)
				throw new IllegalArgumentException("Each edge must be at least 2 ints with an index of a point");
		
		this.points = points;
		this.faces = faces;
	}
	
	public void translate(double x, double y, double z) {
		for(double[] point: points) {
			PointUtils.translate(point, x, y, z);
		}
	}
	
	public void rotate(double yaw, double pitch, double roll, double[] pivot) {
		for(double[] point: points)
			PointUtils.rotate(point, yaw, pitch, roll, pivot);
	}
	
	public void rotate(double yaw, double pitch, double roll) {
		rotate(yaw, pitch, roll, getCenter());
	}
	
	public void scale(double factor) {
		scale(factor, getCenter());
	}
	
	public void scale(double factor, double[] pivot) {
		for(double[] point: points)
			PointUtils.scale(point, factor, pivot);
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
	
	public int[][] getFaces(){
		return faces;
	}
	
}
