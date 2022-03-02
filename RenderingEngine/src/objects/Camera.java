package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import util.PointUtils;
import util.VectorUtils;

public class Camera {
	
	private ArrayList<Shape3D> shapes;
	private ArrayList<LightSource> lights;
	private double[] point;
	private double yaw, pitch, roll, fov;
	
	public Camera(double[] point, double yaw, double pitch, double roll) {
		if(point.length != 3)
			throw new IllegalArgumentException("Point must be 3 double values");
		
		shapes = new ArrayList<>();
		lights = new ArrayList<>();
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
		PointUtils.translate(point, z*Math.sin(yaw)+x*Math.cos(yaw), y, z*Math.cos(yaw)-x*Math.sin(yaw));
	}
	
	public void rotate(double yaw, double pitch, double roll) {
		this.yaw = (this.yaw+yaw)%(Math.PI*2);
		this.pitch = (this.pitch+pitch)%(Math.PI*2);
		this.roll = (this.roll+roll)%(Math.PI*2);
	}
	
	public BufferedImage draw(BufferedImage img) {
		Graphics2D g2 = (Graphics2D)img.getGraphics();
		AffineTransform trans = new AffineTransform();
		trans.translate(img.getWidth()/2, img.getHeight()/2);
		g2.transform(trans);
		
		for(Shape3D shape: shapes) {
			
			double points[][] = shape.getPoints();
			double planeDistance = img.getWidth()/(2*Math.tan(Math.toRadians(fov/2)));
			
			Faces:
			for(int[] face: shape.getFaces()) {
					double temp[][] = new double[face.length][3];
					double center[] = new double[3];
					for(int i = 0; i < temp.length; i++) {
						for(int j = 0; j < 3; j++)
							center[j] += points[face[i]][j];
						System.arraycopy(points[face[i]], 0, temp[i], 0, 3);
						PointUtils.translate(temp[i], -point[0], -point[1], -point[2]);
						PointUtils.rotate(temp[i], yaw, pitch, roll);
						if(temp[i][2] <= 0)
							continue Faces;
					}
					for(int j = 0; j < 3; j++)
						center[j] /= points.length;
					int xArr[] = Arrays.stream(temp).mapToInt(n -> (int)(n[0]*(planeDistance/n[2]))).toArray();
					int yArr[] = Arrays.stream(temp).mapToInt(n -> (int)(n[1]*(planeDistance/n[2]))).toArray();
					
					Color c = shape.getColor();
					for(LightSource light: lights)
						c = light.applyLight(center, VectorUtils.crossProduct(VectorUtils.subtract(center, points[face[0]]), VectorUtils.subtract(center, points[face[1]])), c);
					g2.setColor(c);
					
					if(xArr.length > 2)
						g2.fillPolygon(xArr, yArr, xArr.length);
					else
						g2.drawLine(xArr[0], yArr[0], xArr[1], yArr[1]);
			}
		}
		
		return img;
	}
	
	public void setFOV(double fov) {
		this.fov = Math.max(1, Math.min(179, fov));
	}
	
	public void addShape(Shape3D shape) {
		shapes.add(shape);
	}
	
	public void addLightSource(LightSource light) {
		lights.add(light);
	}
	
	public void addShapes(Collection<Shape3D> shapes) {
		this.shapes.addAll(shapes);
	}
	
	public double[] getPosition() {
		return point;
	}
	
}
