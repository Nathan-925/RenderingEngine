package objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

public class Camera extends JPanel {
	
	private ArrayList<Shape3D> shapes;
	private double[] point;
	private double yaw, pitch, roll, fov;
	
	public Camera(double[] point, double yaw, double pitch, double roll) {
		if(point.length != 3)
			throw new IllegalArgumentException("Point must be 3 double values");
		setBackground(Color.WHITE);
		
		shapes = new ArrayList<>();
		this.point = point;
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;
		fov = 90;
	}
	
	public Camera(double[] point) {
		this(point, 0, 0, 0);
	}
	
	public Camera() {
		this(new double[] {0, 0, 0});
	}
	
	public void move(double x, double y, double z) {
		point[0] += x;
		point[1] += y;
		point[2] += z;
	}
	
	public void rotate(double yaw, double pitch, double roll) {
		this.yaw = (this.yaw+yaw)%(Math.PI*2);
		this.pitch = (this.pitch+pitch)%(Math.PI*2);
		this.roll = (this.roll+roll)%(Math.PI*2);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return getParent().getSize();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform trans = new AffineTransform();
		trans.translate(getWidth()/2, getHeight()/2);
		g2.transform(trans);
		
		for(Shape3D shape: shapes) {
			
			double points[][] = shape.getPoints();
			double planeDistance = getWidth()/(2*Math.tan(Math.toRadians(fov/2)));
			
			g2.setColor(Color.BLACK);
			for(int[] edge: shape.getEdges()) {
				if(Math.min(points[edge[0]][2]-point[2], points[edge[1]][2]-point[2]) > 0) {
					int p1x = (int)((points[edge[0]][0]-point[0])*(planeDistance/(points[edge[0]][2]-point[2])));
					int p1y = (int)((points[edge[0]][1]-point[1])*(planeDistance/(points[edge[0]][2]-point[2])));
					int p2x = (int)((points[edge[1]][0]-point[0])*(planeDistance/(points[edge[1]][2]-point[2])));
					int p2y = (int)((points[edge[1]][1]-point[1])*(planeDistance/(points[edge[1]][2]-point[2])));
					
					g2.drawLine(p1x, p1y, p2x, p2y);
				}
			}
		}
	}
	
	public void setFOV(double fov) {
		this.fov = Math.max(1, Math.min(179, fov));
	}
	
	public void addShape(Shape3D shape) {
		shapes.add(shape);
	}
	
	public void addShapes(Collection<Shape3D> shapes) {
		this.shapes.addAll(shapes);
	}
	
}
