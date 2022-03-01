package objects;

import java.util.Arrays;

public class Sphere extends Shape3D {

	private static double[][] generatePoints(double[] point, int detail, double radius) {
		//TODO fix the tips (they have multiple points in the same place
		double[][] points = new double[detail*detail][];
		for(int i = 0; i < detail; i++) {
			double layerHeight = radius*Math.cos(Math.PI*i/(detail-1)), layerRadius = radius*Math.sin(Math.PI*i/(detail-1));
			for(int j = 0; j < detail; j++) {
				points[i*detail+j] = new double[] {point[0]+layerRadius*Math.cos(Math.PI*2*j/detail), point[1]+layerHeight, point[2]+layerRadius*Math.sin(Math.PI*2*j/detail)};
			}
		}
		return points;
	}
	
	private static int[][] generateEdges(int detail){
		int edges[][] = new int[detail*detail*2-detail][];
		for(int i = 0; i < detail; i++) {
			for(int j = 0; j < detail; j++)
				edges[i*detail*2+j] = new int[] {i*detail+j, i*detail+(j+1)%detail};
			if(i < detail-1)
				for(int j = 0; j < detail; j++)
					edges[i*detail*2+j+detail] = new int[] {i*detail+j, ((i+1)*detail+j)%(detail*detail)};
		}
		return edges;
	}
	
	public Sphere(double[] point, int detail, double radius) {
		super(generatePoints(point, detail, radius), generateEdges(detail));
	}
	
}
