package objects;

import java.awt.Color;

public class Pyramid extends Shape3D {
	
	private static double[][] generatePoints(double[] point, int sides, double baseRadius, double height, double angle){
		double[][] points = new double[sides+1][];
		points[0] = new double[3];
		System.arraycopy(point, 0, points[0], 0, 3);
		for(int i = 0; i < sides; i++)
			points[i+1] = new double[] {baseRadius*Math.cos((Math.PI*2)*i/sides+angle)+point[0], point[1]+height, baseRadius*Math.sin((Math.PI*2)*i/sides+angle)+point[2]};
		return points;
	}
	
	private static int[][] generateEdges(int sides){
		int[][] edges = new int[sides*2][];
		for(int i = 0; i < sides; i++) {
			edges[i*2] = new int[] {0, i+1};
			edges[i*2+1] = new int[] {i+1, (i+1)%(sides)+1};
		}
		return edges;
	}
	
	private static int[][] generateFaces(int sides){
		int[][] edges = new int[sides+1][];
		edges[0] = new int[sides];
		for(int i = 0; i < edges[0].length; i++)
			edges[0][sides-1-i] = i+1;
		for(int i = 0; i < sides; i++) {
			edges[i+1] = new int[] {0, i+1, (i+1)%(sides)+1};
		}
		return edges;
	}
	
	public Pyramid(double[] point, int sides, double baseRadius, double height, double angle, Color color, boolean filled) {
		super(generatePoints(point, sides, baseRadius, height, angle), filled ? generateFaces(sides) : generateEdges(sides), color);
	}
	
}
