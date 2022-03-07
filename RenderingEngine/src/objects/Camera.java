package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import util.ColorUtils;
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
						center[j] /= face.length;
					int xArr[] = Arrays.stream(temp).mapToInt(n -> (int)(n[0]*(planeDistance/n[2]))).toArray();
					int yArr[] = Arrays.stream(temp).mapToInt(n -> (int)(n[1]*(planeDistance/n[2]))).toArray();
					
					double norm[] = VectorUtils.crossProduct(VectorUtils.subtract(points[face[0]], center), VectorUtils.subtract(points[face[1]], center));
					
					double lightEffect[] = new double[3];
					for(LightSource light: lights) {
						double eff[] = light.getEffect(center, norm);
						for(int i = 0; i < 3; i++)
							lightEffect[i] += eff[i];
					}
					g2.setColor(ColorUtils.multiply(shape.getColor(), lightEffect));
					
					if(xArr.length > 2)
						g2.fillPolygon(xArr, yArr, xArr.length);
					else
						g2.drawLine(xArr[0], yArr[0], xArr[1], yArr[1]);
					
					norm = VectorUtils.toUnitVector(norm);
					for(int i = 0; i < 3; i++)
						norm[i] *= 10;
					
					/*
					double centerNorm[] = {center[0]+norm[0], center[1]+norm[1], center[2]+norm[2]};
					PointUtils.translate(center, -point[0], -point[1], -point[2]);
					PointUtils.rotate(center, yaw, pitch, roll);
					PointUtils.translate(centerNorm, -point[0], -point[1], -point[2]);
					PointUtils.rotate(centerNorm, yaw, pitch, roll);
					if(Math.min(center[2], centerNorm[2]) > 0) {
						g2.setColor(Color.WHITE);
						int pnts[] = {(int)(center[0]*(planeDistance/center[2])),
									  (int)(center[1]*(planeDistance/center[2])),
									  (int)(centerNorm[0]*(planeDistance/centerNorm[2])),
									  (int)(centerNorm[1]*(planeDistance/centerNorm[2]))};
						g2.drawLine(pnts[0], pnts[1], pnts[2], pnts[3]);
					}
					*/
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
