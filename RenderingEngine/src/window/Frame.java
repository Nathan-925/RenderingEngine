package window;

import javax.swing.JFrame;

public class Frame {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Field());
		frame.pack();
		frame.setVisible(true);
	}
}
