package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import objects.AmbientLight;
import objects.Camera;
import objects.Cube;
import objects.PointLight;
import objects.Pyramid;
import objects.Sphere;
import util.PointUtils;

public class Field extends JPanel implements Runnable {

	private Camera camera;
	private HashMap<Integer, Boolean> keys;
	Cube c1, c2;
	Sphere sph;
	double[] lpnt;
	
	public Field() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setBackground(Color.CYAN);
		camera = new Camera();
		lpnt = camera.getPosition();
		camera.addLightSource(new PointLight(new double[] {500, -400, 0}, 0.7, Color.WHITE));
		camera.addLightSource(new AmbientLight(0.3, Color.WHITE));
		c1 = new Cube(new double[] {100, 100, 800}, 200, Color.BLUE, true);
		c2 = new Cube(new double[] {100, 200, 400}, 100, Color.GREEN, true);
		//camera.addShape(c1);
		//camera.addShape(c2);
		camera.addShape(new Cube(new double[] {100, 100, 750}, 50, Color.BLUE, true));
		camera.addShape(new Cube(new double[] {100, 150, 750}, 50, Color.BLUE, true));
		camera.addShape(new Cube(new double[] {100, 200, 750}, 50, Color.BLUE, true));
		camera.addShape(new Cube(new double[] {100, 250, 750}, 50, Color.BLUE, true));
		camera.addShape(new Pyramid(new double[] {-200, 100, 400}, 6, 100, 200, 0, Color.BLUE, true));
		//camera.addShape(new Sphere(new double[] {0, 0, 0}, 6, 100));
		//camera.addShape(new Sphere(new double[] {-300, 0, 0}, 4, 100));
		//camera.addShape(new Sphere(new double[] {-600, 0, 0}, 10, 100));
		
		sph = new Sphere(new double[] {200, 200, 900}, 25, 100, Color.BLUE, true);
		camera.addShape(sph);
		camera.addShape(new Sphere(new double[] {0, 0, 0}, 3, 100, new Color(100, 0, 200), true));
		//camera.addShape(new Pyramid(new double[] {200, 0, 900}, 4, Math.hypot(100, 100), 100, Math.PI/4, Color.MAGENTA, true));
		
		keys = new HashMap<>();
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				keys.put(e.getKeyCode(), true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				keys.put(e.getKeyCode(), false);
			}
		});
		setFocusable(true);
		
		new Thread(this).start();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 800);
	}
	
	double fov = 90;
	public void tick() {
		for(int n: keys.keySet()) {
			if(keys.get(n))
				switch(n) {
					case KeyEvent.VK_W:
						camera.move(0, 0, 1);
						break;
					case KeyEvent.VK_S:
						camera.move(0, 0, -1);
						break;	
					case KeyEvent.VK_A:
						camera.move(-1, 0, 0);
						break;
					case KeyEvent.VK_D:
						camera.move(1, 0, 0);
						break;
					case KeyEvent.VK_SHIFT:
						camera.move(0, 1, 0);
						break;
					case KeyEvent.VK_CONTROL:
						camera.move(0, -1, 0);
						break;
					case KeyEvent.VK_LEFT:
						camera.rotate(-Math.PI/100, 0, 0);
						break;
					case KeyEvent.VK_RIGHT:
						camera.rotate(Math.PI/100, 0, 0);
						break;
					case KeyEvent.VK_UP:
						camera.rotate(0, -Math.PI/100, 0);
						break;
					case KeyEvent.VK_DOWN:
						camera.rotate(0, Math.PI/100, 0);
						break;
					case KeyEvent.VK_Q:
						camera.rotate(0, 0, Math.PI/100);
						break;
					case KeyEvent.VK_E:
						camera.rotate(0, 0, -Math.PI/100);
						break;
					case KeyEvent.VK_O:
						camera.setFOV(fov--);
						break;
					case KeyEvent.VK_P:
						camera.setFOV(fov++);
						break;
					case KeyEvent.VK_I:
						PointUtils.translate(lpnt, 0, -1, 0);
						break;
					case KeyEvent.VK_K:
						PointUtils.translate(lpnt, 0, 1, 0);
						break;
					case KeyEvent.VK_J:
						PointUtils.translate(lpnt, -1, 0, 0);
						break;
					case KeyEvent.VK_L:
						PointUtils.translate(lpnt, 1, 0, 0);
						break;
				}
		}
		//c1.rotate(Math.PI/400, 0, 0);
		//c1.rotate(0, Math.PI/400, 0);
		//c1.rotate(0, 0, Math.PI/400);
		
		//c2.rotate(0, 0, Math.PI/400);
		//c2.rotate(0, Math.PI/400, 0);
		//c2.rotate(Math.PI/400, 0, 0);
		
		//sph.rotate(Math.PI/500, Math.PI/500, Math.PI/500);
		//System.out.println(Arrays.toString(lpnt));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage cam = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		g.drawImage(camera.draw(cam), 0, 0, null);
	}

	@Override
	public void run() {
		try {
			while(true) {
				tick();
				repaint();
				Thread.sleep(5);
			}
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
