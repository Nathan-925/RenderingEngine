package objects;

import java.awt.Color;

public class CoolShape2 extends Shape3D {

	private static double[][] generatePoints(double[] point, int detail, double radius) {
		double[][] points = new double[detail*detail][];
		for(int i = 0; i < detail; i++) {
			for(int j = 0; j < detail; j++) {
				points[i*detail+j] = new double[] {point[0]+radius*Math.cos(Math.PI*2*j/detail)-radius*Math.sin(Math.PI*2*i/detail), point[1]+radius*2*Math.cos(Math.PI*i/detail), point[2]+radius*Math.sin(Math.PI*2*j/detail)-radius*Math.sin(Math.PI*2*i/detail)};
			}
		}
		//for(double[] row: points)
			//System.out.println(Arrays.toString(row));
		return points;
	}
	
	private static int[][] generateEdges(int detail){
		int edges[][] = new int[detail*detail*2][];
		for(int i = 0; i < detail; i++) {
			for(int j = 0; j < detail; j++) {
				edges[i*detail*2+j] = new int[] {i*detail+j, i*detail+(j+1)%detail};
				edges[i*detail*2+j+detail] = new int[] {i*detail+j, ((i+1)*detail+j)%(detail*detail)};
			}
		}
		//for(int[] row: edges)
			//System.out.println(Arrays.toString(row));
		return edges;
	}
	
	public CoolShape2(double[] point, int detail, double radius, Color color) {
		super(generatePoints(point, detail, radius), generateEdges(detail), color);
	}
	
}
