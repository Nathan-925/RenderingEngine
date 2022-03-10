package objects;

import java.awt.Color;
import java.util.Arrays;

public class Sphere extends Shape3D {

	private static double[][] generatePoints(double[] point, int detail, double radius) {
		double[][] points = new double[detail*detail-detail*2+2][];
		
		points[0] = new double[] {point[0], point[1]+radius, point[2]};
		
		for(int i = 0; i < detail-2; i++) {
			double layerHeight = radius*Math.cos(Math.PI*(i+1)/(detail-1)), layerRadius = radius*Math.sin(Math.PI*(i+1)/(detail-1));
			for(int j = 0; j < detail; j++) {
				points[i*detail+j+1] = new double[] {point[0]+layerRadius*Math.cos(Math.PI*2*j/detail), point[1]+layerHeight, point[2]+layerRadius*Math.sin(Math.PI*2*j/detail)};
			}
		}
		
		points[points.length-1] = new double[] {point[0], point[1]-radius, point[2]};
		
		return points;
	}
	
	//TODO this doesn't work
	private static int[][] generateEdges(int detail){
		int edges[][] = new int[detail*detail*2-detail][];
		for(int i = 0; i < detail; i++) {
			for(int j = 0; j < detail; j++)
				edges[i*detail*2+j] = new int[] {i*detail+j, i*detail+(j+1)%detail};
			if(i < detail-1)
				for(int j = 0; j < detail; j++)
					edges[i*detail*2+j+detail] = new int[] {i*detail+j, (i+1)*detail+j};
		}
		return edges;
	}
	
	private static int[][] generateFaces(int detail){
		int faces[][] = new int[detail*detail-detail][];
		
		for(int i = 0; i < detail; i++)
			faces[i] = new int[] {0, (i+1)%detail+1, i+1};
		
		for(int i = 0; i < detail-2; i++) {
			for(int j = 0; j < detail; j++)
				faces[i*detail+detail+j] = new int[] {i*detail+j+1, i*detail+(j+1)%detail+1, (i+1)*detail+(j+1)%detail+1, (i+1)*detail+j+1};
		}
		
		for(int i = 0; i < detail; i++)
			faces[faces.length-detail+i] = new int[] {detail*detail-detail*2+1, detail*detail-detail*3+1+i, detail*detail-detail*3+1+((i+1)%detail)};
		
		return faces;
	}
	
	public Sphere(double[] point, int detail, double radius, Color color, boolean filled) {
		super(generatePoints(point, detail, radius), filled ? generateFaces(detail) : generateEdges(detail), color);
	}
	
}
