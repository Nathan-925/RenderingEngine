package objects;

import java.util.Arrays;

public class Pyramid extends Shape3D {
	
	private static double[][] generatePoints(double[] point, int sides, double baseRadius, double height){
		double[][] points = new double[sides+1][];
		points[0] = new double[3];
		System.arraycopy(point, 0, points[0], 0, 3);
		for(int i = 0; i < sides; i++)
			points[i+1] = new double[] {baseRadius*Math.cos((Math.PI*2)*i/sides)+point[0], point[1]+height, baseRadius*Math.sin((Math.PI*2)*i/sides)+point[2]};
		return points;
	}
	
	private static int[][] generateEdges(int sides){
		int[][] edges = new int[sides*2][];
		for(int i = 0; i < sides; i++) {
			edges[i*2] = new int[] {0, i+1};
			edges[i*2+1] = new int[] {i+1, (i+1)%(sides)+1};
		}
		for(int[] e: edges)
			System.out.println(Arrays.toString(e));
		return edges;
	}
	
	public Pyramid(double[] point, int sides, double baseRadius, double height) {
		super(generatePoints(point, sides, baseRadius, height), generateEdges(sides));
	}
	
}
