package objects;

public class Cube extends Shape3D {

	public Cube(double[] point, double size) {
		super(new double[][] {{point[0], point[1], point[2]}, {point[0], point[1], point[2]+size}, {point[0], point[1]+size, point[2]}, {point[0], point[1]+size, point[2]+size},
							  {point[0]+size, point[1], point[2]}, {point[0]+size, point[1], point[2]+size}, {point[0]+size, point[1]+size, point[2]}, {point[0]+size, point[1]+size, point[2]+size}},
			  new int[][] {{0, 1}, {0, 2}, {0, 4},
						   {1, 3}, {1, 5},
						   {2, 3}, {2, 6},
						   {3, 3}, {3, 7},
						   {4, 5}, {4, 6},
						   {5, 7},
						   {6, 7}});
	}
	
}
