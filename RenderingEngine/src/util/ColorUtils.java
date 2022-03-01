package util;

import java.awt.Color;

public class ColorUtils {

	public static Color multiply(Color c1, Color c2) {
		return new Color((int)(((float)c1.getRed()*c2.getRed())/255),
						 (int)(((float)c1.getGreen()*c2.getGreen())/255),
						 (int)(((float)c1.getBlue()*c2.getBlue())/255));
	}
	
	public static Color multuply(Color c, double fac) {
		return new Color((int)(c.getRed()*fac),
						 (int)(c.getGreen()*fac),
						 (int)(c.getBlue()*fac));
	}
	
}
