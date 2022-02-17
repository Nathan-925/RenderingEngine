package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JPanel;

import objects.Camera;
import objects.Cube;

public class Field extends JPanel implements Runnable {

	private Camera camera;
	private HashMap<Integer, Boolean> keys;
	Cube c1, c2;
	
	public Field() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setBackground(Color.CYAN);
		camera = new Camera();
		c1 = new Cube(new double[] {100, 100, 800}, 200);
		c2 = new Cube(new double[] {100, 200, 400}, 100);
		camera.addShape(c1);
		camera.addShape(c2);
		add(camera);
		
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
					case KeyEvent.VK_O:
						camera.setFOV(fov--);
						break;
					case KeyEvent.VK_P:
						camera.setFOV(fov++);
						break;
				}
		}
		c1.rotateYaw(Math.PI/40);
		//c1.rotate(Math.PI/40, 0, 0);
		//c1.rotate(0, Math.PI/400, 0);
		//c1.rotate(0, 0, Math.PI/10);
		//
		//c2.rotate(0, 0, Math.PI/400);
		//c2.rotate(0, Math.PI/400, 0);
		c2.rotate(Math.PI/40, 0, 0);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
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
